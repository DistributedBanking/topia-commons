package io.bitexpress.topia.commons.data.keygenerator;

import java.io.Serializable;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.boot.model.relational.Database;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.PersistentIdentifierGenerator;
import org.hibernate.generator.GeneratorCreationContext;
import org.hibernate.id.enhanced.Optimizer;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

public class StringSequenceGenerator implements PersistentIdentifierGenerator, Configurable {

	private SequenceStyleGenerator sequenceStyleGenerator;

	@Override
	public void configure(GeneratorCreationContext creationContext, Properties params) throws MappingException {
		sequenceStyleGenerator = new SequenceStyleGenerator();
		sequenceStyleGenerator.configure(creationContext, params);
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
	public Optimizer getOptimizer() {
		return sequenceStyleGenerator.getOptimizer();
	}

}
