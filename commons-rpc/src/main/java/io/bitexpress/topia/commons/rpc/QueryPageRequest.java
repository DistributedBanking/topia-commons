package io.bitexpress.topia.commons.rpc;

import io.bitexpress.topia.commons.pagination.SortPageParam;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 分页查询请求
 *
 * @author shenyue
 * @deprecated
 */
@Deprecated
public class QueryPageRequest implements Serializable {
    /**
     * 查询开始时间
     */
    private Date startTime;

    /**
     * 查询结束时间
     */
    private Date endTime;

    /**
     * 查询分页参数
     */
    @NotNull
    @Valid
    private SortPageParam sortPageParam;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public SortPageParam getSortPageParam() {
        return sortPageParam;
    }

    public void setSortPageParam(SortPageParam sortPageParam) {
        this.sortPageParam = sortPageParam;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("sortPageParam", this.sortPageParam)
                .append("startTime", this.startTime).append("endTime", this.endTime).toString();
    }

}
