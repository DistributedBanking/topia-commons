package io.bitexpress.topia.commons.basic.replenishment;

import org.joda.time.Interval;
import org.junit.jupiter.api.Test;

public class ReplenishmentRangeTest {

    @Test
    public void getInterval() {
        ReplenishmentRange range = new ReplenishmentRange();
        Interval interval = range.getInterval();
        System.out.println(interval);
        System.out.println(range.getRange());
    }

    @Test
    public void testBuilder() {
		ReplenishmentRange replenishmentRange = new ReplenishmentRange();
		System.out.println(replenishmentRange);
		System.out.println(replenishmentRange.getDateRange());
	}
}
