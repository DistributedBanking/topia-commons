package io.bitexpress.topia.commons.basic.competition;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.apache.commons.lang3.tuple.Pair;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VmLockTemplate implements LockTemplate {

	private static final Logger logger = LoggerFactory.getLogger(VmLockTemplate.class);

	public static final Period DEFAULT_COOLING_PERIOD = Period.seconds(30);

	private Period coolingPeriod = DEFAULT_COOLING_PERIOD;

	private ConcurrentMap<String, Lock> lockMap = new ConcurrentHashMap<>();

	private Map<String, Pair<DateTime, String>> lockInfoMap = new ConcurrentHashMap<>();

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
		Lock lock = lockMap.get(lockKey);
		if (lock == null) {
			lockMap.putIfAbsent(lockKey, new ReentrantLock());
			lock = lockMap.get(lockKey);
		}
		Pair<DateTime, String> lockInfo = null;
		try {
			boolean tryLock = lock.tryLock(0, TimeUnit.SECONDS);
			if (!tryLock) {
				logger.debug("lock failure,skip this turn.");
				return null;
			}
			lockInfo = lockInfoMap.get(lockKey);
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
			lockInfoMap.put(lockKey, Pair.of(new DateTime(), scenario));
			return locked;
		} catch (InterruptedException e) {
			throw new ContextedRuntimeException(e);
		} finally {
			lock.unlock();
		}
	}

	public void setCoolingPeriod(Period coolingPeriod) {
		this.coolingPeriod = coolingPeriod;
	}

	public Map<String, Pair<DateTime, String>> getLockInfoMap() {
		return lockInfoMap;
	}

}
