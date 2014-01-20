package me.retask.service.requests;

import android.content.Context;
import android.content.Intent;

import me.retask.service.RetaskService;

public class UnprogressTaskRetaskServiceRequest implements RetaskServiceRequest {
    public final static String COMMAND = "unprogressTask";

    private final String sessionToken;
    private final long taskId;

    public UnprogressTaskRetaskServiceRequest(String sessionToken, long taskId) {
        this.sessionToken = sessionToken;
        this.taskId = taskId;
    }

    @Override
    public Intent getIntent(Context context) {
        Intent intent = new Intent(context, RetaskService.class);
        intent.putExtra(EXTRA_COMMAND, COMMAND);
        intent.putExtra(EXTRA_SESSION_TOKEN, sessionToken);
        intent.putExtra(EXTRA_TASK_ID, taskId);
        return intent;
    }
}
