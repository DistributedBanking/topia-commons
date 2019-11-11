package io.bitexpress.topia.commons.basic.dubbo;

import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Activate(group = CommonConstants.CONSUMER)
public class DubboConsumerLoggerFilter implements Filter {
	public static final int DEFAULT_MAX_PAY_LOAD_LENGTH = 2000;
	private Logger logger = LoggerFactory.getLogger(DubboConsumerLoggerFilter.class);

	private int maxPayLoadLength = DEFAULT_MAX_PAY_LOAD_LENGTH;

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		try {
			Result result = invoker.invoke(invocation);
			if (logger.isTraceEnabled()) {
				logger.trace("invocation:{}, result:{}",
						StringUtils.abbreviate(String.valueOf(invocation), maxPayLoadLength),
						StringUtils.abbreviate(String.valueOf(result.getValue()), maxPayLoadLength));
			}
			return result;
		} catch (RuntimeException e) {
			if (logger.isTraceEnabled()) {
				logger.trace("exception:{}", StringUtils.abbreviate(e.getMessage(), maxPayLoadLength * 2));
			}
			throw e;
		}

	}

	public void setLogger(String loggerName) {
		this.logger = LoggerFactory.getLogger(loggerName);
	}

}
