package io.bitexpress.topia.commons.basic.config;

import java.util.List;

public interface ConfigReader<KEY, CONF> {
	CONF getConfig(KEY key);

	List<CONF> getConfigList();
	
}
