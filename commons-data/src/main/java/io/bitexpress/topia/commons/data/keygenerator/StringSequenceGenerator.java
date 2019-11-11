package io.bitexpress.topia.commons.data.keygenerator;

import java.io.Serializable;
import java.util.Properties;

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

public class StringSequenceGenerator implements PersistentIdentifierGenerator, Configurable {

	private SequenceStyleGenerator sequenceStyleGenerator;

	@Override
	public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
		sequenceStyleGenerator = new SequenceStyleGenerator();
		sequenceStyleGenerator.configure(new LongType(), params, serviceRegistry);
	}

	@Override
	public void registerExportables(Database database) {
		sequenceStyleGenerator.registerExportables(database);
	}

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		return String.valueOf(sequenceStyleGenerator.generate(session, object));

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
