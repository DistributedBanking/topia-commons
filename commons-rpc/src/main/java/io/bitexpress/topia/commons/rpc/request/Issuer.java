package io.bitexpress.topia.commons.rpc.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;

/**
 * <p>请求发起方</p >
 *
 * @author shenyue
 */

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Issuer {
    /**
     * 发起方代码
     */
    @NotBlank
    private String issuerCode;

    /**
     * 发起方类型
     */
    @NotBlank
    private String issuerType;
}
