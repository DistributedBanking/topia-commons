package io.bitexpress.topia.commons.basic.rpc.utils;

import java.util.List;

import io.bitexpress.topia.commons.pagination.Pagination;
import io.bitexpress.topia.commons.pagination.SortPageParam;

/**
 * 
 * @deprecated use io.bitexpress.topia.commons.data.rpc.PageTransformer
 * 
 * @author shwh1
 *
 */
@Deprecated
public class PaginationUtils {

	public static <T> Pagination<T> list2Page(List<T> rawList, SortPageParam spp) {
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
}
