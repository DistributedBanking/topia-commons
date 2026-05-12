package io.bitexpress.topia.commons.data.keygenerator;

import java.io.Serializable;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.time.FastDateFormat;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.boot.model.relational.Database;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.PersistentIdentifierGenerator;
import org.hibernate.generator.GeneratorCreationContext;
import org.hibernate.id.enhanced.Optimizer;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

public class LongSequenceGenerator implements PersistentIdentifierGenerator, Configurable {
	public static final int DEFAULT_SEQUENCE_LENGTH = 9;

	private SequenceStyleGenerator sequenceStyleGenerator;

	private int sequenceLength = DEFAULT_SEQUENCE_LENGTH;

	private long baseNumber = (long) Math.pow(10, sequenceLength);

	@Override
	public void configure(GeneratorCreationContext creationContext, Properties params) throws MappingException {
		sequenceStyleGenerator = new SequenceStyleGenerator();
		sequenceStyleGenerator.configure(creationContext, params);
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
	public Optimizer getOptimizer() {
		return sequenceStyleGenerator.getOptimizer();
	}

}
