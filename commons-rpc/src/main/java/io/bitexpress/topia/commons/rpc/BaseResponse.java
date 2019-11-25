package io.bitexpress.topia.commons.rpc;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 基础响应组件
 * 
 * @author shenyue
 *
 */
@Deprecated
public class BaseResponse implements Serializable {
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

	public SystemCode getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(SystemCode systemCode) {
		this.systemCode = systemCode;
	}

	public String getBusinessCode() {
		return businessCode;
	}

	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}

	public String getTrace() {
		return trace;
	}

	public void setTrace(String trace) {
		this.trace = trace;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("systemCode", this.systemCode)
				.append("businessCode", this.businessCode).append("trace", this.trace).append("message", this.message)
				.toString();
	}

}
