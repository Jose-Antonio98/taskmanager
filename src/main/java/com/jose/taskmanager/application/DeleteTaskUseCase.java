package com.jose.taskmanager.application;

import com.jose.taskmanager.domain.TaskId;
import com.jose.taskmanager.domain.TaskNotFoundException;
import com.jose.taskmanager.domain.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteTaskUseCase {

    private final TaskRepository repository;

    public DeleteTaskUseCase(TaskRepository repository) {
        this.repository = repository;
    }

    public void execute(TaskId id) {
        if (repository.findById(id).isEmpty()) {
            throw new TaskNotFoundException(id);
        }

        repository.delete(id);
    }
}
