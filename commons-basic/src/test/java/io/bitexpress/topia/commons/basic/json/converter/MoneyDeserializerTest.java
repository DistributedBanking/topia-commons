package io.bitexpress.topia.commons.basic.json.converter;

import java.io.IOException;

import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MoneyDeserializerTest {

	private ObjectMapper objectMapper;

	@BeforeClass
	private void prepare() {
		Jackson2ObjectMapperFactoryBean bean = new Jackson2ObjectMapperFactoryBean();
		bean.setSerializers(new MoneySerializer());
		bean.setDeserializers(new MoneyDeserializer());
		bean.afterPropertiesSet();
		objectMapper = bean.getObject();
	}

	@Test
	public void deserialize() throws IOException {
		String sampleJson = "{\"money\":\"USD 0.01\"}";
		MoneyModel readValue = objectMapper.readValue(sampleJson, MoneyModel.class);
		System.out.println(readValue);
	}

}
