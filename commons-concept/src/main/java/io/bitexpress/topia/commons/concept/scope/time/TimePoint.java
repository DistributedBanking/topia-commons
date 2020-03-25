package io.bitexpress.topia.commons.concept.scope.time;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * <p>时间点</p >
 *
 * @author shenyue
 */
@Data
@SuperBuilder
public class TimePoint {
    /**
     * 是否包含该点
     */
    private boolean inclusive;
    /**
     * 时间点
     */
    private Date time;
}
