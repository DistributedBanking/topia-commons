package io.bitexpress.topia.commons.data.page;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import io.bitexpress.topia.commons.pagination.SortParam;

public class SortUtils {
	private SortUtils() {
	}

	public static Sort convert(SortParam... sortParams) {
		if (ArrayUtils.isEmpty(sortParams)) {
			return Sort.unsorted();
		}
		List<Order> orders = new ArrayList<>();
		for (SortParam sortParam : sortParams) {
			orders.add(new Order(Direction.valueOf(sortParam.getDirection().name()), sortParam.getProperty()));
		}
		return Sort.by(orders);
	}

	public static List<SortParam> convert(Sort sort) {
		if (sort == null || sort.isUnsorted()) {
			return null;
		}
		List<SortParam> sortParamList = new ArrayList<>();

		for (Order order : sort) {
			io.bitexpress.topia.commons.pagination.Direction valueOf = io.bitexpress.topia.commons.pagination.Direction
					.valueOf(order.getDirection().name());
			sortParamList.add(new SortParam(valueOf, order.getProperty()));
		}
		return sortParamList;
	}
}
