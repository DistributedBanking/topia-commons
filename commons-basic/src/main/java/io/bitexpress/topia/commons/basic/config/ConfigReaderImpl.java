package io.bitexpress.topia.commons.basic.config;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Function;
import com.google.common.collect.Maps;
import io.bitexpress.topia.commons.basic.exception.ErrorCodeValidate;
import io.bitexpress.topia.commons.basic.jackson.JacksonObjectBuilder;
import io.bitexpress.topia.commons.rpc.error.ErrorCode;

import jodd.bean.BeanUtil;

public class ConfigReaderImpl<KEY, CONF> implements ConfigReader<KEY, CONF>, InitializingBean {

	private ObjectMapper objectMapper;

	private Resource jsonResource;

	private Class<CONF> objectType;

	private List<CONF> configList;

	private Map<KEY, CONF> configMap;
	private String keyProperty;

	private ErrorCode notFoundErrorCode;

	@Override
	public CONF getConfig(KEY key) {
		if (notFoundErrorCode != null) {
			return ErrorCodeValidate.notNull(configMap.get(key), notFoundErrorCode, key);
		} else {
			return Validate.notNull(configMap.get(key), "config not found,%s", key);
		}
	}

	@Override
	public List<CONF> getConfigList() {
		return configList;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		JacksonObjectBuilder<CONF> builder = new JacksonObjectBuilder<>();
		builder.setObjectMapper(objectMapper);
		builder.setObjectType(objectType);
		builder.setResource(jsonResource);
		configList = builder.createObjectList();
		configMap = Maps.uniqueIndex(configList, new Function<CONF, KEY>() {

			@Override
			public KEY apply(CONF conf) {
				return BeanUtil.pojo.getProperty(conf, keyProperty);
			}
		});
	}

	@Required
	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Required
	public void setJsonResource(Resource jsonResource) {
		this.jsonResource = jsonResource;
	}

	@Required
	public void setKeyProperty(String keyProperty) {
		this.keyProperty = keyProperty;
	}

	@Required
	public void setObjectType(Class<CONF> objectType) {
		this.objectType = objectType;
	}

	public void setNotFoundErrorCode(ErrorCode notFoundErrorCode) {
		this.notFoundErrorCode = notFoundErrorCode;
	}

}
