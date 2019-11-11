package io.bitexpress.topia.commons.basic.replenishment;

import java.util.Date;

import org.apache.commons.lang3.Range;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;

public class ReplenishmentRange {
	public static final Period DEFAULT_START_PERIOD = Period.days(30);

	public static final Period DEFAULT_END_PERIOD = Period.minutes(5);

	private Period startPeriod = DEFAULT_START_PERIOD;

	private Period endPeriod = DEFAULT_END_PERIOD;

	public void setStartPeriod(Period startPeriod) {
		this.startPeriod = startPeriod;
	}

	public void setEndPeriod(Period endPeriod) {
		this.endPeriod = endPeriod;
	}

	public Interval getInterval() {
		DateTime startTime = new DateTime().minus(startPeriod);
		DateTime endTime = new DateTime().minus(endPeriod);
		return new Interval(startTime, endTime);
	}

	public Range<Date> getRange() {
		Interval interval = getInterval();
		return Range.between(interval.getStart().toDate(), interval.getEnd().toDate());
	}

}
