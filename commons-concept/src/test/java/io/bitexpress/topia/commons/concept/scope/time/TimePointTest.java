package io.bitexpress.topia.commons.concept.scope.time;

import org.junit.jupiter.api.Test;

import java.util.Date;

/**
 * <p></p >
 *
 * @author shenyue
 */
class TimePointTest {
    @Test
    void name() {
        TimePoint build = TimePoint.builder().inclusive(true).time(new Date()).build();
        System.out.println(build);
    }
}