package com.yousefbee.todoappbackend.module.todo.port.out;

import com.yousefbee.todoappbackend.module.todo.port.dto.TodoDto;

import java.util.List;

public interface TodosPersistence {
  List<TodoDto> getAllTodos();
  TodoDto createTodo(String text);
}
