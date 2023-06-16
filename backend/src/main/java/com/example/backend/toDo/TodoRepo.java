package com.example.backend.toDo;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@NoArgsConstructor
public class TodoRepo {

    private List<TodoItem> todoRepository;

    public List<TodoItem> getTodoRepository() {
        return todoRepository;
    }

    void setStatus(String id, Status status) {
        todoRepository.stream().forEach(todoItem -> {
            if (todoItem.getId().equals(id)) {
                todoItem.setStatus(status);
            }
        });
    }

    void addTodoItem(TodoItem todoItem) {
        todoRepository.add(todoItem);
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

    List<TodoItem> getTodoTodos() {
        List<TodoItem> todoTodos = new ArrayList<>();
        todoRepository.stream().forEach(todo -> {
            if (todo.getStatus().equals(Status.TODO.toString())) {
                todoTodos.add(todo);
            }
        });
        return todoTodos;
    }

    List<TodoItem> getTodoDoings() {
        List<TodoItem> todoDoings = new ArrayList<>();
        todoRepository.stream().forEach(todo -> {
            if (todo.getStatus().equals(Status.DOING.toString())) {
                todoDoings.add(todo);
            }
        });
        return todoDoings;
    }

    List<TodoItem> getTodoDones() {
        List<TodoItem> todoDones = new ArrayList<>();
        todoRepository.stream().forEach(todo -> {
            if (todo.getStatus().equals(Status.DONE.toString())) {
                todoDones.add(todo);
            }
        });
        return todoDones;
    }

}
