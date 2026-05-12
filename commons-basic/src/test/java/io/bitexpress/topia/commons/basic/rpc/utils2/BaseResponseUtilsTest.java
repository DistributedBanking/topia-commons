package io.bitexpress.topia.commons.basic.rpc.utils2;

import io.bitexpress.topia.commons.basic.exception.ErrorCodeException;
import io.bitexpress.topia.commons.rpc.SystemCode;
import io.bitexpress.topia.commons.rpc.response.BaseResponse;
import io.bitexpress.topia.commons.rpc.response.ResponseHeader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BaseResponseUtilsTest {

	@Test
	public void systemError() {
		BaseResponse baseResponse = BaseResponseUtils.failureBaseResponse("aaa");
		try {
			BaseResponseUtils.parse(baseResponse);
			Assertions.fail();
		} catch (IllegalStateException e) {
			assertEquals("aaa", e.getMessage());
		}
	}

	@Test
	public void businessError() {
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setHeader(ResponseHeader.builder()
				.systemCode(SystemCode.SUCCESS)
				.businessCode("fff")
				.message("aaa")
				.build());
		try {
			BaseResponseUtils.parse(baseResponse);
			Assertions.fail();
		} catch (ErrorCodeException e) {
			assertEquals("fff", e.getErrorCode());
			assertEquals("aaa", e.getMessage());
		}
	}

	@Test
	public void ok() {
		BaseResponse baseResponse = BaseResponseUtils.successBaseResponse("aaa");
		BaseResponseUtils.parse(baseResponse);
	}

	@Test
	public void businessSilent() {
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setHeader(ResponseHeader.builder()
				.systemCode(SystemCode.SUCCESS)
				.businessCode("fff")
				.message("aaa")
				.build());
		BaseResponseUtils.parse(baseResponse, "fff");
	}
}
