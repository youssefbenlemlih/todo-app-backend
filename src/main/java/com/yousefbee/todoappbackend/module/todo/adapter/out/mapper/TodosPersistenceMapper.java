package com.yousefbee.todoappbackend.module.todo.adapter.out.mapper;

import com.yousefbee.todoappbackend.module.todo.adapter.out.repository.TodoEntity;
import com.yousefbee.todoappbackend.module.todo.port.dto.TodoDto;
import org.springframework.stereotype.Component;

@Component
public class TodosPersistenceMapper {
  public TodoDto toDto(TodoEntity dto) {
    return TodoDto.builder()
        .id((String.valueOf(dto.getId())))
        .checked(dto.isChecked())
        .text(dto.getText())
        .build();
  }

  public TodoEntity toEntity(TodoDto dto) {
    return TodoEntity.builder()
        .id(Long.valueOf(dto.getId()))
        .checked(dto.isChecked())
        .text(dto.getText())
        .build();
  }
}
