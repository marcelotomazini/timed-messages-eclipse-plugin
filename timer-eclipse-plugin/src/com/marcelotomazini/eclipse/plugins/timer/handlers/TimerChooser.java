package com.marcelotomazini.eclipse.plugins.timer.handlers;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.marcelotomazini.eclipse.plugins.timer.utils.MessageBoxUtils;

public class TimerChooser extends Dialog {

	private String timer;
	private String[] timers;
	private Combo combo;
	
	protected TimerChooser(Shell shell, String[] timers) {
		super(shell);
		this.timers = timers;
	}

	public String getSelectedTimer() {
		return timer;
	}

	@Override
	protected void okPressed() {
		if(combo.getSelectionIndex() < 0) {
			MessageBoxUtils.showMessageBox(
					getShell(), 
					"Error", 
					"You must select an existing timer");
		} else {
			timer = combo.getItem(combo.getSelectionIndex());
			super.okPressed();
		}
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite contents = (Composite) super.createDialogArea(parent);
		((GridLayout) contents.getLayout()).numColumns = 1;
		
		Label label = new Label(contents, SWT.WRAP);
		label.setText("Select one timer to enable");
		
		combo = new Combo(contents, SWT.BORDER);
		combo.setItems(timers);
		
		return contents;
	}
}
