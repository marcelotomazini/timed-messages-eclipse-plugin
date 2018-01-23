package com.marcelotomazini.eclipse.plugins.timer;

import org.eclipse.ui.plugin.AbstractUIPlugin;

public class TimerPlugin extends AbstractUIPlugin {

	private static TimerPlugin plugin;
	
	public TimerPlugin() {
		super();
		plugin = this;
	}
	
	public static String getId() {
		return getDefault().getBundle().getSymbolicName();
	}
	
	public static TimerPlugin getDefault() {
		return plugin;
	}
}
