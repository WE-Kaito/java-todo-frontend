package com.example.backend.toDo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class TodoServiceTest {


    TodoRepo todoRepo = mock(TodoRepo.class);

    TodoService todoService = mock(TodoService.class);

    @Test
    void addTodoItem() {
        TodoItem todoItem = new TodoItem("test", "test", "test");
        todoService.addTodoItem(todoItem);
        Assertions.assertEquals(todoItem, todoRepo.getTodoItem(todoItem.getId()));
        verify(todoService, times(1)).addTodoItem(todoItem);
    }
}