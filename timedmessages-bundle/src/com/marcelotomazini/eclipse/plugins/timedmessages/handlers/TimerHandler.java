package com.marcelotomazini.eclipse.plugins.timedmessages.handlers;

import java.util.List;

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

import com.marcelotomazini.eclipse.plugins.timedmessages.Timer;
import com.marcelotomazini.eclipse.plugins.timedmessages.TimerList;
import com.marcelotomazini.eclipse.plugins.timedmessages.TimedMessagesPlugin;
import com.marcelotomazini.eclipse.plugins.timedmessages.utils.MarshallUtils;
import com.marcelotomazini.eclipse.plugins.timedmessages.utils.MessageBoxUtils;

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
				TimedMessagesPlugin.getDefault().getBundle().getSymbolicName());

		TimerList timerList = MarshallUtils.unmarshall(TimerList.class, prefs.getString(TimedMessagesPlugin.getId()));
		if (timerList.getTimers().isEmpty())
			noTimersToEnable();
		else
			chooseTimer(timerList.getTimers());
	}

	private void chooseTimer(List<Timer> timers) {
		TimerChooser dialog = new TimerChooser(getShell(), timers);
		if (dialog.open() == Window.OK)
			enableTimer(dialog.getSelectedTimer());		
	}

	private void enableTimer(final Timer selectedTimer) {
		final int toMinutes = 60000;
		job = new UIJob(selectedTimer.toString()) {
			@Override
			public IStatus runInUIThread(IProgressMonitor monitor) {
				schedule(selectedTimer.getTime() * toMinutes);
				return Status.OK_STATUS;
			}
		};
		job.addJobChangeListener(new JobChangeAdapter() {
			public void done(IJobChangeEvent event) {
				if (event.getResult().isOK())
					MessageBoxUtils.showMessageBox(
							getShell(), 
							"Info", 
							selectedTimer.getName());
			}
		});
		job.setSystem(true);
		job.schedule(selectedTimer.getTime() * toMinutes);
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
