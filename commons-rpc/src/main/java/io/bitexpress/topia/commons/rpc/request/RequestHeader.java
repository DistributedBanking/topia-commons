package io.bitexpress.topia.commons.rpc.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.Locale;

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
    public static final RequestHeader EMPTY_HEADER = new RequestHeader();
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
     * 是否开启缓存.空表示AUTO
     */
    private Cacheable cacheable;

    /**
     * 来源本地代码
     */
    private Locale locale;
}
