package com.marcelotomazini.eclipse.plugins.timer.preferences;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.marcelotomazini.eclipse.plugins.timer.Timer;
import com.marcelotomazini.eclipse.plugins.timer.utils.MessageBoxUtils;

public class NewTimerDialog extends Dialog {

	private Timer timer;
	
	private Text txtName;
	private Text txtTimer;

	public NewTimerDialog(Shell parent) {
		super(parent);
	}
	
	@Override
	protected void okPressed() {
		try {
			setFields();
			super.okPressed();
		} catch (Exception e) {
			MessageBoxUtils.showMessageBox(
					getShell(), 
					"Error",
					"- Name must be set\n"
							+ "- Timer must be an integer greater than 0");
		}
	}

	private void setFields() {
		String name = txtName.getText();
		int time = Integer.valueOf(txtTimer.getText());
		
		if(name.isEmpty())
			throw new RuntimeException();
		if(time <= 0)
			throw new RuntimeException();
		
		timer = new Timer();
		timer.setName(name);
		timer.setTime(time);
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite contents = (Composite) super.createDialogArea(parent);
		((GridLayout) contents.getLayout()).numColumns = 2;
		contents.setSize(400, 190);
		
		Label lblName = new Label(contents, SWT.WRAP);
		lblName.setText("Name");
		txtName = new Text(contents, SWT.BORDER);
		txtName.setLayoutData(new GridData(240, 20));
		
		Label lblTimer = new Label(contents, SWT.WRAP);
		lblTimer.setText("Timer (minutes)");
		txtTimer = new Text(contents, SWT.BORDER);
		
		return contents;
	}

	public Timer getTimer() {
		return timer;
	}
}
