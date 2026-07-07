package com.jose.taskmanager.application;

import com.jose.taskmanager.application.input.UpdateTaskInput;
import com.jose.taskmanager.application.output.TaskOutput;
import com.jose.taskmanager.domain.TaskId;
import com.jose.taskmanager.domain.TaskNotFoundException;
import com.jose.taskmanager.domain.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateTaskUseCase {

    private TaskRepository repository;
    public UpdateTaskUseCase(TaskRepository repository) {
        this.repository = repository;
    }

    public TaskOutput execute(TaskId taskId, UpdateTaskInput input) {
        var task = repository.findById(taskId).orElseThrow(() ->new TaskNotFoundException(taskId));

        task.update(input.title(), input.description(), input.status());
        var updated = repository.save(task);
        return TaskOutput.from(updated);
    }
}
