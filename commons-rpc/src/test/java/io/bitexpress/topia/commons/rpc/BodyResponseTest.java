package io.bitexpress.topia.commons.rpc;

import io.bitexpress.topia.commons.rpc.response.BodyResponse;
import io.bitexpress.topia.commons.rpc.response.ResponseHeader;
import org.junit.jupiter.api.Test;

public class BodyResponseTest {
	@Test
	public void tt() {
		BodyResponse<byte[]> br = new BodyResponse<>();
		br.setHeader(ResponseHeader.builder()
				.systemCode(SystemCode.SUCCESS)
				.businessCode("ff")
				.message("mm")
				.trace("tt")
				.build());
		br.setBody(new byte[] { 1, 2, 3 });
		System.out.println(br.toString());
	}
}
