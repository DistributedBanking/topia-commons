package io.bitexpress.topia.commons.basic.httpclient;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class DummySslHttpClientBuilderTest {

    @Test
    @Disabled
    public void test() throws KeyManagementException, UnrecoverableKeyException, KeyStoreException,
            NoSuchAlgorithmException, CertificateException, IOException {
        DummySslHttpClientBuilder builder = new DummySslHttpClientBuilder();
        CloseableHttpClient httpClient = builder.build();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
        RestTemplate restTemplate = new RestTemplate(factory);
        String postForObject = restTemplate.postForObject("https://vipm.heihezi.vip/bitpay/back.php", null,
                String.class);
        System.out.println(postForObject);
    }
}
