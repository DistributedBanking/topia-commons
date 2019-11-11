package io.bitexpress.topia.commons.rpc;

import org.testng.annotations.Test;

public class BaseResponseTest {

	@Test
	public void tt() {
		BaseResponse br = new BaseResponse();
		br.setBusinessCode("ff");
		br.setMessage("mm");
		br.setSystemCode(SystemCode.SUCCESS);
		br.setTrace("tt");
		System.out.println(br.toString());
	}
}
