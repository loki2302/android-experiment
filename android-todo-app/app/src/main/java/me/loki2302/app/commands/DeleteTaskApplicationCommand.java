package me.loki2302.app.commands;

import me.loki2302.app.App;
import me.loki2302.app.data.TaskRepository;

public class DeleteTaskApplicationCommand implements ApplicationCommand<Void> {
    private final int taskId;

    public DeleteTaskApplicationCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public Void run(App app) {
        TaskRepository taskRepository = app.getTaskRepository();
        taskRepository.delete(taskId);

        return null;
    }
}
