package io.bitexpress.topia.commons.data.rpc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.testng.annotations.Test;

import io.bitexpress.topia.commons.pagination.Pagination;
import io.bitexpress.topia.commons.pagination.SortPageParam;

public class PageTransformerTest {

	@Test
	public void transform() {
		PageImpl<String> s = new PageImpl<>(Arrays.asList("a", "b"));
		System.out.println(s);
		Pagination<String> pagination = PageTransformer.transform(s);
		System.out.println(pagination);
	}

	@Test
	public void createPagination() {
		List<String> pageContent = new ArrayList<>();
		SortPageParam spp = new SortPageParam();
		spp.setNumber(0);
		spp.setSize(2);

		Pagination<String> pagination = PageTransformer.createPagination(pageContent, 3, spp);
		System.out.println(pagination);
	}
}
