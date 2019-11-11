package io.bitexpress.topia.commons.basic.competition;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.apache.commons.lang3.tuple.Pair;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IAtomicReference;
import com.hazelcast.core.ILock;

/**
 * lockKey never recycled
 * 
 * @author shwh1
 *
 */
public class HazelcastLockTemplate implements LockTemplate {

	private static final Logger logger = LoggerFactory.getLogger(HazelcastLockTemplate.class);

	public static final Period DEFAULT_COOLING_PERIOD = Period.seconds(5);

	private HazelcastInstance hazelcastInstance;

	private Period coolingPeriod = DEFAULT_COOLING_PERIOD;

	private int leaseMinutes = 30;

	/**
	 * 分布式锁,相同的或者不同场景共用一个lockKey,相同的场景执行有冷却时间,不同的场景无冷却时间
	 * 
	 * @param lockKey.
	 *            既被用作锁名，也被用作锁信息名
	 * @param scenario
	 *            场景
	 * @param lockCallback
	 * @return
	 */
	public <T> T execute(String lockKey, String scenario, LockCallback<T> lockCallback) {
		ILock lock = hazelcastInstance.getLock(lockKey);
		IAtomicReference<Pair<DateTime, String>> lockInfoRef = hazelcastInstance.getAtomicReference(lockKey);
		boolean tryLock = false;
		try {
			tryLock = lock.tryLock(0, TimeUnit.SECONDS, leaseMinutes, TimeUnit.MINUTES);
			if (!tryLock) {
				logger.debug("lock failure,skip this turn.");
				return null;
			}
			Pair<DateTime, String> lockInfo = lockInfoRef.get();
			if (lockInfo != null && Objects.equals(lockInfo.getRight(), scenario)) {
				DateTime localTime = new DateTime();
				if (lockInfo.getLeft().isAfter(localTime)) {
					logger.debug("lock time:{} is later than local time:{}.", lockInfo.getLeft(), localTime);
					return null;
				}
				Interval interval = new Interval(lockInfo.getLeft(), localTime);
				if (interval.toDuration().compareTo(coolingPeriod.toStandardDuration()) <= 0) {
					logger.debug("cooling:{}", interval);
					return null;
				}
			}
			T locked = lockCallback.locked();
			lockInfoRef.set(Pair.of(new DateTime(), scenario));
			return locked;
		} catch (InterruptedException e) {
			throw new ContextedRuntimeException(e);
		} finally {
			if (tryLock) {
				lock.unlock();
			}
		}
	}

	public void setCoolingPeriod(Period coolingPeriod) {
		this.coolingPeriod = coolingPeriod;
	}

	public void setLeaseMinutes(int leaseMinutes) {
		this.leaseMinutes = leaseMinutes;
	}

	@Required
	public void setHazelcastInstance(HazelcastInstance hazelcastInstance) {
		this.hazelcastInstance = hazelcastInstance;
	}

}
