package com.yousefbee.todoappbackend.module.todo.core;

import com.yousefbee.todoappbackend.module.todo.port.dto.TodoDto;
import com.yousefbee.todoappbackend.module.todo.port.in.TodosUseCase;
import com.yousefbee.todoappbackend.module.todo.port.out.TodosPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TodosServiceTest {

  @BeforeEach
  void setUp() {
  }

  @Test
  void getTodos_empty() {
    var persistence = mock(TodosPersistence.class);
    when(persistence.getAllTodos()).thenReturn(List.of());
    TodosService service = new TodosService(persistence);

    List<TodoDto> todos = service.getTodos();

    assertEquals(todos.size(), 0);
  }

  @Test
  void createTodo_but_empty_text() {
    var persistence = mock(TodosPersistence.class);

    TodosService service = new TodosService(persistence);

    TodosUseCase.CreateTodoResult creationResult = service.createTodo("");

    assertInstanceOf(TodosUseCase.CreateTodoResult.InvalidText.class, creationResult);
  }

  @Test
  void createTodo_success() {
    var persistence = mock(TodosPersistence.class);

    TodosService service = new TodosService(persistence);
    when(persistence.createTodo("Text")).thenReturn(TodoDto.builder()
        .id("1")
        .checked(false)
        .text("Text")
        .build());

    TodosUseCase.CreateTodoResult creationResult = service.createTodo("Text");

    verify(persistence).createTodo("Text");

    assertInstanceOf(TodosUseCase.CreateTodoResult.Success.class, creationResult);
    var creationResultSuccess = (TodosUseCase.CreateTodoResult.Success) creationResult;
    assertNotNull(creationResultSuccess.createdTodo());
    assertEquals("Text", creationResultSuccess.createdTodo().getText());
    assertNotNull(creationResultSuccess.createdTodo().getId());
    assertFalse(creationResultSuccess.createdTodo().isChecked());
  }
}