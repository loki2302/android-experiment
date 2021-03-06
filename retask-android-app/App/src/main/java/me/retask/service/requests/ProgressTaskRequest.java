package me.retask.service.requests;

import android.content.ContentResolver;

import me.retask.service.ApplicationState;
import me.retask.dal.RetaskContentResolverUtils;
import me.retask.webapi.ApiCallProcessor;
import me.retask.webapi.apicalls.ProgressTaskApiCall;
import me.retask.webapi.dto.TaskDto;

public class ProgressTaskRequest implements ServiceRequest<Integer> {
    private final long taskId;

    public ProgressTaskRequest(long taskId) {
        this.taskId = taskId;
    }

    @Override
    public Integer run(ApiCallProcessor apiCallProcessor, ApplicationState applicationState, ContentResolver contentResolver) {
        int taskRemoteId = RetaskContentResolverUtils.getTaskRemoteId(contentResolver, taskId);
        ProgressTaskApiCall progressTaskApiCall = new ProgressTaskApiCall(applicationState.getAndUpdateSessionToken(), taskRemoteId);
        TaskDto taskDto = apiCallProcessor.processApiCall(progressTaskApiCall);
        RetaskContentResolverUtils.updateTask(contentResolver, taskId, taskDto);
        return taskDto.taskStatus;
    }
}
