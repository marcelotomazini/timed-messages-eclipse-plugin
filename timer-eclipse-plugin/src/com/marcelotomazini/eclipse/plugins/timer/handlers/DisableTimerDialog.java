package com.marcelotomazini.eclipse.plugins.timer.handlers;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class DisableTimerDialog extends Dialog {

	private String timer;

	protected DisableTimerDialog(Shell parentShell, String timer) {
		super(parentShell);
		this.timer = timer;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite contents = (Composite) super.createDialogArea(parent);
		((GridLayout) contents.getLayout()).numColumns = 2;
		contents.setSize(400, 190);
		
		Label lblName = new Label(contents, SWT.WRAP);
		lblName.setText("Timer '" + timer + "' is enabled. It will be disabled.");
		
		return contents;
	}
}
