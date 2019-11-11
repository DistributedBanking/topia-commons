package io.bitexpress.topia.commons.data.lock;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.Validate;

import io.bitexpress.topia.commons.data.model.MutableModel;

public class JpaLock implements LockOrder<String> {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void lock(Class<? extends MutableModel> entityClass, String id,
			long dataVersion) {
		MutableModel mutableModel = entityManager.find(entityClass, id);
		Validate.notNull(mutableModel);
		Validate.isTrue(mutableModel.getDataVersion() == dataVersion);
		entityManager.lock(mutableModel, LockModeType.PESSIMISTIC_WRITE);
	}

}
