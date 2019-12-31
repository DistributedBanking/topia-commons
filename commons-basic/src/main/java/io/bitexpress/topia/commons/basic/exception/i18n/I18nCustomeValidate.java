package io.bitexpress.topia.commons.basic.exception.i18n;


import io.bitexpress.topia.commons.rpc.error.i18n.I18nMessage;

/**
 * <p></p >
 *
 * @author shenyue
 */
public class I18nCustomeValidate {
    private I18nCustomeValidate() {
    }

    public static void notNull(Object object, String errorCode, String message, I18nMessage i18nMessage) {
        if (object == null) {
            fail(errorCode, message, i18nMessage);
        }
    }

    public static void isTrue(boolean condition, String errorCode, String message, I18nMessage i18nMessage) {
        if (!condition) {
            fail(errorCode, message, i18nMessage);
        }
    }

    public static <T> T fail(String errorCode, String message, I18nMessage i18nMessage) {
        throw new I18nErrorCodeException(errorCode, message, i18nMessage);
    }
}
