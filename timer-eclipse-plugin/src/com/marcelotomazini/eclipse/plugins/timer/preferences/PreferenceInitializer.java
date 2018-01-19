package com.marcelotomazini.eclipse.plugins.timer.preferences;



import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import com.marcelotomazini.eclipse.plugins.timer.TimerPlugin;

public class PreferenceInitializer extends AbstractPreferenceInitializer {

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = TimerPlugin.getDefault().getPreferenceStore();
		store.setDefault(PreferenceConstants.P_TIMER, "");
	}

}