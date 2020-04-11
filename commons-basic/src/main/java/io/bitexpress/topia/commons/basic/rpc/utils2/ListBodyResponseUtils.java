package io.bitexpress.topia.commons.basic.rpc.utils2;

import io.bitexpress.topia.commons.rpc.BusinessCode;
import io.bitexpress.topia.commons.rpc.SystemCode;
import io.bitexpress.topia.commons.rpc.response.ListBodyResponse;
import io.bitexpress.topia.commons.rpc.response.ResponseHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;

/**
 * 包含处理结果列表的响应组件
 *
 * @author shenyue
 */
public class ListBodyResponseUtils<T> {
    private static final Logger logger = LoggerFactory.getLogger(ListBodyResponseUtils.class);

    public static <T extends Serializable> ListBodyResponse<T> codeListResultResponse(List<T> bodyList, SystemCode systemCode, String businessCode, String message) {
        ResponseHeader responseHeader = ResponseHeader.builder().systemCode(systemCode).businessCode(businessCode).message(message).build();
        return ListBodyResponse.<T>listBodyBuilder().header(responseHeader).body(bodyList).build();
    }

    public static <T extends Serializable> ListBodyResponse<T> successListBodyResponse(List<T> bodyList) {
        return codeListResultResponse(bodyList, SystemCode.SUCCESS, BusinessCode.SUCCESS.name(), null);
    }

    public static <T extends Serializable> ListBodyResponse<T> failureBodyResponse(String message) {
        return codeListResultResponse(null, SystemCode.FAILURE, null, message);
    }

    public static <T extends Serializable> ListBodyResponse<T> exceptionListBodyResponse(Throwable throwable) {
        return exceptionListBodyResponse(throwable, false, null);
    }

    public static <T extends Serializable> ListBodyResponse<T> exceptionListBodyResponse(Throwable throwable, boolean enableTrace, Function<Throwable, ResponseHeader.ResponseHeaderBuilder> customizedExceptionHeader) {
        return ListBodyResponse.<T>listBodyBuilder().header(ResponseHeaderUtils.exceptionHeader(throwable, enableTrace, customizedExceptionHeader)).build();
    }

    public static <T extends Serializable> List<T> parse(ListBodyResponse<T> listResultResponse, String... silentBusinessCodes) {
        BaseResponseUtils.parse(listResultResponse, silentBusinessCodes);
        return listResultResponse.getBody();
    }

}
