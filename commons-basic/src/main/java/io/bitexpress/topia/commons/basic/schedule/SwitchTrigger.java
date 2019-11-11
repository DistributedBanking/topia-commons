package io.bitexpress.topia.commons.basic.schedule;

import java.util.Date;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;

public class SwitchTrigger implements Trigger {

	private Trigger trigger;

	private boolean enabled;

	@Override
	public Date nextExecutionTime(TriggerContext context) {
		if (enabled) {
			return trigger.nextExecutionTime(context);
		} else {
			return null;
		}
	}

	public void setTrigger(Trigger trigger) {
		this.trigger = trigger;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
