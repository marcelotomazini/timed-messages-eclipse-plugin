package com.marcelotomazini.eclipse.plugins.timer.preferences;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class Timer extends Dialog {

	private String name;
	private int timer;
	boolean active;
	
	private Text txtName;
	private Text txtTimer;
	private Button chkActive;

	public Timer(Shell parent) {
		super(parent);
	}

	@Override
	public boolean close() {
		name = txtName.getText();
		timer = Integer.valueOf(txtTimer.getText());
		active = chkActive.getSelection();
		return super.close();
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite contents = (Composite) super.createDialogArea(parent);
		((GridLayout) contents.getLayout()).numColumns = 2;
		
		Label lblName = new Label(contents, SWT.WRAP);
		lblName.setText("Name");
		txtName = new Text(contents, SWT.WRAP);
		
		Label lblTimer = new Label(contents, SWT.WRAP);
		lblTimer.setText("Timer(minutes)");
		txtTimer = new Text(contents, SWT.WRAP);
		
		Label lblActive = new Label(contents, SWT.WRAP);
		lblActive.setText("Active");
		chkActive = new Button(contents, SWT.CHECK);
		
		return contents;
	}

	public String getName() {
		return name;
	}

	public int getTimer() {
		return timer;
	}

	public boolean isActive() {
		return active;
	}
}
