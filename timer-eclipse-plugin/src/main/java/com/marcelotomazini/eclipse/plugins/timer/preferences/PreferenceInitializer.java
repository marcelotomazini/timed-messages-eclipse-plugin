package com.marcelotomazini.eclipse.plugins.timer.preferences;



import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

public class PreferenceInitializer extends AbstractPreferenceInitializer {

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = TimerPlugin.getDefault().getPreferenceStore();
		store.setDefault(PreferenceConstants.P_ANNOTATION_EXCLUDES, "Test\nAfter\nBefore");
		store.setDefault(PreferenceConstants.P_METHOD_NAME_EXCLUDES, "main");
	}

}