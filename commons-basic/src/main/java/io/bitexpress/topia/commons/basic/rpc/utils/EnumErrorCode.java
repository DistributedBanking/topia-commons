package io.bitexpress.topia.commons.basic.rpc.utils;

import io.bitexpress.topia.commons.rpc.error.ErrorCode;

import jodd.bean.BeanUtil;

public class EnumErrorCode implements ErrorCode {
	private Enum<?> enumObject;

	public EnumErrorCode(Enum<?> enumObject) {
		this.enumObject = enumObject;
	}

	@Override
	public String getTemplate() {
		return BeanUtil.silent.getProperty(enumObject, "template");
	}

	@Override
	public String getCode() {
		return enumObject.name();
	}

}