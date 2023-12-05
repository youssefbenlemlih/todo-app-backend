package com.yousefbee.todoappbackend.module.todo.adapter.out;

import com.yousefbee.todoappbackend.module.todo.port.dto.TodoDto;
import com.yousefbee.todoappbackend.module.todo.port.out.TodosPersistence;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TodosPersistenceAdapter implements TodosPersistence {
  List<TodoDto> todos = new ArrayList<>() {
    {
      this.add(TodoDto.builder().id("1").text("One").checked(true).build());
      this.add(TodoDto.builder().id("2").text("Two").checked(false).build());
      this.add(TodoDto.builder().id("3").text("Three").checked(false).build());
    }
  };

  @Override
  public List<TodoDto> getAllTodos() {
    return todos;
  }

  @Override
  public TodoDto createTodo(final String text) {
    var newTodo = TodoDto.builder()
        .id(String.valueOf(todos.size() + 1))
        .text(text)
        .checked(false)
        .build();
    todos.add(newTodo);
    return newTodo;
  }
}
