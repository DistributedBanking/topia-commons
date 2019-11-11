package io.bitexpress.topia.commons.basic.json.converter;

import java.io.IOException;

import org.joda.money.CurrencyUnit;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CurrencyUnitDeserializer extends JsonDeserializer<CurrencyUnit> {

	@Override
	public CurrencyUnit deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
		return CurrencyUnit.of(jp.readValueAs(String.class));
	}

	@Override
	public Class<CurrencyUnit> handledType() {
		return CurrencyUnit.class;
	}

}
