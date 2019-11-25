package io.bitexpress.topia.commons.basic.rpc.utils2;

import io.bitexpress.topia.commons.basic.exception.ErrorCodeException;
import io.bitexpress.topia.commons.rpc.SystemCode;
import io.bitexpress.topia.commons.rpc.response.ListBodyResponse;
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

    public static ResponseHeader exceptionHeader(Throwable throwable, boolean enableTrace) {
        logger.error("", throwable);
        ResponseHeader.ResponseHeaderBuilder responseHeaderBuilder = ResponseHeader.builder().systemCode(SystemCode.FAILURE).message(throwable.getMessage());
        if (enableTrace) {
            responseHeaderBuilder = responseHeaderBuilder.trace(ExceptionUtils.getStackTrace(throwable));
        }
        return responseHeaderBuilder.build();
    }
}
