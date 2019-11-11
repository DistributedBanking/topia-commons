package io.bitexpress.topia.commons.idempotence;

import java.util.function.Supplier;

import io.bitexpress.topia.commons.data.model.IdObject;

public class IdempotenceParam<REQ, RI extends IdObject<Long>> {
	private Supplier<RI> requestIdentitySupplier;
	private REQ request;

	public IdempotenceParam(Supplier<RI> requestIdentitySupplier, REQ request) {
		this.requestIdentitySupplier = requestIdentitySupplier;
		this.request = request;
	}

	public Supplier<RI> getRequestIdentitySupplier() {
		return requestIdentitySupplier;
	}

	public REQ getRequest() {
		return request;
	}

}
