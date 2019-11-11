package io.bitexpress.topia.commons.concept.scope;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Scope<T> {
	private T from;

	private T to;

	public Scope() {
	}

	public Scope(T from, T to) {
		this.from = from;
		this.to = to;
	}

	public T getFrom() {
		return from;
	}

	public void setFrom(T from) {
		this.from = from;
	}

	public T getTo() {
		return to;
	}

	public void setTo(T to) {
		this.to = to;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("to", this.to).append("from", this.from).toString();
	}

}
