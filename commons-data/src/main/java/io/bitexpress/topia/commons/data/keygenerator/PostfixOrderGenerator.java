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

import jodd.bean.BeanUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PostfixOrderGenerator extends StringSequenceGenerator {

	public static final int DEFAULT_SEQUENCE_LENGTH = 9;

	private String postfixProperty;

	private int sequenceLength = DEFAULT_SEQUENCE_LENGTH;

	@Override
	public void configure(GeneratorCreationContext creationContext, Properties params) throws MappingException {
		super.configure(creationContext, params);
		postfixProperty = params.getProperty("postfixProperty");
	}

	private FastDateFormat DATE_FORMAT = FastDateFormat.getInstance("yyyyMMdd");

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		String postfix = StringUtils.EMPTY;
		if (StringUtils.isNotBlank(postfixProperty)) {
			Object property = BeanUtil.pojo.getProperty(object, postfixProperty);
			if (property != null) {
				postfix = "-" + String.valueOf(property);
			}
		}

		String id = (String) super.generate(session, object);
		String datePattern = DATE_FORMAT.format(new Date());
		id = datePattern + StringUtils.leftPad(id, sequenceLength, '0') + postfix;
		log.trace("generated id:{}", id);
		return id;
	}

}
