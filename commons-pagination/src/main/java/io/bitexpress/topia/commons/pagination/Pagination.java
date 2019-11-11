package io.bitexpress.topia.commons.pagination;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 分页
 * 
 * @author shenyue
 *
 * @param <T>
 */
public class Pagination<T> implements Serializable {
	/**
	 * 总条目数
	 */
	@Min(0)
	private long totalElements;
	/**
	 * 总分页数
	 */
	@Min(0)
	private long totalPages;
	/**
	 * 分页参数
	 */
	@NotNull
	@Valid
	private SortPageParam sortPageParam;

	/**
	 * 当前分页条目
	 */
	@NotNull
	private List<T> items;

	public Pagination() {
	}

	public Pagination(@Min(0) long totalElements, @Min(0) long totalPages, @NotNull @Valid SortPageParam sortPageParam,
			@NotNull List<T> items) {
		this.totalElements = totalElements;
		this.totalPages = totalPages;
		this.sortPageParam = sortPageParam;
		this.items = items;
	}

	public <U> Pagination<U> map(Function<? super T, ? extends U> converter) {
		Pagination<U> pagination = new Pagination<>();
		List<U> convertedContent = getConvertedContent(converter);
		pagination.setItems(convertedContent);
		pagination.setTotalElements(getTotalElements());
		pagination.setTotalPages(getTotalPages());
		pagination.setSortPageParam(getSortPageParam());
		return pagination;
	}

	/**
	 * Applies the given {@link Function} to the content of the {@link Chunk}.
	 *
	 * @param converter must not be {@literal null}.
	 * @return
	 */
	protected <U> List<U> getConvertedContent(Function<? super T, ? extends U> converter) {
		if (items == null) {
			return null;
		}
		return items.stream().map(converter::apply).collect(Collectors.toList());
	}

	public long getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(long totalPages) {
		this.totalPages = totalPages;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	public SortPageParam getSortPageParam() {
		return sortPageParam;
	}

	public void setSortPageParam(SortPageParam sortPageParam) {
		this.sortPageParam = sortPageParam;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("sortPageParam", this.sortPageParam)
				.append("totalPages", this.totalPages).append("items", this.items)
				.append("totalElements", this.totalElements).toString();
	}

}
