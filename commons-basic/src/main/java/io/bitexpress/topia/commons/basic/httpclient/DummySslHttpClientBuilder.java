package io.bitexpress.topia.commons.basic.httpclient;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;

public class DummySslHttpClientBuilder {

	private String proxyAddress;

	private String proxyUsername;

	private String proxyPassword;

	public CloseableHttpClient build() throws KeyStoreException, NoSuchAlgorithmException, CertificateException,
			IOException, KeyManagementException, UnrecoverableKeyException {
		HttpClientBuilder httpClientBuilder = HttpClients.custom();
		SSLContext sslcontext = DummySslContext.INSTANCE;

		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, null, null,
				NoopHostnameVerifier.INSTANCE);
		httpClientBuilder = httpClientBuilder.setSSLSocketFactory(sslsf);

		if (StringUtils.isNotBlank(proxyAddress)) {
			HttpHost httpHost = HttpHost.create(proxyAddress);
			httpClientBuilder = httpClientBuilder.setRoutePlanner(new DefaultProxyRoutePlanner(httpHost));
			if (StringUtils.isNotBlank(proxyUsername)) {
				AuthScope authScope = new AuthScope(httpHost.getHostName(), httpHost.getPort());
				Credentials credentials = new UsernamePasswordCredentials(proxyUsername, proxyPassword);
				CredentialsProvider credsProvider = new BasicCredentialsProvider();
				credsProvider.setCredentials(authScope, credentials);
				httpClientBuilder.setDefaultCredentialsProvider(credsProvider).build();
			}
		}
		return httpClientBuilder.build();
	}

	public void setProxyAddress(String proxyAddress) {
		this.proxyAddress = proxyAddress;
	}

	public void setProxyUsername(String proxyUsername) {
		this.proxyUsername = proxyUsername;
	}

	public void setProxyPassword(String proxyPassword) {
		this.proxyPassword = proxyPassword;
	}

}
