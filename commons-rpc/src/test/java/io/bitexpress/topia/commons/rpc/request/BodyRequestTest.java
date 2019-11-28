package io.bitexpress.topia.commons.rpc.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p></p >
 *
 * @author shenyue
 */
class BodyRequestTest {

    @Test
    void bodyRequestBuilder() {
        BodyRequest<String> bodyRequest = BodyRequest.<String>bodyRequestBuilder().body("ddd`").build();
        System.out.println(bodyRequest.getBody());
    }
}