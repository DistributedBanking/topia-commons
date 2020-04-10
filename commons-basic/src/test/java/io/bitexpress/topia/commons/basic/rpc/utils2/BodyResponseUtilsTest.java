package io.bitexpress.topia.commons.basic.rpc.utils2;

import io.bitexpress.topia.commons.basic.exception.i18n.I18nErrorCodeException;
import io.bitexpress.topia.commons.rpc.i18n.I18nMessage;
import io.bitexpress.topia.commons.rpc.response.BodyResponse;
import org.junit.jupiter.api.Test;

import java.io.Serializable;

/**
 * <p></p >
 *
 * @author shenyue
 */
class BodyResponseUtilsTest {
    @Test
    void i18nError() {
        I18nMessage aaafff = I18nMessage.builder().key("aaafff").build();
        I18nErrorCodeException i18nErrorCodeException = new I18nErrorCodeException("ddd", "eee", aaafff);

        BodyResponse<Serializable> serializableBodyResponse = BodyResponseUtils.exceptionBodyResponse(i18nErrorCodeException);
        System.out.println(serializableBodyResponse);
    }
}