package com.jose.taskmanager.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class TaskRepositoryTest {

    TaskRepository repository;

    protected abstract TaskRepository createRepository();

    @BeforeEach
    void setUp() {
        this.repository = createRepository();
    }

    @Test
    void should_save_and_retrieve_task_by_id() {
        //given
        var task = new Task("Passar na padaria", Optional.empty());

        //when
        var saved = repository.save(task);
        Optional<Task> result = repository.findById(saved.getId());

        //then
        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(task.getId());
        assertThat(result.get().getDescription()).isEqualTo(task.getDescription());
        assertThat(result.get().getStatus()).isEqualTo(task.getStatus());
    }

    @Test
    void should_find_all_persisted_tasks() {
        //given
        var Task1 = new Task("Arrumar chuveiro", Optional.of("Comprar chuveiro novo"));
        var Task2 = new Task("Trocar interruptor", Optional.of("Ecnontrar a chave de fenda"));

        repository.save(Task1);
        repository.save(Task2);

        List<Task> tasks = repository.findAll();

        assertThat(tasks).hasSize(2);
        assertThat(tasks).extracting(Task::getId).containsExactlyInAnyOrder(Task1.getId(), Task2.getId());
    }

    @Test
    void should_delete_task_by_id() {
        //given
        var task = repository.save(new Task("treinar na academia", Optional.empty()));
        var taskId = task.getId();

        //when
        repository.delete(taskId);
        Optional<Task> result = repository.findById(taskId);

        //then
        assertThat(result).isEmpty();
    }

    @Test
    void should_return_empty_when_searcheing_non_existent_task() {
        //given
        var nonExistenId = new TaskId();

        //when
        Optional<Task> result = repository.findById(nonExistenId);

        //then
        assertThat(result).isEmpty();

    }

    @Test
    void should_update_task_status_successful() {
        //given
        var task = new Task("Atualizar CNH", Optional.empty());

        task.setDescription(Optional.of("Não expirou ainda"));
        task.setStatus(TaskStatus.IN_PROGRESS);


        //when
        repository.save(task);
        Optional<Task> result = repository.findById(task.getId());

        assertThat(result).isPresent();
        assertThat(result.get().getDescription()).isEqualTo(Optional.of("Não expirou ainda"));
        assertThat(result.get().getStatus()).isEqualTo(TaskStatus.IN_PROGRESS);


    }

}