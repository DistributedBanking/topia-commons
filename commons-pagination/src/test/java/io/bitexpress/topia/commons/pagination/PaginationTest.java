package io.bitexpress.topia.commons.pagination;

import static org.testng.Assert.assertEquals;

import java.util.Arrays;

import org.testng.annotations.Test;

public class PaginationTest {

	@Test
	public void map() {
		Pagination<String> p = new Pagination<>(10, 10, new SortPageParam(5, 5), Arrays.asList("1", "2", "3"));
		System.out.println(p);
		Pagination<Integer> map = p.map(Integer::parseInt);
		System.out.println(map);
		assertEquals(map.getItems(), Arrays.asList(1, 2, 3));
	}
}
