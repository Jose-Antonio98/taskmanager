package com.jose.taskmanager.infrastructure.http.request;

import com.jose.taskmanager.application.input.CreateTaskInput;

import java.util.Optional;

public record CreateTaskRequest(String title, Optional<String> description) {

    public CreateTaskInput toInput() {
        return new CreateTaskInput(title, description);
    }
}
