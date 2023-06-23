package com.example.backend.toDo;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TodoService {

    final TodoRepo todoRepo;


    public void setStatus(String id, String status) {
        todoRepo.setStatus(id, status);
    }

    public void addTodoItem(TodoItem todoItem) {
        todoItem.setId(UUID.randomUUID().toString().substring(0, 4));
        todoRepo.addTodoItem(todoItem);
    }

    public void deleteTodoItem(String id) {
        todoRepo.deleteTodoItem(id);
    }

    public void updateTodoDescription(String id, String description) {
        todoRepo.updateTodoDescription(id, description);
    }

    public List<TodoItem> getAllTodos() {
        return todoRepo.getAllTodos();
    }

    public TodoItem getTodoItem(String id) {
        return todoRepo.getTodoItem(id);
    }
}
