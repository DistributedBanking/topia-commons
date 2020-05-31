package io.bitexpress.topia.commons.concept.scope.time;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;

/**
 * <p>时间范围.</p >
 * 为什么不直接使用internval或者Range&lt;Date>，因为两边都可空，并且要加上是开闭区间的标志
 *
 * @author shenyue
 */
@Data
@SuperBuilder
@NoArgsConstructor()
@AllArgsConstructor
@ToString(callSuper = true)
public class TimeScope {
    /**
     * 开始时间点
     */
    @Valid
    private TimePoint from;

    /**
     * 结束时间点
     */
    @Valid
    private TimePoint to;

}
