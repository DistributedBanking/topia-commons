package io.bitexpress.topia.commons.data.model;

public interface StatusEnabled<S> {
	S getStatus();

	void setStatus(S s);
}
