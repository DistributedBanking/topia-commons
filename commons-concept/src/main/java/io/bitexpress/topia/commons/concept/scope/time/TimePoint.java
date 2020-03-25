package io.bitexpress.topia.commons.concept.scope.time;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
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
     * 时间点包含特性
     */
    @NotNull
    private PointInclusion inclusion;

    /**
     * 时间点
     */
    @NotNull
    private Date time;
}
