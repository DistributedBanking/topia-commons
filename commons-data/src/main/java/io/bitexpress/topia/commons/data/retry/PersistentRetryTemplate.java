package io.bitexpress.topia.commons.data.retry;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.apache.commons.lang3.StringUtils;

@Deprecated
public class PersistentRetryTemplate<CMD extends Statusable> {

	protected RetryableCommandFunction<CMD> retryableCommandFunction;

	protected String cmdSuccessStatus;

	protected String cmdFailureStatus;

	/**
	 * 
	 * @param retryParent
	 * @param lockCallback
	 * @param params
	 * @param function
	 * @return
	 */
	public <T> CMD execute(RetryParent<T> retryParent, Consumer<T> lockCallback, Supplier<Map<String, String>> params,
			Function<ParentAndCommand<T, CMD>, String> function) {
		CMD retryableCommand = retryableCommandFunction.lockAndPrepareCommand(retryParent, lockCallback, params,
				cmdFailureStatus);

		if (cmdSuccessStatus.equals(retryableCommand.getStatus())) {
			return retryableCommand;
		}

		String cmdStatus = function.apply(new ParentAndCommand<T, CMD>(retryParent.getParent(), retryableCommand));

		if (StringUtils.isNotBlank(cmdStatus) && !cmdStatus.equals(retryableCommand.getStatus())) {
			retryableCommand = retryableCommandFunction.updateStatus(retryableCommand, cmdStatus);
		}
		return cmdSuccessStatus.equals(retryableCommand.getStatus()) ? retryableCommand : null;

	}

	public void setRetryableCommandFunction(RetryableCommandFunction<CMD> retryableCommandFunction) {
		this.retryableCommandFunction = retryableCommandFunction;
	}

	public void setCmdSuccessStatus(String cmdSuccessStatus) {
		this.cmdSuccessStatus = cmdSuccessStatus;
	}

	public void setCmdFailureStatus(String cmdFailureStatus) {
		this.cmdFailureStatus = cmdFailureStatus;
	}

}
