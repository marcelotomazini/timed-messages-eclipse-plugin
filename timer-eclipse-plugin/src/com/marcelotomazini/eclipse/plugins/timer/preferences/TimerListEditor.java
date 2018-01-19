package com.marcelotomazini.eclipse.plugins.timer.preferences;

import org.eclipse.jface.preference.ListEditor;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Composite;

public class TimerListEditor extends ListEditor {
	
	protected TimerListEditor(String name, String labelText, Composite parent) {
		init(name, labelText);
		createControl(parent);
	}

	@Override
	protected String createList(String[] items) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < items.length; i++) {
			result.append(items[i]).append(" \n");
		}
		return result.toString();
	}

	@Override
	protected String getNewInputObject() {
		Timer dialog = new Timer(getShell());
		if(dialog.open() == Window.OK) {
			return String.format("%s-%s minutes (%s)",
					dialog.getName(),
					dialog.getTimer(),
					dialog.isActive() ? "Active" : "Inactive");
		}
		
		return null;
	}

	@Override
	protected String[] parseString(String stringList) {
		return stringList.split("\\s+");
	}
}
