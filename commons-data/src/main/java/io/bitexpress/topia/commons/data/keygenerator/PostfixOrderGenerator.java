package io.bitexpress.topia.commons.data.keygenerator;

import java.io.Serializable;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jodd.bean.BeanUtil;

public class PostfixOrderGenerator extends StringSequenceGenerator {

	private static final Logger logger = LoggerFactory.getLogger(PostfixOrderGenerator.class);

	public static final int DEFAULT_SEQUENCE_LENGTH = 9;

	private String postfixProperty;

	private int sequenceLength = DEFAULT_SEQUENCE_LENGTH;

	@Override
	public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
		super.configure(type, params, serviceRegistry);
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
		logger.trace("generated id:{}", id);
		return id;
	}

}
