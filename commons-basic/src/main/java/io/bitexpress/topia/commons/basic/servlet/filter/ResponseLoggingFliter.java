package io.bitexpress.topia.commons.basic.servlet.filter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ResponseLoggingFliter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(ResponseLoggingFliter.class);

	private RequestPathMatcherHelper excludeLogPathMatcherHelper;
	private Integer maxPayloadLength;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		if (shouldLog((HttpServletRequest) request)) {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			HttpServletResponseCopier responseCopier = new HttpServletResponseCopier((HttpServletResponse) response,
					byteArrayOutputStream);
			chain.doFilter(request, responseCopier);
			try {
				String string = new String(byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8);
				if (maxPayloadLength != null) {
					string = StringUtils.abbreviate(string, maxPayloadLength);
				}
				logger.trace("response body:{}", string);
			} catch (Exception e) {
				logger.error("", e);
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	protected boolean shouldLog(HttpServletRequest request) {
		return logger.isTraceEnabled() && !excludeLogPathMatcherHelper.match(request);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

	@Override
	public void destroy() {
	}

	public void setExcludeLogPathMatcherHelper(RequestPathMatcherHelper excludeLogPathMatcherHelper) {
		this.excludeLogPathMatcherHelper = excludeLogPathMatcherHelper;
	}

	public void setMaxPayloadLength(Integer maxPayloadLength) {
		this.maxPayloadLength = maxPayloadLength;
	}

}