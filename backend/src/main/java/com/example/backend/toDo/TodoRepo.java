package com.example.backend.toDo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TodoRepo {

    private final List<TodoItem> todoRepository;

    public List<TodoItem> getAllTodos() {
        return this.todoRepository;
    }

    void setStatus(String id, String status) {
        todoRepository.stream().forEach(todoItem -> {
            if (todoItem.getId().equals(id)) {
                todoItem.setStatus(status);
            }
        });
    }

    void addTodoItem(TodoItem todoItem) {
        todoRepository.add(todoItem);
    }

    public List<TodoItem> getTodoRepository() {
        return todoRepository;
    }

    TodoItem getTodoItem(String id) {
        TodoItem todoItem = null;
        for (TodoItem todo : todoRepository) {
            if (todo.getId().equals(id)) {
                todoItem = todo;
            }
        }
        return todoItem;
    }

    void deleteTodoItem(String id) {
        todoRepository.removeIf(todoItem -> todoItem.getId().equals(id));
    }

    void updateTodoDescription(String id, String description) {
        todoRepository.stream().forEach(todo -> {
            if (todo.getId().equals(id)) {
                todo.setDescription(description);
            }
        });
    }
}
