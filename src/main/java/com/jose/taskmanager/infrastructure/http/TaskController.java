package com.jose.taskmanager.infrastructure.http;

import com.jose.taskmanager.application.*;
import com.jose.taskmanager.application.input.CreateTaskInput;
import com.jose.taskmanager.application.output.TaskOutput;
import com.jose.taskmanager.domain.TaskId;
import com.jose.taskmanager.infrastructure.http.request.CreateTaskRequest;
import com.jose.taskmanager.infrastructure.http.request.UpdateTaskRequest;
import com.jose.taskmanager.infrastructure.http.response.TaskResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final CreateTaskUseCase createTaskUseCase;
    private final GetTasksUseCase getTaskUseCase;
    private final GetTaskByIdUseCase getTaskByIdUseCase;
    private final DeleteTaskUseCase deleteTaskUseCase;
    private final UpdateTaskUseCase updateTaskUseCase;

    public TaskController(CreateTaskUseCase createTaskUseCase, GetTasksUseCase getTaskUseCase,
                          GetTaskByIdUseCase getTaskByIdUseCase,  DeleteTaskUseCase deleteTaskUseCase,
                          UpdateTaskUseCase updateTaskUseCase) {
        this.createTaskUseCase = createTaskUseCase;
        this.getTaskUseCase = getTaskUseCase;
        this.getTaskByIdUseCase = getTaskByIdUseCase;
        this.deleteTaskUseCase = deleteTaskUseCase;
        this.updateTaskUseCase = updateTaskUseCase;
    }

    @PostMapping
    TaskResponse create(@RequestBody @Valid CreateTaskRequest request) {
        var input = request.toInput();
        var output = createTaskUseCase.execute(input);
        return TaskResponse.from(output);
    }

    @GetMapping
    List<TaskResponse> list() {
        return getTaskUseCase.execute().stream().map(TaskResponse::from).toList();
    }

    @GetMapping("/{id}")
    TaskResponse read(@PathVariable UUID id) {
        var output = getTaskByIdUseCase.execute(new TaskId(id));
        return TaskResponse.from(output);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@RequestParam UUID id) {
        deleteTaskUseCase.execute(new TaskId(id));

    }

    @PatchMapping
    TaskResponse update(@PathVariable UUID id, @RequestBody UpdateTaskRequest request) {
        var input = request.toInput();
        var output = updateTaskUseCase.execute(new TaskId(id), input);
        return TaskResponse.from(output);
    }
}
