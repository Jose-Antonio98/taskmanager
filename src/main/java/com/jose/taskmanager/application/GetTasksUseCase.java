package com.jose.taskmanager.application;

import com.jose.taskmanager.application.output.TaskOutput;
import com.jose.taskmanager.domain.Task;
import com.jose.taskmanager.domain.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetTasksUseCase {

    private final TaskRepository repository;

    public GetTasksUseCase(TaskRepository repository) {
        this.repository = repository;
    }

    List <TaskOutput> execute() {
        return repository.findAll().stream().map(TaskOutput::from).toList();
    }
}
