package io.bitexpress.topia.commons.basic.competition;

/**
 * lockKey never recycled
 * 
 * @author godmonth
 *
 */
public interface LockTemplate2 {

	<T> T execute(String lockKey, LockCallback<T> lockCallback);

}
