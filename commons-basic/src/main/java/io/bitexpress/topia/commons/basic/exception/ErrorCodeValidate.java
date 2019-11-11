package io.bitexpress.topia.commons.basic.exception;

import java.util.function.Supplier;

import io.bitexpress.topia.commons.rpc.error.ErrorCode;

public class ErrorCodeValidate {
	private ErrorCodeValidate() {
	}

	public static <T> T notNull(T object, ErrorCode errorCode, final Object... values) {
		CustomeValidate.notNull(object, errorCode.getCode(), errorCode.getTemplate(), values);
		return object;
	}

	public static void isTrue(boolean condition, ErrorCode errorCode, final Object... values) {
		CustomeValidate.isTrue(condition, errorCode.getCode(), errorCode.getTemplate(), values);
	}

	public static void isTrue(boolean condition, Supplier<ErrorCode> errorCode, final Object... values) {
		ErrorCode errorCode2 = errorCode.get();
		CustomeValidate.isTrue(condition, errorCode2.getCode(), errorCode2.getTemplate(), values);
	}

	public static void fail(ErrorCode errorCode, final Object... values) {
		CustomeValidate.fail(errorCode.getCode(), errorCode.getTemplate(), values);
	}
}
