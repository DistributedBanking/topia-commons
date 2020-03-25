package io.bitexpress.topia.commons.concept.scope.time;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * <p>时间范围.</p >
 * 为什么不直接使用internval或者Range&lt;Date>，因为两边都可空，并且要加上是否包含的标志
 *
 * @author shenyue
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TimeScope {

    private TimePoint startTime;

    private TimePoint endTime;

}
