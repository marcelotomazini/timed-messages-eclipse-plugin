package com.marcelotomazini.eclipse.plugins.timer.handlers;

import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.marcelotomazini.eclipse.plugins.timer.Timer;
import com.marcelotomazini.eclipse.plugins.timer.utils.MessageBoxUtils;

public class TimerChooser extends Dialog {

	private Timer timer;
	private List<Timer> timers;
	private Combo combo;
	
	protected TimerChooser(Shell shell, List<Timer> timers) {
		super(shell);
		this.timers = timers;
	}

	public Timer getSelectedTimer() {
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
			timer = findBy(combo.getItem(combo.getSelectionIndex()));
			super.okPressed();
		}
	}
	
	private Timer findBy(String name) {
		for(Timer t : timers)
			if(t.getName().equals(name))
				return t;
		
		return null;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite contents = (Composite) super.createDialogArea(parent);
		((GridLayout) contents.getLayout()).numColumns = 1;
		
		Label label = new Label(contents, SWT.WRAP);
		label.setText("Select one timer to enable");
		
		combo = new Combo(contents, SWT.BORDER);
		for(Timer timer : timers)
			combo.add(timer.toString());
		
		return contents;
	}
}
