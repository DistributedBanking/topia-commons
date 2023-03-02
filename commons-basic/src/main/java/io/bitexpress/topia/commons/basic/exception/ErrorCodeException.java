package io.bitexpress.topia.commons.basic.exception;

import lombok.Getter;

public class ErrorCodeException extends RuntimeException {
    @Getter
    private String errorCode;
    /**
     * 永久拒绝.表示该请求(用requestIdentity标识)被永久拒绝
     */
    @Getter
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


}
