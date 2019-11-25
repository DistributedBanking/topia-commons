package io.bitexpress.topia.commons.basic.rpc.utils;

import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.bitexpress.topia.commons.basic.exception.ErrorCodeException;
import io.bitexpress.topia.commons.rpc.BusinessCode;
import io.bitexpress.topia.commons.rpc.MapResultResponse;
import io.bitexpress.topia.commons.rpc.SystemCode;

/**
 * 包含处理数据的响应组件
 * 
 * @author shenyue
 *
 */
@Deprecated
public class MapResultResponseUtils {
	private static final Logger logger = LoggerFactory.getLogger(MapResultResponseUtils.class);

	public static <K, V> MapResultResponse<K, V> codeMapResultResponse(Map<K, V> result, SystemCode systemCode,
			String businessCode, String message) {
		MapResultResponse<K, V> rr = new MapResultResponse<K, V>();
		rr.setSystemCode(systemCode);
		rr.setBusinessCode(businessCode);
		rr.setMessage(message);
		rr.setResult(result);
		return rr;
	}

	public static <K, V> MapResultResponse<K, V> successMapResultResponse(Map<K, V> result) {
		return codeMapResultResponse(result, SystemCode.SUCCESS, BusinessCode.SUCCESS.name(), null);
	}

	public static <K, V> MapResultResponse<K, V> failureMapResultResponse(String message) {
		return codeMapResultResponse(null, SystemCode.FAILURE, null, message);
	}

	public static <K, V> MapResultResponse<K, V> errorCodeExceptionMapResultResponse(ErrorCodeException e) {
		logger.info("code:{},message:{}", e.getErrorCode(), e.getMessage());
		MapResultResponse<K, V> rr = new MapResultResponse<K, V>();
		rr.setSystemCode(SystemCode.SUCCESS);
		rr.setBusinessCode(e.getErrorCode());
		rr.setMessage(e.getMessage());
		return rr;
	}

	public static <K, V> MapResultResponse<K, V> exceptionMapResultResponse(Throwable throwable) {
		return exceptionMapResultResponse(throwable, false);
	}

	public static <K, V> MapResultResponse<K, V> exceptionMapResultResponse(Throwable throwable, boolean enableTrace) {
		if (throwable instanceof ErrorCodeException) {
			return errorCodeExceptionMapResultResponse((ErrorCodeException) throwable);
		}
		logger.error("", throwable);
		MapResultResponse<K, V> rr = new MapResultResponse<K, V>();
		rr.setSystemCode(SystemCode.FAILURE);
		rr.setMessage(throwable.getMessage());
		rr.setTrace(ExceptionUtils.getStackTrace(throwable));
		return rr;
	}

}
