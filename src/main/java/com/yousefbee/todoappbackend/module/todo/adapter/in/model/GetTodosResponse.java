package com.yousefbee.todoappbackend.module.todo.adapter.in.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class GetTodosResponse {
  List<TodoResponse> todos;
}
