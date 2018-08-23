package com.marcelotomazini.eclipse.plugins.timedmessages.preferences;



import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.marcelotomazini.eclipse.plugins.timedmessages.TimedMessagesPlugin;

public class TimedMessagesPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public TimedMessagesPreferencePage() {
		super(GRID);
		setPreferenceStore(TimedMessagesPlugin.getDefault().getPreferenceStore());
	}

	@Override
	public void createFieldEditors() {
		addField(new TimerListEditor(TimedMessagesPlugin.getId(), "&Timers", getFieldEditorParent()));		
	}

	@Override
	public void init(IWorkbench workbench) {}
}