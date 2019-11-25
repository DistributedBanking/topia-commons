package io.bitexpress.topia.commons.basic.rpc.utils2;

import io.bitexpress.topia.commons.basic.exception.ErrorCodeException;
import io.bitexpress.topia.commons.rpc.BusinessCode;
import io.bitexpress.topia.commons.rpc.SystemCode;
import io.bitexpress.topia.commons.rpc.response.BodyResponse;
import io.bitexpress.topia.commons.rpc.response.ListBodyResponse;
import io.bitexpress.topia.commons.rpc.response.ResponseHeader;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 包含处理结果列表的响应组件
 *
 * @author shenyue
 */
public class ListBodyResponseUtils<T> {
    private static final Logger logger = LoggerFactory.getLogger(ListBodyResponseUtils.class);

    public static <T> ListBodyResponse<T> codeListResultResponse(List<T> bodyList, SystemCode systemCode,
                                                                 String businessCode, String message) {
        ResponseHeader responseHeader = ResponseHeader.builder().systemCode(systemCode).businessCode(businessCode).message(message).build();
        ListBodyResponse<T> rr = new ListBodyResponse<T>();
        rr.setHeader(responseHeader);
        rr.setBody(bodyList);
        return rr;
    }

    public static <T> ListBodyResponse<T> successListResultResponse(List<T> resultList) {
        return codeListResultResponse(resultList, SystemCode.SUCCESS, BusinessCode.SUCCESS.name(), null);
    }

    public static <T> ListBodyResponse<T> failureResultResponse(String message) {
        return codeListResultResponse(null, SystemCode.FAILURE, null, message);
    }

    public static <T> ListBodyResponse<T> errorCodeExceptionListResultResponse(ErrorCodeException e) {
        logger.info("code:{},message:{}", e.getErrorCode(), e.getMessage());
        ResponseHeader responseHeader = ResponseHeader.builder().systemCode(SystemCode.SUCCESS).businessCode(e.getErrorCode()).message(e.getMessage()).build();
        ListBodyResponse<T> rr = new ListBodyResponse<T>();
        rr.setHeader(responseHeader);
        return rr;
    }

    public static <T> ListBodyResponse<T> exceptionListResultResponse(Throwable throwable) {
        return exceptionListResultResponse(throwable, false);
    }

    public static <T> ListBodyResponse<T> exceptionListResultResponse(Throwable throwable, boolean enableTrace) {
        if (throwable instanceof ErrorCodeException) {
            return errorCodeExceptionListResultResponse((ErrorCodeException) throwable);
        }
        ListBodyResponse<T> rr = new ListBodyResponse<T>();
        rr.setHeader(ResponseHeaderUtils.exceptionHeader(throwable, enableTrace));
        return rr;
    }

    public static <T> List<T> parse(ListBodyResponse<T> listResultResponse, String... silentBusinessCodes) {
        BaseResponseUtils.parse(listResultResponse, silentBusinessCodes);
        return listResultResponse.getBody();
    }

}
