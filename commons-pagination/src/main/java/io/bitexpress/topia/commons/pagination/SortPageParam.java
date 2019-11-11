package io.bitexpress.topia.commons.pagination;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 分页参数
 * 
 * @author shenyue
 */
public class SortPageParam extends PageParam {

	/**
	 * 排序
	 */
	@Valid
	private List<SortParam> sortParamList;

	public SortPageParam() {
	}

	/**
	 * 
	 * @param number
	 * @param size
	 */
	public SortPageParam(int number, int size) {
		super(number, size);
	}

	public SortPageParam(PageParam pageParam, SortParam sortParam) {
		this(pageParam.getNumber(), pageParam.getSize(), sortParam);
	}

	public SortPageParam(int number, int size, SortParam sortParam) {
		super(number, size);
		List<SortParam> list = new ArrayList<SortParam>();
		list.add(sortParam);
		this.sortParamList = list;
	}

	public SortPageParam(int number, int size, List<SortParam> sortParamList) {
		super(number, size);
		this.sortParamList = sortParamList;
	}

	public List<SortParam> getSortParamList() {
		return sortParamList;
	}

	public void setSortParamList(List<SortParam> sortParamList) {
		this.sortParamList = sortParamList;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).appendSuper(super.toString())
				.append("sortParamList", this.sortParamList).toString();
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof SortPageParam)) {
			return false;
		}
		SortPageParam rhs = (SortPageParam) object;
		return new EqualsBuilder().appendSuper(super.equals(object)).append(this.sortParamList, rhs.sortParamList)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-1143119417, 1396470783).appendSuper(super.hashCode()).append(this.sortParamList)
				.toHashCode();
	}

}
