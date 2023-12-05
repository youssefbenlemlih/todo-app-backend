package com.yousefbee.todoappbackend.module.todo.core;

import com.yousefbee.todoappbackend.module.todo.port.dto.TodoDto;
import com.yousefbee.todoappbackend.module.todo.port.in.TodosUseCase;
import com.yousefbee.todoappbackend.module.todo.port.out.TodosPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodosService implements TodosUseCase {
  private final TodosPersistence todosPersistence;

  @Override
  public List<TodoDto> getTodos() {
    return todosPersistence.getAllTodos();
  }

  @Override
  public CreateTodoResult createTodo(final String text) {
    if (!StringUtils.hasText(text)) {
      return new CreateTodoResult.InvalidText();
    } else {
      var todo = todosPersistence.createTodo(text);
      return new CreateTodoResult.Success(todo);
    }
  }
}
