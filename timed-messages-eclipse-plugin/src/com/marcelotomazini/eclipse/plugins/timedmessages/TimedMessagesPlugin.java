package com.marcelotomazini.eclipse.plugins.timedmessages;

import org.eclipse.ui.plugin.AbstractUIPlugin;

public class TimedMessagesPlugin extends AbstractUIPlugin {

	private static TimedMessagesPlugin plugin;
	
	public TimedMessagesPlugin() {
		super();
		plugin = this;
	}
	
	public static String getId() {
		return getDefault().getBundle().getSymbolicName();
	}
	
	public static TimedMessagesPlugin getDefault() {
		return plugin;
	}
}
