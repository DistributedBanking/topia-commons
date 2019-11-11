package io.bitexpress.topia.commons.data.keygenerator;

import java.io.Serializable;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.time.FastDateFormat;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.boot.model.relational.Database;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.PersistentIdentifierGenerator;
import org.hibernate.id.enhanced.TableGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;

/**
 * 最大值9223372036854775807.之后将转为负数,程序将抛出异常
 * 
 * @author shenyue
 *
 */
public class TimeLongTableGenerator implements PersistentIdentifierGenerator, Configurable {
	public static final int DEFAULT_SEQUENCE_LENGTH = 9;

	private TableGenerator tableGenerator;

	private int sequenceLength = DEFAULT_SEQUENCE_LENGTH;

	private long baseNumber = (long) Math.pow(10, sequenceLength);

	@Override
	public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
		tableGenerator = new TableGenerator();
		tableGenerator.configure(new LongType(), params, serviceRegistry);
	}

	@Override
	public void registerExportables(Database database) {
		tableGenerator.registerExportables(database);
	}

	private FastDateFormat DATE_FORMAT = FastDateFormat.getInstance("yyyyMMdd");

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		Number generate = (Number) tableGenerator.generate(session, object);
		long longValue = generate.longValue();
		Validate.isTrue(longValue >= 0);
		longValue = longValue % baseNumber;
		return getPrefix() + longValue;
	}

	private Long getPrefix() {
		String datePattern = DATE_FORMAT.format(new Date());
		long parseLong = Long.parseLong(datePattern);
		return parseLong * baseNumber;
	}

	@Override
	public String[] sqlCreateStrings(Dialect dialect) throws HibernateException {
		return tableGenerator.sqlCreateStrings(dialect);
	}

	@Override
	public String[] sqlDropStrings(Dialect dialect) throws HibernateException {
		return tableGenerator.sqlDropStrings(dialect);
	}

	@Override
	public Object generatorKey() {
		return tableGenerator.generatorKey();
	}

}