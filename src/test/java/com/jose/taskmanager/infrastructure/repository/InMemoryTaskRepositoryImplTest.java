package com.jose.taskmanager.infrastructure.repository;

import com.jose.taskmanager.domain.TaskRepository;
import com.jose.taskmanager.domain.TaskRepositoryTest;
import org.junit.jupiter.api.BeforeEach;

class InMemoryTaskRepositoryImplTest extends TaskRepositoryTest {

    private InMemoryTaskRepositoryImpl repository;

    @Override
    protected TaskRepository createRepository() {
        return new  InMemoryTaskRepositoryImpl();
    }
}