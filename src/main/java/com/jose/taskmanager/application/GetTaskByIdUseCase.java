package com.jose.taskmanager.application;

import com.jose.taskmanager.application.output.TaskOutput;
import com.jose.taskmanager.domain.TaskId;
import com.jose.taskmanager.domain.TaskNotFoundException;
import com.jose.taskmanager.domain.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class GetTaskByIdUseCase {
    private final TaskRepository repository;

    public GetTaskByIdUseCase(TaskRepository repository) {
        this.repository = repository;
    }

    public TaskOutput execute(TaskId id) {
        return repository.findById(id).map(TaskOutput::from).orElseThrow(() -> new TaskNotFoundException(id));

    }
}
