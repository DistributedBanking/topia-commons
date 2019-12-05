package io.bitexpress.topia.commons.rpc.response;

import io.bitexpress.topia.commons.rpc.SystemCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p></p >
 *
 * @author shenyue
 */
class BodyResponseTest {

    @Test
    void testToString() {
        ResponseHeader responseHeader = ResponseHeader.builder().systemCode(SystemCode.FAILURE).businessCode("sss").build();
        BodyResponse response = BodyResponse.bodyBuilder().header(responseHeader).body("fff").build();
        System.out.println(response);
    }
}