package io.bitexpress.topia.commons.basic.rpc.utils;

import io.bitexpress.topia.commons.basic.exception.ErrorCodeException;
import io.bitexpress.topia.commons.rpc.BusinessCode;
import io.bitexpress.topia.commons.rpc.SystemCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BaseResponseUtilsTest {

    @Test
    public void systemError() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setSystemCode(SystemCode.FAILURE);
        baseResponse.setMessage("aaa");
        try {
            BaseResponseUtils.parse(baseResponse);
            Assertions.fail();
        } catch (IllegalStateException e) {
            assertEquals(e.getMessage(), "aaa");
        }
    }

    @Test
    public void businessError() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setSystemCode(SystemCode.SUCCESS);
        baseResponse.setBusinessCode("fff");
        baseResponse.setMessage("aaa");
        try {
            BaseResponseUtils.parse(baseResponse);
            Assertions.fail();
        } catch (ErrorCodeException e) {
            assertEquals(e.getErrorCode(), "fff");
            assertEquals(e.getMessage(), "aaa");
        }
    }

    @Test
    public void ok() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setSystemCode(SystemCode.SUCCESS);
        baseResponse.setBusinessCode(BusinessCode.SUCCESS.name());
        baseResponse.setMessage("aaa");
        BaseResponseUtils.parse(baseResponse);
    }

    @Test
    public void businessSlient() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setSystemCode(SystemCode.SUCCESS);
        baseResponse.setBusinessCode("fff");
        baseResponse.setMessage("aaa");
        BaseResponseUtils.parse(baseResponse, "fff");

    }
}
