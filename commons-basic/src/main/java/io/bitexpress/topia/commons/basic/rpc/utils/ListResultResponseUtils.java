package io.bitexpress.topia.commons.basic.rpc.utils;

import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.bitexpress.topia.commons.basic.exception.ErrorCodeException;
import io.bitexpress.topia.commons.rpc.BusinessCode;
import io.bitexpress.topia.commons.rpc.ListResultResponse;
import io.bitexpress.topia.commons.rpc.SystemCode;

/**
 * 包含处理结果列表的响应组件
 * 
 * @author shenyue
 *
 */
@Deprecated
public class ListResultResponseUtils<T> {
	private static final Logger logger = LoggerFactory.getLogger(ListResultResponseUtils.class);

	public static <T> ListResultResponse<T> codeListResultResponse(List<T> resultList, SystemCode systemCode,
			String businessCode, String message) {
		ListResultResponse<T> rr = new ListResultResponse<T>();
		rr.setSystemCode(systemCode);
		rr.setMessage(message);
		rr.setBusinessCode(businessCode);
		rr.setResultList(resultList);
		return rr;
	}

	public static <T> ListResultResponse<T> successListResultResponse(List<T> resultList) {
		return codeListResultResponse(resultList, SystemCode.SUCCESS, BusinessCode.SUCCESS.name(), null);
	}

	public static <T> ListResultResponse<T> failureResultResponse(String message) {
		return codeListResultResponse(null, SystemCode.FAILURE, null, message);
	}

	public static <T> ListResultResponse<T> errorCodeExceptionListResultResponse(ErrorCodeException e) {
		logger.info("code:{},message:{}", e.getErrorCode(), e.getMessage());
		ListResultResponse<T> rr = new ListResultResponse<T>();
		rr.setSystemCode(SystemCode.SUCCESS);
		rr.setBusinessCode(e.getErrorCode());
		rr.setMessage(e.getMessage());
		return rr;
	}

	public static <T> ListResultResponse<T> exceptionListResultResponse(Throwable throwable) {
		return exceptionListResultResponse(throwable, false);
	}

	public static <T> ListResultResponse<T> exceptionListResultResponse(Throwable throwable, boolean enableTrace) {
		if (throwable instanceof ErrorCodeException) {
			return errorCodeExceptionListResultResponse((ErrorCodeException) throwable);
		}
		logger.error("", throwable);
		ListResultResponse<T> rr = new ListResultResponse<T>();
		rr.setSystemCode(SystemCode.FAILURE);
		rr.setMessage(throwable.getMessage());
		rr.setTrace(ExceptionUtils.getStackTrace(throwable));
		return rr;
	}

	public static <T> List<T> parse(ListResultResponse<T> listResultResponse, String... silentBusinessCodes) {
		BaseResponseUtils.parse(listResultResponse, silentBusinessCodes);
		return listResultResponse.getResultList();
	}

}
