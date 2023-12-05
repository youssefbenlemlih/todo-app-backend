package com.yousefbee.todoappbackend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yousefbee.todoappbackend.module.todo.adapter.in.model.CreateTodoRequest;
import com.yousefbee.todoappbackend.module.todo.adapter.out.repository.TodoEntity;
import com.yousefbee.todoappbackend.module.todo.adapter.out.repository.TodosRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK
)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class TodoAppBackendApplicationTests {
  @Autowired
  TodosRepository todosRepository;
  @Autowired
  MockMvc mockMvc;
  @Autowired
  ObjectMapper objectMapper;

  @BeforeEach
  public void setUp() throws Exception {
    todosRepository.deleteAll();
    todosRepository.flush();
  }

  @Test
  void contextLoads() {
  }

  @Test
  void createTodo() throws Exception {
    todosRepository.save(TodoEntity.builder().id(1L).text("1").checked(false).build());
    var newTodoText = "New todo text";

    var postBody = objectMapper.writeValueAsString(CreateTodoRequest.builder().text(newTodoText).build());
    mockMvc.perform(post("/todos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(postBody))
        .andExpect(status().isCreated());

    mockMvc.perform(get("/todos"))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("todos.[0].id").value(1L))
        .andExpect(jsonPath("todos.[0].text").value("1"))
        .andExpect(jsonPath("todos.[0].checked").value(false))
        .andExpect(jsonPath("todos.[1].id").value(2L))
        .andExpect(jsonPath("todos.[1].text").value(newTodoText))
        .andExpect(jsonPath("todos.[1].checked").value(false));
  }

  @Test
  void getAllTodos() throws Exception {
    todosRepository.saveAll(
        List.of(TodoEntity.builder().id(1L).text("1").checked(false).build(),
            TodoEntity.builder().id(2L).text("2").checked(true).build())
    );
    mockMvc.perform(get("/todos"))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("todos.[0].id").value(1L))
        .andExpect(jsonPath("todos.[0].text").value("1"))
        .andExpect(jsonPath("todos.[0].checked").value(false))
        .andExpect(jsonPath("todos.[1].id").value(2L))
        .andExpect(jsonPath("todos.[1].text").value("2"))
        .andExpect(jsonPath("todos.[1].checked").value(true));
  }

}
