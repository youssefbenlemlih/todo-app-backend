package com.yousefbee.todoappbackend.module.todo.port.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TodoDto {
  String id;
  String text;
  boolean checked;
}
