package io.bitexpress.topia.commons.data.page;


import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;

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
        Assertions.assertTrue(convert2.get(0).equals(sortParam));
    }
}
