package io.bitexpress.topia.commons.pagination;

import javax.validation.Valid;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 分页参数
 * 
 * @author shenyue
 */
public class OneSortPageParam extends PageParam {

	/**
	 * 排序
	 */
	@Valid
	private SortParam sortParam;

	public OneSortPageParam() {
	}

	public SortParam getSortParam() {
		return sortParam;
	}

	public void setSortParam(SortParam sortParam) {
		this.sortParam = sortParam;
	}

	/**
	 * 
	 * @param number
	 * @param size
	 */
	public OneSortPageParam(int number, int size) {
		super(number, size);
	}

	public OneSortPageParam(int number, int size, SortParam sortParam) {
		super(number, size);
		this.sortParam = sortParam;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof OneSortPageParam)) {
			return false;
		}
		OneSortPageParam rhs = (OneSortPageParam) object;
		return new EqualsBuilder().appendSuper(super.equals(object)).append(this.sortParam, rhs.sortParam).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(333294985, -31508475).appendSuper(super.hashCode()).append(this.sortParam)
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("number", this.getNumber()).append("size", this.getSize()).toString();
	}

}
