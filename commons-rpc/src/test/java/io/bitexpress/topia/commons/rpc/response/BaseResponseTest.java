package io.bitexpress.topia.commons.rpc.response;

import io.bitexpress.topia.commons.rpc.SystemCode;
import org.junit.jupiter.api.Test;

/**
 * <p></p >
 *
 * @author shenyue
 */
class BaseResponseTest {


    @Test
    void testToString() {
        ResponseHeader responseHeader = ResponseHeader.builder().systemCode(SystemCode.FAILURE).businessCode("sss").build();
        BaseResponse response = BaseResponse.builder().header(responseHeader).build();
        System.out.println(response);
    }
}