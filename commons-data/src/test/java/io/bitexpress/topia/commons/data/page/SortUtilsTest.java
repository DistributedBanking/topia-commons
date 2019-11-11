package io.bitexpress.topia.commons.data.page;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.testng.annotations.Test;

import io.bitexpress.topia.commons.pagination.Direction;
import io.bitexpress.topia.commons.pagination.SortParam;

public class SortUtilsTest {

	@Test
	public void round() {
		SortParam sortParam = new SortParam();
		sortParam.setDirection(Direction.ASC);
		sortParam.setProperty("qqqq");
		Sort convert = SortUtils.convert(sortParam);
		List<SortParam> convert2 = SortUtils.convert(convert);
		assertTrue(convert2.get(0).equals(sortParam));
	}
}
