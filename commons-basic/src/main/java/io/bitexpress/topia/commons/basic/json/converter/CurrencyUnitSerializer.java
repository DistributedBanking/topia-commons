package io.bitexpress.topia.commons.basic.json.converter;

import java.io.IOException;

import org.joda.money.CurrencyUnit;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
@Deprecated
public class CurrencyUnitSerializer extends JsonSerializer<CurrencyUnit> {

	@Override
	public void serialize(CurrencyUnit value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		if (value != null) {
			gen.writeString(value.getCode());
		}
	}

	@Override
	public Class<CurrencyUnit> handledType() {
		return CurrencyUnit.class;
	}

}
