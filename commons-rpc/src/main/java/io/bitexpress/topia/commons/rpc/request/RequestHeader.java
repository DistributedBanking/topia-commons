package io.bitexpress.topia.commons.rpc.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import java.io.Serializable;

/**
 * <p></p >
 *
 * @author shenyue
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class RequestHeader implements Serializable {

    /**
     * 来源系统代码
     */
    private String sourceCode;

    /**
     * 请求发起方
     */
    @Valid
    private Issuer issuer;

    /**
     * 是否开启缓存
     */
    private boolean cacheable;
}
