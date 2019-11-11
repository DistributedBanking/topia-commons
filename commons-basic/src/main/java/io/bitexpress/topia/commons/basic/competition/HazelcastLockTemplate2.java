package io.bitexpress.topia.commons.basic.competition;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ILock;

/**
 * lockKey never recycled
 * 
 * @author shwh1
 *
 */
public class HazelcastLockTemplate2 implements LockTemplate2 {

	private static final Logger logger = LoggerFactory.getLogger(HazelcastLockTemplate2.class);

	private HazelcastInstance hazelcastInstance;

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
	public <T> T execute(String lockKey, LockCallback<T> lockCallback) {
		ILock lock = hazelcastInstance.getLock(lockKey);
		boolean tryLock = false;
		try {
			tryLock = lock.tryLock(0, TimeUnit.SECONDS, leaseMinutes, TimeUnit.MINUTES);
			if (!tryLock) {
				logger.debug("lock failure: {}", lockKey);
				return null;
			}
			logger.trace("lock acquired:{}", lockKey);
			T locked = lockCallback.locked();
			return locked;
		} catch (InterruptedException e) {
			throw new ContextedRuntimeException(e);
		} finally {
			if (tryLock) {
				lock.unlock();
				logger.trace("lock released:{}", lockKey);
			}
		}
	}

	@Required
	public void setHazelcastInstance(HazelcastInstance hazelcastInstance) {
		this.hazelcastInstance = hazelcastInstance;
	}

	public void setLeaseMinutes(int leaseMinutes) {
		this.leaseMinutes = leaseMinutes;
	}

}
