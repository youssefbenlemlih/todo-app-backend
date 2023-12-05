package com.yousefbee.todoappbackend.module.todo.port.in;

import com.yousefbee.todoappbackend.module.todo.port.dto.TodoDto;

import java.util.List;

public interface TodosUseCase {
  List<TodoDto> getTodos();
  CreateTodoResult createTodo(String text);

  sealed interface CreateTodoResult {
    record Success(TodoDto createdTodo) implements CreateTodoResult {}
    record InvalidText() implements CreateTodoResult {}
  }

  sealed interface ReorderTodoResult {
    record Success() implements ReorderTodoResult {}
    record TodoNotFound() implements ReorderTodoResult {}
    record IndexNotValid() implements ReorderTodoResult {}
  }

}
