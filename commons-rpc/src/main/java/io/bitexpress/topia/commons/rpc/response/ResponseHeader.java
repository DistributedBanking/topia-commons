package io.bitexpress.topia.commons.rpc.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.bitexpress.topia.commons.rpc.SystemCode;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p></p >
 *
 * @author shenyue
 */
@Data
@Builder
public class ResponseHeader implements Serializable {
    /**
     * 系统代码
     */
    @NotNull
    private SystemCode systemCode;

    /**
     * 业务代码
     */
    private String businessCode;

    /**
     * 异常堆栈
     */
    private String trace;
    /**
     * 相关信息
     */
    private String message;

    @JsonIgnore
    public String getDescription() {
        return String.format("systemCode:%s,businessCode:%s, message:%s", systemCode, businessCode, message);
    }
}