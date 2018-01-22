package com.marcelotomazini.eclipse.plugins.timer.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.eclipse.ui.progress.UIJob;

import com.marcelotomazini.eclipse.plugins.TimerPlugin;
import com.marcelotomazini.eclipse.plugins.timer.utils.MessageBoxUtils;

public class TimerHandler extends AbstractHandler implements IHandler {

	private Job job;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		if (job == null)
			enableTimer();
		else
			disableTimer();
		return null;
	}

	private void enableTimer() {
		IPreferenceStore prefs = new ScopedPreferenceStore(InstanceScope.INSTANCE,
				TimerPlugin.getDefault().getBundle().getSymbolicName());

		String[] timers = prefs.getString(TimerPlugin.getId()).split("\\n+");
		if (timers[0].isEmpty())
			noTimersToEnable();
		else
			chooseTimer(timers);
	}

	private void chooseTimer(String[] timers) {
		TimerChooser dialog = new TimerChooser(getShell(), timers);
		if (dialog.open() == Window.OK)
			enableTimer(dialog.getSelectedTimer());		
	}

	private void enableTimer(String selectedTimer) {
		final int timer = Integer
				.valueOf(selectedTimer.substring(selectedTimer.indexOf(" (") + 2, selectedTimer.indexOf(" minutes)")));
		final String message = selectedTimer.substring(0, selectedTimer.indexOf(" ("));

		final int toMinutes = 60000;
		job = new UIJob(selectedTimer) {
			@Override
			public IStatus runInUIThread(IProgressMonitor monitor) {
				schedule(timer * toMinutes);
				return Status.OK_STATUS;
			}
		};
		job.addJobChangeListener(new JobChangeAdapter() {
			public void done(IJobChangeEvent event) {
				if (event.getResult().isOK())
					MessageBoxUtils.showMessageBox(
							getShell(), 
							"Info", 
							message);
			}
		});
		job.setSystem(true);
		job.schedule(timer * toMinutes);
	}

	private void disableTimer() {
		DisableTimerDialog dialog = new DisableTimerDialog(getShell(), job.getName());
		if(dialog.open() == Window.OK) {
			job.cancel();
			job = null;
		}
	}

	private Shell getShell() {
		return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
	}

	private void noTimersToEnable() {
		MessageBoxUtils.showMessageBox(
				getShell(), 
				"Info", 
				"There are no timers to enable");
	}
}
