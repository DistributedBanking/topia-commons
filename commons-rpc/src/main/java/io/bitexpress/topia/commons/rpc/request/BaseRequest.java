package io.bitexpress.topia.commons.rpc.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * <p>基础请求</p>
 *
 * @author shenyue
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class BaseRequest implements Serializable {
    private RequestHeader header;
}
