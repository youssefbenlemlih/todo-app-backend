package com.yousefbee.todoappbackend.module.todo.adapter.in.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateTodoResponse {
  TodoResponse createdTodo;
}
