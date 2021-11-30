package io.bitexpress.topia.commons.rpc;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 包含处理结果列表的响应组件
 *
 * @param <T>
 * @author shenyue
 * @see io.bitexpress.topia.commons.rpc.response.ListBodyResponse
 * @deprecated
 */
@Deprecated
public class ListResultResponse<T> extends BaseResponse {
    /**
     * 处理结果列表
     */
    private List<T> resultList;

    public List<T> getResultList() {
        return resultList;
    }

    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .appendSuper(super.toString())
                .append("resultList", this.resultList).toString();
    }

}
