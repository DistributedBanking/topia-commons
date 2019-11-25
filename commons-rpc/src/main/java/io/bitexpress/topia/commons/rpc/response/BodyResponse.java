package io.bitexpress.topia.commons.rpc.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 包含处理数据的响应组件
 * 
 * @author shenyue
 *
 * @param <T>
 */
@Data
@SuperBuilder(builderMethodName = "bodyBuilder")
@AllArgsConstructor
@NoArgsConstructor
public class BodyResponse<T> extends BaseResponse {
	/**
	 * 返回结果
	 */
	private T body;

}
