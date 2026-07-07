package com.jose.taskmanager.application.input;

import com.jose.taskmanager.domain.TaskStatus;

import java.util.Optional;

public record UpdateTaskInput(Optional<String>title, Optional<String> description, Optional<TaskStatus> status) {


}
