package io.bitexpress.topia.commons.basic.replenishment;

import org.joda.time.Interval;
import org.testng.annotations.Test;

public class ReplenishmentRangeTest {

	@Test
	public void getInterval() {
		ReplenishmentRange range = new ReplenishmentRange();
		Interval interval = range.getInterval();
		System.out.println(interval);
		System.out.println(range.getRange());
	}
}
