package io.bitexpress.topia.commons.concept.scope.time;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * <p>时间点</p >
 *
 * @author shenyue
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TimePoint {
    /**
     * 是否包含该点.默认是不包含
     */
    private boolean inclusive;

    /**
     * 时间点
     */
    private Date time;
}
