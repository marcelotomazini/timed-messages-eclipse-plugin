package com.marcelotomazini.eclipse.plugins.timer.utils;

import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class MessageBoxUtils {

	public static void showMessageBox(Shell shell, String title, String message) {
		MessageBox messageBox = new MessageBox(shell);
		messageBox.setText(title);
		messageBox.setMessage(message);
		messageBox.open();
	}
}
