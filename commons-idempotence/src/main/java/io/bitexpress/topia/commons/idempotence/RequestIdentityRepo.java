package io.bitexpress.topia.commons.idempotence;

import io.bitexpress.topia.commons.data.model.IdObject;
import io.bitexpress.topia.commons.rpc.RequestIdentity;

public interface RequestIdentityRepo<RI extends IdObject<Long>> {
	RI findByRi(RequestIdentity requestIdentity);

	RI save(RequestIdentity requestIdentity, String parentType);
}
