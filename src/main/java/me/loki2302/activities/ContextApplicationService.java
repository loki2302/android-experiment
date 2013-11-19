package me.loki2302.activities;

import java.util.List;

import me.loki2302.application.Task;
import me.loki2302.dal.ApplicationService;
import me.loki2302.dal.ApplicationServiceCallback;
import me.loki2302.dal.dto.TaskStatus;
import roboguice.inject.ContextSingleton;

import android.app.Activity;
import android.content.Context;

import com.google.inject.Inject;

@ContextSingleton
public class ContextApplicationService {
	@Inject
	private Context context;
	
	@Inject
	private ProgressDialogLongOperationListener longOperationListener;
	
	@Inject
	private ApplicationService applicationService;
	
	//
	// TODO: can I get rid of this class if inject Provider<Activity> to real application service?
	//
	
	public void signIn(String email, String password, ApplicationServiceCallback<String> callback) {
		applicationService.signIn(
				longOperationListener, 
				email, 
				password, 
				runOnUiThread(callback));
	}
	
	public void getTasksByStatus(TaskStatus status, ApplicationServiceCallback<List<Task>> callback) {
		applicationService.getTasksByStatus(
				longOperationListener, 
				status, 
				runOnUiThread(callback));
	}
	
	public void getTask(int taskId, ApplicationServiceCallback<Task> callback) {
		applicationService.getTask(
				longOperationListener, 
				taskId, 
				runOnUiThread(callback));
	}
	
	public void createTask(String taskDescription, ApplicationServiceCallback<Task> callback) {
		applicationService.createTask(
				longOperationListener, 
				taskDescription, 
				runOnUiThread(callback));
	}
	
	public void updateTask(int taskId, String taskDescription, ApplicationServiceCallback<Task> callback) {
		applicationService.updateTask(
				longOperationListener,
				taskId,
				taskDescription, 
				runOnUiThread(callback));
	}
	
	public void progressTask(int taskId, ApplicationServiceCallback<Task> callback) {
		applicationService.progressTask(
				longOperationListener,
				taskId,
				runOnUiThread(callback));
	}
	
	public void unprogressTask(int taskId, ApplicationServiceCallback<Task> callback) {
		applicationService.unprogressTask(
				longOperationListener,
				taskId,
				runOnUiThread(callback));
	}
	
	public void deleteTask(int taskId, ApplicationServiceCallback<Object> callback) {
		applicationService.deleteTask(
				longOperationListener,
				taskId,
				runOnUiThread(callback));
	}
	
	private <T> ApplicationServiceCallback<T> runOnUiThread(ApplicationServiceCallback<T> applicationServiceCallback) {
		return new RunOnUiThreadApplicationServiceCallbackDecorator<T>((Activity)context, applicationServiceCallback);
	}
	
	private static class RunOnUiThreadApplicationServiceCallbackDecorator<T> implements ApplicationServiceCallback<T> {
		private final Activity activity;
		private final ApplicationServiceCallback<T> applicationServiceCallback;
		
		public RunOnUiThreadApplicationServiceCallbackDecorator(
				Activity activity, 
				ApplicationServiceCallback<T> applicationServiceCallback) {
			
			this.activity = activity;
			this.applicationServiceCallback = applicationServiceCallback;
		}

		@Override
		public void onSuccess(final T result) {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					applicationServiceCallback.onSuccess(result);
				}				
			});			
		}

		@Override
		public void onError() {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					applicationServiceCallback.onError();
				}				
			});			
		}
		
		private void runOnUiThread(Runnable runnable) {
			activity.runOnUiThread(runnable);
		}
	}
}