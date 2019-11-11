package io.bitexpress.topia.commons.rpc;

import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 包含处理数据的响应组件
 * 
 * @author shenyue
 *
 * @param <T>
 */
public class MapResultResponse<K, V> extends BaseResponse {
	/**
	 * 返回结果
	 */
	private Map<K, V> result;

	public Map<K, V> getResult() {
		return result;
	}

	public void setResult(Map<K, V> result) {
		this.result = result;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).appendSuper(super.toString())
				.append("result", this.result).toString();
	}

}
