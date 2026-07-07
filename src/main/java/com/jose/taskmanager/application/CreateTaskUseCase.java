package com.jose.taskmanager.application;

import com.jose.taskmanager.application.input.CreateTaskInput;
import com.jose.taskmanager.application.output.TaskOutput;
import com.jose.taskmanager.domain.Task;
import com.jose.taskmanager.domain.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateTaskUseCase {

    private final TaskRepository repository;

    public CreateTaskUseCase(TaskRepository repository) {
        this.repository = repository;
    }


    public TaskOutput execute(CreateTaskInput input) {
        var task = new Task(input.title(), input.description());
        var saved = repository.save(task);
        return TaskOutput.from(task);
    }
}
