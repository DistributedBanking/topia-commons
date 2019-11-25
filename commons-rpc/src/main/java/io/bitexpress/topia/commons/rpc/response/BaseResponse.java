package io.bitexpress.topia.commons.rpc.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.bitexpress.topia.commons.rpc.SystemCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 基础响应组件
 *
 * @author shenyue
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse implements Serializable {
	/**
	 * 响应头
	 */
	@NotNull
	@Valid
    private ResponseHeader header;

}
