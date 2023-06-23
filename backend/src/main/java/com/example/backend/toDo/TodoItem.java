package com.example.backend.toDo;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
public class TodoItem {
    private String id;
    private String description;
    private String status;

    public String getStatus() {
        return status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodoItem todoItem = (TodoItem) o;
        return Objects.equals(description, todoItem.description) && Objects.equals(status, todoItem.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, status);
    }
}

