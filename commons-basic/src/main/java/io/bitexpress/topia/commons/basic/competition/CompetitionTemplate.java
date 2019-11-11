package io.bitexpress.topia.commons.basic.competition;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Required;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IAtomicReference;
import com.hazelcast.core.ILock;


/**
 * 用LockTemplate代替
 * 
 * @author shwh1
 * @see LockTemplate
 *
 */
@Deprecated
public class CompetitionTemplate implements InitializingBean {

	private static final Logger logger = LoggerFactory.getLogger(CompetitionTemplate.class);

	public static final Period DEFAULT_COOLING_PERIOD = Period.minutes(1);

	private HazelcastInstance hazelcastInstance;

	private ILock lock;

	private IAtomicReference<DateTime> lastExecutedTime;

	private String lockKey;
	private String lastExecutedTimeKey;

	private Period coolingPeriod = DEFAULT_COOLING_PERIOD;

	@Override
	public void afterPropertiesSet() throws Exception {
		lock = hazelcastInstance.getLock(lockKey);
		lastExecutedTime = hazelcastInstance.getAtomicReference(lastExecutedTimeKey);
	}

	public <T> T execute(CompetitionCallback<T> competitionCallback) {

		boolean tryLock = lock.tryLock();
		if (!tryLock) {
			logger.debug("lock failure,skip this turn.");
			return null;
		}
		try {
			DateTime clusterTime = lastExecutedTime.get();
			DateTime localTime = new DateTime();
			if (clusterTime != null) {
				if (!localTime.isAfter(clusterTime)) {
					logger.debug("clusterTime:{} is later than local time:{}.", clusterTime, localTime);
					return null;
				}
				Interval interval = new Interval(clusterTime, localTime);
				if (interval.toDuration().compareTo(coolingPeriod.toStandardDuration()) <= 0) {
					logger.debug("cooling:{}", interval);
					return null;
				}
			}
			boolean compareAndSet = lastExecutedTime.compareAndSet(clusterTime, localTime);
			if (compareAndSet) {
				return competitionCallback.won(localTime.toDate());
			} else {
				logger.error("cas failure");
				return null;
			}
		} finally {
			lock.unlock();
		}
	}

	public void setCoolingPeriod(Period coolingPeriod) {
		this.coolingPeriod = coolingPeriod;
	}

	@Required
	public void setLockKey(String lockKey) {
		this.lockKey = lockKey;
	}

	@Required
	public void setLastExecutedTimeKey(String lastExecutedTimeKey) {
		this.lastExecutedTimeKey = lastExecutedTimeKey;
	}

	@Required
	public void setHazelcastInstance(HazelcastInstance hazelcastInstance) {
		this.hazelcastInstance = hazelcastInstance;
	}

}
