package com.example.backend;

import com.example.backend.toDo.TodoItem;
import com.example.backend.toDo.TodoRepo;
import com.example.backend.toDo.TodoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


import java.util.List;
import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BackendIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TodoService todoService;
    @Autowired
    private TodoRepo todoRepo;

    private ObjectMapper objectMapper = new ObjectMapper();


    @Test
    @Order(1)
    void test_getAllToDos() throws Exception {

        TodoItem todo1 = new TodoItem(null,"Aufräumen", "OPEN");
        TodoItem todo2 = new TodoItem(null,"Tee kochen", "IN_PROGRESS");

        todoService.addTodoItem(todo1);
        todoService.addTodoItem(todo2);

        todoRepo.getTodoRepository().get(0).setId("100");
        todoRepo.getTodoRepository().get(1).setId("101");


        String expectedList = objectMapper.writeValueAsString(List.of(
                new TodoItem("100","Aufräumen", "OPEN"),
                new TodoItem("101","Tee kochen", "IN_PROGRESS")
        ));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedList));
    }

    @Test
    @Order(2)
    void test_addToDo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/todo", "12341")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "description": "Training",
                        "status": "OPEN"
                        }""")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
        ;

        todoRepo.getTodoRepository().get(2).setId("102");

        Assertions.assertEquals(todoService.getAllTodos().get(2).getDescription(),"Training");
    }

    @Test
    @Order(3)
    void getTodoById() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo/100"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                        {
                        "id": "100",
                        "description": "Aufräumen",
                        "status": "OPEN"
                        }"""));
    }

    @Test
    @Order(4)
    void updateTodo() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.put("/api/todo/100")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "description": "Aufräumen",
                        "status": "IN_PROGRESS"
                        }""")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
        ;

        Assertions.assertEquals(todoService.getAllTodos().get(0).getStatus(),"IN_PROGRESS");
    }

    @Test
    @Order(5)
    void deleteTodo() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/todo/100"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Assertions.assertEquals(todoService.getAllTodos().size(),2);
        Assertions.assertEquals(todoService.getAllTodos().get(0).getDescription(),"Tee kochen");

    }


}
