package io.bitexpress.topia.commons.basic.mbassador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.engio.mbassy.bus.error.IPublicationErrorHandler;
import net.engio.mbassy.bus.error.PublicationError;

public class MbassadorSlf4jLoggerErrorHandler implements IPublicationErrorHandler {

	private Logger logger = LoggerFactory.getLogger(MbassadorSlf4jLoggerErrorHandler.class);

	public MbassadorSlf4jLoggerErrorHandler() {
	}

	public MbassadorSlf4jLoggerErrorHandler(String loggerName) {
		super();
		this.logger = LoggerFactory.getLogger(loggerName);
	}

	@Override
	public void handleError(PublicationError error) {
		logger.error("", error.getCause());
	}

}
