package io.bitexpress.topia.commons.data.lock;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.bitexpress.topia.commons.data.model.MutableModel;

public class OracleLock<ID extends Serializable> implements LockOrder<String> {

	private static final Logger logger = LoggerFactory
			.getLogger(OracleLock.class);

	@PersistenceContext
	private EntityManager entityManager;

	private int timeout = 5;
	private String idColumn = "id";

	private String lockSqlTemplate = "select %s from %s where %s = ? and data_version = ? for update wait %d";

	public void lock(Class<? extends MutableModel> entityClass, String id,
			long dataVersion) {
		Table declaredAnnotation = entityClass.getAnnotation(Table.class);
		String sql = String.format(lockSqlTemplate, idColumn,
				declaredAnnotation.name(), idColumn, timeout);
		logger.trace("lockSql:{}", sql);

		Query nativeQuery = entityManager.createNativeQuery(sql);
		nativeQuery.setParameter(1, id);
		nativeQuery.setParameter(2, dataVersion);
		nativeQuery.getSingleResult();
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

}
