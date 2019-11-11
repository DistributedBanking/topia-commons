package io.bitexpress.topia.commons.rpc.utils;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @deprecated use io.bitexpress.topia.commons.concept.CodeAndName
 * @author shwh1
 * 
 */
@Deprecated
public class CodeAndName implements Comparable {
	private String code;
	private String name;

	public CodeAndName() {
	}

	public CodeAndName(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("code", this.code)
				.append("name", this.name).toString();
	}

	/**
	 * @see java.lang.Comparable#compareTo(Object)
	 */
	public int compareTo(Object object) {
		CodeAndName myClass = (CodeAndName) object;
		return new CompareToBuilder().append(this.code, myClass.code).toComparison();
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof CodeAndName)) {
			return false;
		}
		CodeAndName rhs = (CodeAndName) object;
		return new EqualsBuilder().append(this.code, rhs.code).append(this.name, rhs.name).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(664967347, -2122682197).append(this.code).append(this.name).toHashCode();
	}

}
