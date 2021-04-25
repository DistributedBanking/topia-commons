package io.bitexpress.topia.commons.basic.competition;

/**
 * lockKey never recycled
 * 
 * @author godmonth
 *
 */
public interface LockTemplate2 {
	/**
	 *
	 * @param lockKey
	 * @param lockCallback
	 * @param <T> 在锁释放后才会返回结果
	 * @return
	 */
	<T> T execute(String lockKey, LockCallback<T> lockCallback);

}
