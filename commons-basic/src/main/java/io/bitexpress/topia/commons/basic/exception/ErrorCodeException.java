package io.bitexpress.topia.commons.basic.exception;

public class ErrorCodeException extends RuntimeException {
	private String errorCode;

	public ErrorCodeException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

}
