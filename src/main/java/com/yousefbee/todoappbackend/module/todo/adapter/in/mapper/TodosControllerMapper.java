package com.yousefbee.todoappbackend.module.todo.adapter.in.mapper;

import com.yousefbee.todoappbackend.module.todo.adapter.in.model.TodoResponse;
import com.yousefbee.todoappbackend.module.todo.port.dto.TodoDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TodosControllerMapper {
  public List<TodoResponse> toResponse(List<TodoDto> todos) {
    if (todos == null) {
      return null;
    }
    return todos
        .stream()
        .map(this::toResponse)
        .toList();
  }

  public TodoResponse toResponse(TodoDto todo) {
    if (todo == null) {
      return null;
    }
    return TodoResponse.builder()
        .id(todo.getId())
        .text(todo.getText())
        .checked(todo.isChecked())
        .build();
  }
}
