package io.bitexpress.topia.commons.basic.json.converter;

import java.math.BigDecimal;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MoneySerializerTest {

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
	public void deserialize() throws JsonProcessingException {
		MoneyModel model = new MoneyModel();
		model.setMoney(Money.of(CurrencyUnit.USD, new BigDecimal("0.01")));
		String writeValueAsString = objectMapper.writeValueAsString(model);
		System.out.println(writeValueAsString);
	}
}
