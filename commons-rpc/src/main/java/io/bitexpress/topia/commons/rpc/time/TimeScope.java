package io.bitexpress.topia.commons.rpc.time;

import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * <p>时间范围.</p >
 * 为什么不直接使用internval或者Range&lt;Date>，因为两边都可空，并且要加上是否包含的标志
 *
 * @author shenyue
 */
@Data
@SuperBuilder
public class TimeScope {

    private TimePoint startTime;

    private TimePoint endTime;

}
