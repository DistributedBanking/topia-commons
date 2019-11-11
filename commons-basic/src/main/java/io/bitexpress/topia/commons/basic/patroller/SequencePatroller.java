package io.bitexpress.topia.commons.basic.patroller;

import java.util.List;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.bitexpress.topia.commons.basic.competition.LockTemplate2;

public class SequencePatroller {

	private Logger scheduleLogger = LoggerFactory.getLogger(SequencePatroller.class);

	public static final String DEFAULT_KEY = "PATROL";

	private LockTemplate2 lockTemplate;

	private List<Patroller> patrollers;

	private String lockKey = DEFAULT_KEY;

	public void patrol() {
		scheduleLogger.info("patrol begin:{}", lockKey);
		lockTemplate.execute(lockKey, () -> {
			for (Patroller patroller : patrollers) {
				try {
					patroller.patrol();
				} catch (Exception e) {
					scheduleLogger.error("", e);
				}
			}
			return null;
		});
		scheduleLogger.info("patrol end:{}", lockKey);
	}

	public void setPatrollers(List<Patroller> patrollers) {
		this.patrollers = patrollers;
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
