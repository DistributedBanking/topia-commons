package io.bitexpress.topia.commons.basic.exception;

public class ErrorCodeException extends RuntimeException {
    private String errorCode;
    /**
     * 永久拒绝.表示该请求(用requestIdentity标识)被永久拒绝
     */
    private boolean permanentRejection;

    public ErrorCodeException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCodeException(String errorCode, String message, boolean permanentRejection) {
        super(message);
        this.errorCode = errorCode;
        this.permanentRejection = permanentRejection;
    }

    public String getErrorCode() {
        return errorCode;
    }

}
