package io.bitexpress.topia.commons.rpc.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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

    @NotNull
    @Valid
    private RequestHeader header;
}
