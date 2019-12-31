package io.bitexpress.topia.commons.basic.exception.i18n;

import io.bitexpress.topia.commons.basic.exception.ErrorCodeException;
import io.bitexpress.topia.commons.rpc.error.i18n.I18nMessage;
import lombok.Getter;

/**
 * <p></p >
 *
 * @author shenyue
 */
public class I18nErrorCodeException extends ErrorCodeException {
    @Getter
    private I18nMessage i18nMessage;

    public I18nErrorCodeException(String errorCode, String message, I18nMessage i18nMessage) {
        super(errorCode, message);
        this.i18nMessage=i18nMessage;
    }

}
