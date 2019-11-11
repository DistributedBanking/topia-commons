package io.bitexpress.topia.commons.data.rpc;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import io.bitexpress.topia.commons.data.page.PageUtils;
import io.bitexpress.topia.commons.data.page.SortUtils;
import io.bitexpress.topia.commons.pagination.Pagination;
import io.bitexpress.topia.commons.pagination.SortPageParam;

import jodd.bean.BeanCopy;

public class PageTransformer {

	public static <T> Pagination<T> createPagination(List<T> pageContent, int totalElements, SortPageParam spp) {
		Pagination<T> pagination = new Pagination<>();
		pagination.setSortPageParam(spp);
		pagination.setTotalElements(totalElements);
		int pageSize = spp.getSize();
		pagination.setTotalPages(totalElements / pageSize + (totalElements % pageSize > 0 ? 1 : 0));
		pagination.setItems(pageContent);
		return pagination;
	}

	public static <T> Pagination<T> extractPageFromFullList(List<T> rawList, SortPageParam spp) {
		int totalElements = rawList.size();
		int totalPages = totalElements / spp.getSize() + totalElements % spp.getSize() > 0 ? 1 : 0;
		int fromIndex = Math.min(totalElements, spp.getNumber() * spp.getSize());
		int toIndex = Math.min(totalElements, fromIndex + spp.getSize());
		List<T> subList = rawList.subList(fromIndex, toIndex);
		Pagination<T> pagination = new Pagination<>();
		pagination.setSortPageParam(spp);
		pagination.setTotalElements(totalElements);
		pagination.setTotalPages(totalPages);
		pagination.setItems(subList);
		return pagination;
	}

	/**
	 * 不合适的名字.
	 * 
	 * @deprecated use extractPageFromFullList
	 * @param rawList
	 * @param spp
	 * @return
	 */
	@Deprecated
	public static <T> Pagination<T> list2Page(List<T> rawList, SortPageParam spp) {
		return extractPageFromFullList(rawList, spp);
	}

	public static <IN, OUT> Pagination<OUT> transform(Pagination<IN> page, Function<IN, OUT> function) {
		if (page == null) {
			return null;
		}
		Pagination<OUT> pagination = new Pagination<>();
		BeanCopy.beans(page, pagination).exclude("items").copy();
		pagination.setItems(Lists.transform(page.getItems(), function));
		return pagination;
	}

	public static <IN, OUT> Pagination<OUT> transform(Page<IN> page, Function<IN, OUT> function) {
		if (page == null) {
			return null;
		}
		Pagination<OUT> pagination = new Pagination<OUT>();
		pagination.setTotalElements(page.getTotalElements());
		pagination.setTotalPages(page.getTotalPages());
		pagination.setItems(Lists.transform(page.getContent(), function));
		pagination.setSortPageParam(
				new SortPageParam(page.getNumber(), page.getSize(), SortUtils.convert(page.getSort())));
		return pagination;
	}

	public static <T> Pagination<T> transform(Page<T> page) {
		if (page == null) {
			return null;
		}
		Pagination<T> pagination = new Pagination<T>();
		pagination.setTotalElements(page.getTotalElements());
		pagination.setTotalPages(page.getTotalPages());
		pagination.setItems(page.getContent());
		pagination.setSortPageParam(
				new SortPageParam(page.getNumber(), page.getSize(), SortUtils.convert(page.getSort())));
		return pagination;
	}

	public static <T> Page<T> transform(Pagination<T> pagination) {
		if (pagination == null) {
			return null;
		}
		PageRequest pageRequest = PageUtils.convert(pagination.getSortPageParam());
		return new PageImpl<T>(pagination.getItems(), pageRequest, pagination.getTotalElements());
	}
}
