package io.bitexpress.topia.commons.basic.cxf;

import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.jaxb.JAXBDataBinding;
import org.apache.cxf.phase.Phase;

public class TraceHeaderInterceptor extends AbstractSoapInterceptor {

	public TraceHeaderInterceptor() {
		super(Phase.PRE_PROTOCOL);
	}

	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		List<Header> list = message.getHeaders();
		Header dummyHeader = null;
		try {
			dummyHeader = new Header(new QName("uri:io.bitexpress.topia.commons.basic.cxf", "trace"),
					RandomStringUtils.randomAlphanumeric(10), new JAXBDataBinding(String.class));
		} catch (JAXBException e) {
			throw new ContextedRuntimeException(e);
		}
		list.add(dummyHeader);
		message.put(Header.HEADER_LIST, list);
	}

}
