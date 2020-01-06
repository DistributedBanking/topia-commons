package io.bitexpress.topia.commons.basic.exception.i18n;

import io.bitexpress.topia.commons.rpc.error.i18n.I18nErrorCode;
import io.bitexpress.topia.commons.rpc.i18n.I18nMessage;

import java.io.Serializable;
import java.util.function.Supplier;

/**
 * <p></p >
 *
 * @author shenyue
 */
public class I18nErrorCodeValidate {
    private I18nErrorCodeValidate() {
    }

    public static <T> T notNull(T object, I18nErrorCode errorCode, I18nMessage i18nMessage) {
        return I18nCustomeValidate.notNull(object, errorCode.getCode(), errorCode.getTemplate(), i18nMessage);
    }

    public static <T> T notNull(T object, I18nErrorCode errorCode, Serializable... params) {
        I18nMessage i18nMessage = I18nMessage.builder().key(errorCode.getMessageKey()).params(params).build();
        return notNull(object, errorCode, i18nMessage);
    }

    public static void isTrue(boolean condition, I18nErrorCode errorCode, I18nMessage i18nMessage) {
        I18nCustomeValidate.isTrue(condition, errorCode.getCode(), errorCode.getTemplate(), i18nMessage);
    }

    @Deprecated
    public static void isTrue(boolean condition, I18nErrorCode errorCode, String messageKey, Serializable... params) {
        I18nMessage i18nMessage = I18nMessage.builder().key(messageKey).params(params).build();
        isTrue(condition, errorCode, i18nMessage);
    }

    public static void isTrue(boolean condition, I18nErrorCode errorCode, Serializable... params) {
        I18nMessage i18nMessage = I18nMessage.builder().key(errorCode.getMessageKey()).params(params).build();
        isTrue(condition, errorCode, i18nMessage);
    }

    @Deprecated
    public static void isTrue(boolean condition, Supplier<I18nErrorCode> errorCode, Serializable... params) {
        I18nErrorCode errorCode2 = errorCode.get();
        isTrue(condition, errorCode2, params);
    }


    public static <T> T fail(I18nErrorCode errorCode, Serializable... params) {
        I18nMessage i18nMessage = I18nMessage.builder().key(errorCode.getMessageKey()).params(params).build();
        return fail(errorCode, i18nMessage);
    }

    public static <T> T fail(I18nErrorCode errorCode, I18nMessage i18nMessage) {
        return I18nCustomeValidate.fail(errorCode.getCode(), errorCode.getTemplate(), i18nMessage);
    }
}
