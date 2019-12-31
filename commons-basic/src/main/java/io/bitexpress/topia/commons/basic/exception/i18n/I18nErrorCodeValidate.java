package io.bitexpress.topia.commons.basic.exception.i18n;

import io.bitexpress.topia.commons.basic.exception.CustomeValidate;
import io.bitexpress.topia.commons.rpc.error.ErrorCode;
import io.bitexpress.topia.commons.rpc.error.i18n.I18nErrorCode;
import io.bitexpress.topia.commons.rpc.error.i18n.I18nMessage;

import java.util.function.Supplier;

/**
 * <p></p >
 *
 * @author shenyue
 */
public class I18nErrorCodeValidate {
    private I18nErrorCodeValidate() {
    }

    public static <T> T notNull(T object, I18nErrorCode errorCode, Object... params) {
        I18nMessage i18nMessage = I18nMessage.builder().key(errorCode.getMessageKey()).params(params).build();
        return notNull(object, errorCode, i18nMessage);
    }


    public static void isTrue(boolean condition, I18nErrorCode errorCode, String messageKey, Object... params) {
        I18nMessage i18nMessage = I18nMessage.builder().key(messageKey).params(params).build();
        isTrue(condition, errorCode, i18nMessage);
    }

    public static void isTrue(boolean condition, I18nErrorCode errorCode, Object... params) {
        CustomeValidate.isTrue(condition, errorCode.getCode(), errorCode.getTemplate(), params);
    }

    public static void isTrue(boolean condition, Supplier<I18nErrorCode> errorCode, Object... params) {
        ErrorCode errorCode2 = errorCode.get();
        CustomeValidate.isTrue(condition, errorCode2.getCode(), errorCode2.getTemplate(), params);
    }


    public static <T> T fail(I18nErrorCode errorCode, Object... params) {
        I18nMessage i18nMessage = I18nMessage.builder().key(errorCode.getMessageKey()).params(params).build();
        return I18nCustomeValidate.fail(errorCode.getCode(), errorCode.getTemplate(), i18nMessage);
    }
}
