package io.bitexpress.topia.commons.rpc.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * <p>含请求体请求</p>
 *
 * @author shenyue
 */
@Data
@Builder(builderMethodName = "listBodyRequestBuilder")
@AllArgsConstructor
@NoArgsConstructor
public class ListBodyRequest<T> extends BaseRequest {
    /**
     * 列表请求体
     */
    private List<T> body;

}
