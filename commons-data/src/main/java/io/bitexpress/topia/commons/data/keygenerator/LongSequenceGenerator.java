package io.bitexpress.topia.commons.data.keygenerator;

import java.io.Serializable;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.time.FastDateFormat;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.boot.model.relational.Database;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.PersistentIdentifierGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;

public class LongSequenceGenerator implements PersistentIdentifierGenerator, Configurable {
	public static final int DEFAULT_SEQUENCE_LENGTH = 9;

	private SequenceStyleGenerator sequenceStyleGenerator;

	private int sequenceLength = DEFAULT_SEQUENCE_LENGTH;

	private long baseNumber = (long) Math.pow(10, sequenceLength);

	@Override
	public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
		sequenceStyleGenerator = new SequenceStyleGenerator();
		sequenceStyleGenerator.configure(new LongType(), params, serviceRegistry);
	}

	@Override
	public void registerExportables(Database database) {
		sequenceStyleGenerator.registerExportables(database);
	}

	private FastDateFormat DATE_FORMAT = FastDateFormat.getInstance("yyyyMMdd");

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		Number generate = (Number) sequenceStyleGenerator.generate(session, object);
		long longValue = generate.longValue() % baseNumber;
		return getPrefix() + longValue;
	}

	private Long getPrefix() {
		String datePattern = DATE_FORMAT.format(new Date());
		long parseLong = Long.parseLong(datePattern);
		return parseLong * baseNumber;
	}

	@Override
	public String[] sqlCreateStrings(Dialect dialect) throws HibernateException {
		return null;
	}

	@Override
	public String[] sqlDropStrings(Dialect dialect) throws HibernateException {
		return null;
	}

	@Override
	public Object generatorKey() {
		return sequenceStyleGenerator.generatorKey();
	}

}
