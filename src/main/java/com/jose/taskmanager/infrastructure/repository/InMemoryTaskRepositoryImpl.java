package com.jose.taskmanager.infrastructure.repository;

import com.jose.taskmanager.domain.Task;
import com.jose.taskmanager.domain.TaskId;
import com.jose.taskmanager.domain.TaskRepository;

import java.util.*;

public class InMemoryTaskRepositoryImpl implements TaskRepository {
    private final Map<TaskId, Task> storage = new HashMap<TaskId, Task>();

    @Override
    public Task save(Task task) {
        storage.put(task.getId(), task);
        return task;
    }

    @Override
    public List<Task> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Optional<Task> findById(TaskId id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public void delete(TaskId id) {
        storage.remove(id);
    }
}
