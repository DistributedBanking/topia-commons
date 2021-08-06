package io.bitexpress.topia.commons.basic.cxf;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TraceHeaderInterceptorTest {

    @Test
    public void handleMessage() {
        TraceHeaderInterceptor headerInterceptor = new TraceHeaderInterceptor();
        SoapMessage sm = Mockito.mock(SoapMessage.class);
        List<Header> headers = new ArrayList<>();
        Mockito.when(sm.getHeaders()).thenReturn(headers);
        headerInterceptor.handleMessage(sm);
        Assertions.assertNotNull(headers.get(0).getObject());
        System.out.println(headers.get(0).getObject());
    }
}
