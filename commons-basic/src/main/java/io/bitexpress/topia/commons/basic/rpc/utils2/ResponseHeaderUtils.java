package io.bitexpress.topia.commons.basic.rpc.utils2;

import io.bitexpress.topia.commons.basic.exception.ErrorCodeException;
import io.bitexpress.topia.commons.basic.exception.i18n.I18nErrorCodeException;
import io.bitexpress.topia.commons.rpc.SystemCode;
import io.bitexpress.topia.commons.rpc.response.ResponseHeader;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p></p >
 *
 * @author shenyue
 */
public class ResponseHeaderUtils {
    private static final Logger logger = LoggerFactory.getLogger(ResponseHeaderUtils.class);

    private ResponseHeaderUtils() {
    }

    public static ResponseHeader exceptionHeader(Throwable e, boolean enableTrace) {
        ResponseHeader.ResponseHeaderBuilder responseHeaderBuilder = null;
        if (e instanceof I18nErrorCodeException) {
            responseHeaderBuilder = i18nErrorCodeExceptionResponseHeaderBuilder((I18nErrorCodeException) e);
        } else if (e instanceof ErrorCodeException) {
            responseHeaderBuilder = errorCodeExceptionResponseHeaderBuilder((ErrorCodeException) e);
        }
        if (enableTrace) {
            responseHeaderBuilder = responseHeaderBuilder.trace(ExceptionUtils.getStackTrace(e));
        }
        return responseHeaderBuilder.build();
    }


    private static ResponseHeader.ResponseHeaderBuilder i18nErrorCodeExceptionResponseHeaderBuilder(I18nErrorCodeException e) {
        logger.trace("code:{},message:{},i18nMessage:{}", e.getErrorCode(), e.getMessage(), e.getI18nMessage());
        return ResponseHeader.builder().systemCode(SystemCode.SUCCESS).businessCode(e.getErrorCode()).message(e.getMessage()).i18nMessage(e.getI18nMessage());
    }

    private static ResponseHeader.ResponseHeaderBuilder errorCodeExceptionResponseHeaderBuilder(ErrorCodeException e) {
        logger.trace("code:{},message:{}", e.getErrorCode(), e.getMessage());
        return ResponseHeader.builder().systemCode(SystemCode.SUCCESS).businessCode(e.getErrorCode()).message(e.getMessage());
    }
}
