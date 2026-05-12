package io.bitexpress.topia.commons.data.keygenerator;

import java.io.Serializable;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.generator.GeneratorCreationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderSequenceGenerator extends StringSequenceGenerator {

	public static final int DEFAULT_SEQUENCE_LENGTH = 9;
	private String prefix;

	private boolean date;

	private int sequenceLength = DEFAULT_SEQUENCE_LENGTH;

	@Override
	public void configure(GeneratorCreationContext creationContext, Properties params) throws MappingException {
		super.configure(creationContext, params);
		prefix = params.getProperty("prefix");
		date = Boolean.valueOf(params.getProperty("date", "true"));
	}

	private FastDateFormat DATE_FORMAT = FastDateFormat.getInstance("yyyyMMdd");

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		String id = (String) super.generate(session, object);
		String datePattern = date ? DATE_FORMAT.format(new Date()) : "";
		id = StringUtils.defaultString(prefix) + datePattern + StringUtils.leftPad(id, sequenceLength, '0');
		log.trace("generated id:{}", id);
		return id;
	}

}
