package io.bitexpress.topia.commons.basic.rpc.utils;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.bitexpress.topia.commons.basic.exception.ErrorCodeException;
import io.bitexpress.topia.commons.rpc.BaseResponse;
import io.bitexpress.topia.commons.rpc.BusinessCode;
import io.bitexpress.topia.commons.rpc.SystemCode;

public class BaseResponseUtilsTest {

	@Test
	public void systemError() {
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setSystemCode(SystemCode.FAILURE);
		baseResponse.setMessage("aaa");
		try {
			BaseResponseUtils.parse(baseResponse);
			Assert.fail();
		} catch (IllegalStateException e) {
			assertEquals(e.getMessage(), "aaa");
		}
	}

	@Test
	public void businessError() {
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setSystemCode(SystemCode.SUCCESS);
		baseResponse.setBusinessCode("fff");
		baseResponse.setMessage("aaa");
		try {
			BaseResponseUtils.parse(baseResponse);
			Assert.fail();
		} catch (ErrorCodeException e) {
			assertEquals(e.getErrorCode(), "fff");
			assertEquals(e.getMessage(), "aaa");
		}
	}

	@Test
	public void ok() {
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setSystemCode(SystemCode.SUCCESS);
		baseResponse.setBusinessCode(BusinessCode.SUCCESS.name());
		baseResponse.setMessage("aaa");
		BaseResponseUtils.parse(baseResponse);
	}

	@Test
	public void businessSlient() {
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setSystemCode(SystemCode.SUCCESS);
		baseResponse.setBusinessCode("fff");
		baseResponse.setMessage("aaa");
		BaseResponseUtils.parse(baseResponse, "fff");

	}
}
