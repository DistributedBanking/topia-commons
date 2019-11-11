package io.bitexpress.topia.commons.basic.patroller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.google.common.base.Stopwatch;
import io.bitexpress.topia.commons.basic.competition.LockCallback;
import io.bitexpress.topia.commons.basic.competition.LockTemplate2;

public class MutexPatroller implements InitializingBean {

	private Logger scheduleLogger = LoggerFactory.getLogger(MutexPatroller.class);

	private LockTemplate2 lockTemplate;

	private Object target;

	private String method;

	private String lockKey;
	private Method reflectMethod;

	@Override
	public void afterPropertiesSet() throws Exception {
		reflectMethod = target.getClass().getMethod(method);
	}

	public void patrol() {
		Stopwatch stopwatch = Stopwatch.createStarted();
		try {
			scheduleLogger.info("patrol begin:{}", lockKey);
			lockTemplate.execute(lockKey, new LockCallback<Void>() {

				@Override
				public Void locked() {
					try {
						reflectMethod.invoke(target);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						throw new ContextedRuntimeException(e);
					}
					return null;
				}
			});
		} finally {
			stopwatch.stop();
			scheduleLogger.info("patrol end:{}, cost:{}", lockKey, stopwatch);
		}
	}

	public void setTarget(Object target) {
		this.target = target;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public void setLockTemplate(LockTemplate2 lockTemplate) {
		this.lockTemplate = lockTemplate;
	}

	public void setLockKey(String lockKey) {
		Validate.notBlank(lockKey, "lockKey is blank");
		this.lockKey = lockKey;
	}

	public void setLoggerName(String loggerName) {
		Validate.notBlank(loggerName, "loggerName is blank");
		scheduleLogger = LoggerFactory.getLogger(loggerName);
	}

}
