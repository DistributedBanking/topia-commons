package io.bitexpress.topia.commons.rpc.error;

public class ErrorCodeImpl implements ErrorCode {

	private String code;
	private String Template;

	public ErrorCodeImpl(String code, String template) {
		this.code = code;
		Template = template;
	}

	public String getTemplate() {
		return Template;
	}

	public String getCode() {
		return code;
	}

}
