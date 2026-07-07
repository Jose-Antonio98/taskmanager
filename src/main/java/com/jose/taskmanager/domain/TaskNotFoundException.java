package com.jose.taskmanager.domain;

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(TaskId taskId) {

        super("Task with id " + taskId + " not found");
    }


}
