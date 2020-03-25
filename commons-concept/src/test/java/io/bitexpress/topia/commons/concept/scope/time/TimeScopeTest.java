package io.bitexpress.topia.commons.concept.scope.time;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;

import java.util.Date;

/**
 * <p></p >
 *
 * @author shenyue
 */
class TimeScopeTest {
    @Test
    void name() {
        TimePoint startTime = TimePoint.builder().inclusive(true).time(DateUtils.addDays(new Date(), -1)).build();
        TimePoint endTime = TimePoint.builder().inclusive(false).time(DateUtils.addDays(new Date(), 0)).build();

        TimeScope timeScope = TimeScope.builder().startTime(startTime).endTime(endTime).build();
        System.out.println(timeScope);
    }
}