package io.bitexpress.topia.commons.rpc;


import org.junit.jupiter.api.Test;

public class ResultResponseTest {
	@Test
	public void tt() {
		ResultResponse<byte[]> br = new ResultResponse<>();
		br.setBusinessCode("ff");
		br.setMessage("mm");
		br.setSystemCode(SystemCode.SUCCESS);
		br.setTrace("tt");
		br.setResult(new byte[] { 1, 2, 3 });
		System.out.println(br.toString());
	}
}
