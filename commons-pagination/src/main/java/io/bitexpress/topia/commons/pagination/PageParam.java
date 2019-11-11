package io.bitexpress.topia.commons.pagination;

import java.io.Serializable;

import javax.validation.constraints.Min;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 分页参数
 * 
 * @author shenyue
 *
 */
public class PageParam implements Serializable {

	/**
	 * 分页号
	 */
	@Min(0)
	private int number;
	/**
	 * 页尺寸
	 */
	@Min(1)
	private int size;

	public PageParam() {
	}

	/**
	 * 
	 * @param number
	 * @param size
	 */
	public PageParam(int number, int size) {
		this.size = size;
		this.number = number;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof PageParam)) {
			return false;
		}
		PageParam rhs = (PageParam) object;
		return new EqualsBuilder().append(this.number, rhs.number).append(this.size, rhs.size).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(180786243, 1430630351).append(this.number).append(this.size).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("number", this.number)
				.append("size", this.size).toString();
	}

}
