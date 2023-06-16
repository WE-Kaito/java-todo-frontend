package com.example.backend.toDo;

import java.util.UUID;

public class TodoItem {
    private final String id;
    private String description;
    private String status;

    public TodoItem(String description, Status status) {
        this.id = UUID.randomUUID().toString().substring(0, 4);
        this.description = description;
        setStatus(status);
    }

    public String getStatus() {
        return status;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(Status status) {
        this.status = status.toString();
    }


}

