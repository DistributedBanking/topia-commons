package io.bitexpress.topia.commons.rpc.error;

public class I18nErrorCodeImpl extends ErrorCodeImpl implements I18nErrorCode {

    private String messageKey;

    public I18nErrorCodeImpl(String code, String template, String messageKey) {
        super(code, template);
        this.messageKey = messageKey;
    }

    @Override
    public String getMessageKey() {
        return messageKey;
    }

    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }
}
