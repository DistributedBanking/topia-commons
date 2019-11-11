package io.bitexpress.topia.commons.data.retry;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Deprecated
public interface RetryableCommandFunction<CMD> {

	public CMD getNotFailureCommand(String parentType, Long parentId, String command, String failureStatus);

	/**
	 * 
	 * @param retryIndex
	 * @param lockCallback
	 * @param paramsSupplier
	 * @param failureStatus
	 * @return
	 */
	public <T> CMD lockAndPrepareCommand(RetryParent<T> retryParent, Consumer<T> lockCallback,
			Supplier<Map<String, String>> paramsSupplier, String failureStatus);

	public CMD prepareCommand(String parentType, Long parentId, String command, Map<String, String> params,
			String failureStatus);

	public CMD updateStatus(String commandId, String status);

	public CMD updateStatus(CMD command, String status);
}
