package io.bitexpress.topia.commons.rpc.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * <p>含请求体请求</p>
 *
 * @author shenyue
 */
@Data
@SuperBuilder(builderMethodName = "bodyRequestBuilder")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BodyRequest<T> extends BaseRequest {

    /**
     * 请求体
     */
    private T body;

}
