package io.bitexpress.topia.commons.data.lock;

import java.io.Serializable;

import io.bitexpress.topia.commons.data.model.MutableModel;

public interface LockOrder<ID extends Serializable> {
	void lock(Class<? extends MutableModel> entityClass, ID id, long dataVersion);

}
