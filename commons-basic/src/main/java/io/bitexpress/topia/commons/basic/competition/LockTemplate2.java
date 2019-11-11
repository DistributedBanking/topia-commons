package io.bitexpress.topia.commons.basic.competition;

/**
 * lockKey never recycled
 * 
 * @author shwh1
 *
 */
public interface LockTemplate2 {

	<T> T execute(String lockKey, LockCallback<T> lockCallback);

}
