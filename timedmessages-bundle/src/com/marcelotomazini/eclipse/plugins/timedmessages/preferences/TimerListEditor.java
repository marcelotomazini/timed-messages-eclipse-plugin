package com.marcelotomazini.eclipse.plugins.timedmessages.preferences;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.preference.ListEditor;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Composite;

import com.marcelotomazini.eclipse.plugins.timedmessages.Timer;
import com.marcelotomazini.eclipse.plugins.timedmessages.TimerList;
import com.marcelotomazini.eclipse.plugins.timedmessages.utils.MarshallUtils;

public class TimerListEditor extends ListEditor {

	private TimerList timerList = new TimerList();
	
	protected TimerListEditor(String name, String labelText, Composite parent) {
		init(name, labelText);
		createControl(parent);
	}

	@Override
	protected String createList(String[] items) {
		List<Timer> timers = new ArrayList<Timer>();
		for(String item : items)
			timers.add(findTimer(item));
		timerList.setTimers(timers);
		return MarshallUtils.marshall(TimerList.class, timerList);
	}

	private Timer findTimer(String item) {
		int openBracket = item.lastIndexOf("(");
		int closeBracket = item.lastIndexOf(")");

		String name = item.substring(0, openBracket - 1);
		int time = Integer.valueOf(item.substring(openBracket + 1, closeBracket));
		
		Timer timer = new Timer();
		timer.setName(name);
		timer.setTime(time);
		
		return timer;
	}

	@Override
	protected String getNewInputObject() {
		NewTimerDialog dialog = new NewTimerDialog(getShell());
		if (dialog.open() == Window.OK) {
			Timer timer = dialog.getTimer();
			
			timerList.add(timer);
			return timer.toString();
		}

		return null;
	}

	@Override
	protected String[] parseString(String stringList) {
		if(stringList.isEmpty())
			return new String[0];
		
		timerList = MarshallUtils.unmarshall(TimerList.class, stringList);
		List<String> timers = new ArrayList<String>();
		for(Timer timer : timerList.getTimers())
			timers.add(timer.toString());
		
		return (String[]) timers.toArray(new String[timers.size()]);
	}
}
