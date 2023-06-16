package com.example.backend.toDo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    TodoRepo todoRepo;

    @Autowired
    public TodoService(TodoRepo todoRepo) {
        this.todoRepo = todoRepo;
    }

    public void setStatus(String id, Status status) {
        todoRepo.setStatus(id, status);
    }

    public void addTodoItem(TodoItem todoItem) {
        todoRepo.addTodoItem(todoItem);
    }

    public void deleteTodoItem(String id) {
        todoRepo.deleteTodoItem(id);
    }

    public void updateTodoDescription(String id, String description) {
        todoRepo.updateTodoDescription(id, description);
    }

    public List<TodoItem> getTodoTodos() {
        return todoRepo.getTodoTodos();
    }

    public List<TodoItem> getTodoDoings() {
        return todoRepo.getTodoDoings();
    }

    public List<TodoItem> getTodoDones() {
        return todoRepo.getTodoDones();
    }

}
