package io.bitexpress.topia.commons.basic.rpc.utils;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.bitexpress.topia.commons.basic.exception.ErrorCodeException;
import io.bitexpress.topia.commons.rpc.BusinessCode;
import io.bitexpress.topia.commons.rpc.ResultResponse;
import io.bitexpress.topia.commons.rpc.SystemCode;

/**
 * 包含处理数据的响应组件
 * 
 * @author shenyue
 *
 */
public class ResultResponseUtils {
	private static final Logger logger = LoggerFactory.getLogger(ResultResponseUtils.class);

	public static <T> ResultResponse<T> codeResultResponse(T result, SystemCode systemCode, String businessCode,
			String message) {
		ResultResponse<T> rr = new ResultResponse<T>();
		rr.setSystemCode(systemCode);
		rr.setBusinessCode(businessCode);
		rr.setMessage(message);
		rr.setResult(result);
		return rr;
	}

	public static <T> ResultResponse<T> successResultResponse(T result) {
		return codeResultResponse(result, SystemCode.SUCCESS, BusinessCode.SUCCESS.name(), null);
	}

	public static <T> ResultResponse<T> failureResultResponse(String message) {
		return codeResultResponse(null, SystemCode.FAILURE, null, message);
	}

	public static <T> ResultResponse<T> errorCodeExceptionResultResponse(ErrorCodeException e) {
		logger.info("code:{},message:{}", e.getErrorCode(), e.getMessage());
		ResultResponse<T> rr = new ResultResponse<T>();
		rr.setSystemCode(SystemCode.SUCCESS);
		rr.setBusinessCode(e.getErrorCode());
		rr.setMessage(e.getMessage());
		return rr;
	}

	public static <T> ResultResponse<T> exceptionResultResponse(Throwable throwable) {
		return exceptionResultResponse(throwable, false);
	}

	public static <T> ResultResponse<T> exceptionResultResponse(Throwable throwable, boolean enableTrace) {
		if (throwable instanceof ErrorCodeException) {
			return errorCodeExceptionResultResponse((ErrorCodeException) throwable);
		}
		logger.error("", throwable);
		ResultResponse<T> rr = new ResultResponse<T>();
		rr.setSystemCode(SystemCode.FAILURE);
		rr.setMessage(throwable.getMessage());
		if (enableTrace) {
			rr.setTrace(ExceptionUtils.getStackTrace(throwable));
		}
		return rr;
	}

	public static <T> T parse(ResultResponse<T> resultResponse, String... silentBusinessCodes) {
		BaseResponseUtils.parse(resultResponse, silentBusinessCodes);
		return resultResponse.getResult();
	}

}
