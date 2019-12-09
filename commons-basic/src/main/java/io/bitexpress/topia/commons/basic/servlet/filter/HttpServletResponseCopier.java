package io.bitexpress.topia.commons.basic.servlet.filter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.OutputStream;

public class HttpServletResponseCopier extends HttpServletResponseWrapper {

	private OutputStream outputStream;

	public HttpServletResponseCopier(HttpServletResponse response, OutputStream outputStream) throws IOException {
		super(response);
		this.outputStream = outputStream;
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		return new TeeServletOutputStream(super.getOutputStream(), outputStream);
	}

}
