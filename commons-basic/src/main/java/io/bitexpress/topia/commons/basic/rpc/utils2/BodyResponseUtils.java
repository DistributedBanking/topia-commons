package io.bitexpress.topia.commons.basic.rpc.utils2;

import io.bitexpress.topia.commons.basic.exception.ErrorCodeException;
import io.bitexpress.topia.commons.rpc.BusinessCode;
import io.bitexpress.topia.commons.rpc.SystemCode;
import io.bitexpress.topia.commons.rpc.response.BodyResponse;
import io.bitexpress.topia.commons.rpc.response.ResponseHeader;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 包含处理数据的响应组件
 *
 * @author shenyue
 */

public class BodyResponseUtils {
    private static final Logger logger = LoggerFactory.getLogger(BodyResponseUtils.class);

    public static <T> BodyResponse<T> codeBodyResponse(T result, SystemCode systemCode, String businessCode,
                                                       String message) {
        ResponseHeader responseHeader = ResponseHeader.builder().systemCode(systemCode).businessCode(businessCode).message(message).build();
        BodyResponse<T> rr = new BodyResponse<T>();
        rr.setHeader(responseHeader);
        rr.setBody(result);
        return rr;
    }

    public static <T> BodyResponse<T> successBodyResponse(T result) {
        return codeBodyResponse(result, SystemCode.SUCCESS, BusinessCode.SUCCESS.name(), null);
    }

    public static <T> BodyResponse<T> failureBodyResponse(String message) {
        return codeBodyResponse(null, SystemCode.FAILURE, null, message);
    }

    public static <T> BodyResponse<T> errorCodeExceptionBodyResponse(ErrorCodeException e) {
        logger.info("code:{},message:{}", e.getErrorCode(), e.getMessage());
        ResponseHeader responseHeader = ResponseHeader.builder().systemCode(SystemCode.SUCCESS).businessCode(e.getErrorCode()).message(e.getMessage()).build();
        BodyResponse<T> rr = new BodyResponse<T>();
        rr.setHeader(responseHeader);
        return rr;
    }

    public static <T> BodyResponse<T> exceptionBodyResponse(Throwable throwable) {
        return exceptionBodyResponse(throwable, false);
    }

    public static <T> BodyResponse<T> exceptionBodyResponse(Throwable throwable, boolean enableTrace) {
        if (throwable instanceof ErrorCodeException) {
            return errorCodeExceptionBodyResponse((ErrorCodeException) throwable);
        }
        BodyResponse bodyResponse = new BodyResponse();
        bodyResponse.setHeader(ResponseHeaderUtils.exceptionHeader(throwable, enableTrace));
        return bodyResponse;
    }

    public static <T> T parse(BodyResponse<T> resultResponse, String... silentBusinessCodes) {
        BaseResponseUtils.parse(resultResponse, silentBusinessCodes);
        return resultResponse.getBody();
    }

}
