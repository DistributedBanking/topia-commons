package io.bitexpress.topia.commons.basic.competition;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VmLockTemplate2 implements LockTemplate2 {
	private static final Logger logger = LoggerFactory.getLogger(VmLockTemplate.class);

	private ConcurrentMap<String, Lock> lockMap = new ConcurrentHashMap<>();

	/**
	 * 分布式锁,相同的或者不同场景共用一个lockKey,相同的场景执行有冷却时间,不同的场景无冷却时间
	 * 
	 * @param lockKey
	 *            既被用作锁名，也被用作锁信息名
	 * @param lockCallback
	 * @return
	 */
	public <T> T execute(String lockKey, LockCallback<T> lockCallback) {
		Lock lock = lockMap.get(lockKey);
		if (lock == null) {
			lockMap.putIfAbsent(lockKey, new ReentrantLock());
			lock = lockMap.get(lockKey);
		}
		try {
			boolean tryLock = lock.tryLock(0, TimeUnit.SECONDS);
			if (!tryLock) {
				logger.debug("lock failure,skip this turn.{}", lockKey);
				return null;
			}
			logger.trace("lock acquired:{}", lockKey);
			T locked = lockCallback.locked();
			return locked;
		} catch (InterruptedException e) {
			throw new ContextedRuntimeException(e);
		} finally {
			lock.unlock();
			logger.trace("lock released:{}", lockKey);
		}
	}

}
