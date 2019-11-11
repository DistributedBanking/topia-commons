package io.bitexpress.topia.commons.basic.competition;

/**
 * lockKey never recycled
 * 
 * @author shwh1
 * @deprecated use LockTemplate2
 */
@Deprecated
public interface LockTemplate {

	<T> T execute(String lockKey, String scenario, LockCallback<T> lockCallback);

}
