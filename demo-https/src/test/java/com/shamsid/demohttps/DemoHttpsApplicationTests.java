package com.shamsid.demohttps;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.io.Resource;

import javax.net.ssl.SSLContext;
import java.net.http.HttpClient;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DemoHttpsApplicationTests {

	private String WELCOME_URL = "https://Shamshers-MacBook-Pro.local:8443/ssl-url";

	@Value("${trust.store}")
	private Resource trustStore;

	@Value("${trust.store.password}")
	private String trustStorePassword;

	RestTemplate restTemplate() throws Exception {

		SSLConnectionSocketFactory scsf = new SSLConnectionSocketFactory(
				SSLContexts.custom().loadTrustMaterial(null, new TrustSelfSignedStrategy()).build(),
				NoopHostnameVerifier.INSTANCE);

		/*SSLContext sslContext = new SSLContextBuilder()
				.loadTrustMaterial(trustStore.getURL(), trustStorePassword.toCharArray())
				.build();
		SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext);*/
		CloseableHttpClient httpClient = HttpClients.custom()
				.setSSLSocketFactory(scsf)
				.build();
		HttpComponentsClientHttpRequestFactory factory =
				new HttpComponentsClientHttpRequestFactory(httpClient);
		return new RestTemplate(factory);
	}

	@Test
	void contextLoads() throws Exception {
		ResponseEntity<String> response = restTemplate().getForEntity(WELCOME_URL, String.class, Collections.emptyMap());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("SSL connection is working",response.getBody());
	}
}
