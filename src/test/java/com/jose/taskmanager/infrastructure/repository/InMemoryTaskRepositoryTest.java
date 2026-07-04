package com.jose.taskmanager.infrastructure.repository;

import com.jose.taskmanager.domain.TaskRepository;
import com.jose.taskmanager.domain.TaskRepositoryTest;

class InMemoryTaskRepositoryTest extends TaskRepositoryTest {

    private InMemoryTaskRepository repository;

    @Override
    protected TaskRepository createRepository() {
        return new InMemoryTaskRepository();
    }
}