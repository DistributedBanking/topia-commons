package io.bitexpress.topia.commons.basic.cache;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import com.google.common.cache.CacheStats;

public class GuavaCacheExpirer {

	private static final Logger logger = LoggerFactory.getLogger(GuavaCacheExpirer.class);

	private CacheManager cacheManager;

	public GuavaCacheExpirer(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	@SuppressWarnings("rawtypes")
	public void clean() {
		Collection<String> cacheNames = cacheManager.getCacheNames();
		for (String string : cacheNames) {
			Cache cache = cacheManager.getCache(string);
			if (cache != null) {
				com.google.common.cache.Cache nativeCache = (com.google.common.cache.Cache) cache.getNativeCache();
				nativeCache.cleanUp();
				CacheStats stats = nativeCache.stats();
				logger.trace("cache name:{}, size:{}, {}", string, nativeCache.size(), stats);
			}
		}
	}

}
