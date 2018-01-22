package com.marcelotomazini.eclipse.plugins.timer;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class TimerList {

	@XmlElement
	private List<Timer> timers;

	public List<Timer> getTimers() {
		return timers;
	}

	public void setTimers(List<Timer> timers) {
		this.timers = timers;
	}

	public void add(Timer timer) {
		timers.add(timer);
	}
}
