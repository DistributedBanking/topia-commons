package io.bitexpress.topia.commons.rpc.request;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * <p></p >
 *
 * @author shenyue
 */
@Data
@SuperBuilder
public class RequestHeader implements Serializable {

    /**
     * 来源系统代码
     */
    private String sourceCode;
}
