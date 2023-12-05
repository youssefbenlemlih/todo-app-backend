package com.yousefbee.todoappbackend.module.todo.adapter.in.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateTodoRequest {
  @NotBlank
  String text;
}
