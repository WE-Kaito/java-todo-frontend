package com.example.backend.toDo;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class TodoController {

    final TodoService todoService;

    @GetMapping
    public List<TodoItem> getAllTodos() {
        return todoService.getAllTodos();
    }
    @PostMapping
    public void addTodoItem(@RequestBody TodoItem todoItem) {
        todoService.addTodoItem(todoItem);
    }

    @GetMapping("/{id}")
    public TodoItem getTodoById(@PathVariable("id") String id) {
        return todoService.getTodoItem(id);
    }

    @PutMapping("/{id}")
    public void updateTodo(@PathVariable("id") String id, @RequestBody TodoItem todoItem) {
        todoService.updateTodoDescription(id, todoItem.getDescription());
        todoService.setStatus(id, todoItem.getStatus());
    }

    @DeleteMapping("/{id}")
    public void deleteTodoItem(@PathVariable("id") String id) {
        todoService.deleteTodoItem(id);
    }


}
