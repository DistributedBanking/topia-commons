package io.bitexpress.topia.commons.basic.rpc.utils2;

import io.bitexpress.topia.commons.basic.exception.ErrorCodeException;
import io.bitexpress.topia.commons.basic.exception.i18n.I18nErrorCodeException;
import io.bitexpress.topia.commons.rpc.BusinessCode;
import io.bitexpress.topia.commons.rpc.SystemCode;
import io.bitexpress.topia.commons.rpc.response.BaseResponse;
import io.bitexpress.topia.commons.rpc.response.ResponseHeader;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

/**
 * 基础响应组件
 *
 * @author shenyue
 */
public class BaseResponseUtils {

    private static final Logger logger = LoggerFactory.getLogger(BaseResponseUtils.class);

    public static BaseResponse successBaseResponse() {
        return successBaseResponse(null);
    }

    public static BaseResponse successBaseResponse(String message) {
        return codeBaseResponse(SystemCode.SUCCESS, BusinessCode.SUCCESS.name(), message);
    }

    public static BaseResponse failureBaseResponse(String message) {
        return codeBaseResponse(SystemCode.FAILURE, null, message);
    }

    public static BaseResponse codeBaseResponse(SystemCode systemCode, String businessCode, String message) {
        ResponseHeader responseHeader = ResponseHeader.builder().systemCode(systemCode).businessCode(businessCode).message(message).build();
        return BaseResponse.builder().header(responseHeader).build();
    }

    public static BaseResponse exceptionBaseResponse(Throwable throwable) {
        return exceptionBaseResponse(throwable, false, null);
    }

    public static BaseResponse exceptionBaseResponse(Throwable throwable, boolean enableTrace, Function<Throwable, ResponseHeader.ResponseHeaderBuilder> customizedExceptionHeader) {
        return BaseResponse.builder().header(ResponseHeaderUtils.exceptionHeader(throwable, enableTrace, customizedExceptionHeader)).build();
    }

    public static void parse(BaseResponse baseResponse, String... silentBusinessCodes) {
        ResponseHeader header = baseResponse.getHeader();
        Validate.validState(header.getSystemCode() == SystemCode.SUCCESS, header.getMessage());
        String[] successCodeArray = ArrayUtils.add(silentBusinessCodes, BusinessCode.SUCCESS.name());
        if (!ArrayUtils.contains(successCodeArray, header.getBusinessCode())) {
            if (header.getI18nMessage() != null) {
                throw new I18nErrorCodeException(header.getBusinessCode(), header.getMessage(), header.getI18nMessage());
            } else {
                throw new ErrorCodeException(header.getBusinessCode(), header.getMessage());
            }

        }
    }

}
