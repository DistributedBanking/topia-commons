package io.bitexpress.topia.commons.concept.scope;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;


/**
 * @author godmonth
 * @deprecated  use {@link io.bitexpress.topia.commons.concept.scope.time.TimeScope}
 */
@Deprecated
public class DateScope {
    private Date from;

    private Date to;

    public DateScope(Date from, Date to) {
        super();
        this.from = from;
        this.to = to;
    }

    public DateScope() {
        super();
    }

    public static DateScope fromScope(Scope<Date> input) {
        return new DateScope(input.getFrom(), input.getTo());
    }

    public Scope<Date> toScope() {
        return new Scope<>(from, to);
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("from", this.from).append("to", this.to)
                .toString();
    }

}
