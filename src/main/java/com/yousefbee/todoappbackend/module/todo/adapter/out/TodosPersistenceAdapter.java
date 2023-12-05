package com.yousefbee.todoappbackend.module.todo.adapter.out;

import com.yousefbee.todoappbackend.module.todo.adapter.out.mapper.TodosPersistenceMapper;
import com.yousefbee.todoappbackend.module.todo.adapter.out.repository.TodoEntity;
import com.yousefbee.todoappbackend.module.todo.adapter.out.repository.TodosRepository;
import com.yousefbee.todoappbackend.module.todo.port.dto.TodoDto;
import com.yousefbee.todoappbackend.module.todo.port.out.TodosPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TodosPersistenceAdapter implements TodosPersistence {
  private final TodosRepository repository;
  private final TodosPersistenceMapper mapper;

  @Override
  public List<TodoDto> getAllTodos() {
    return repository.findAll()
        .stream()
        .map(mapper::toDto)
        .toList();
  }

  @Override
  public TodoDto createTodo(final String text) {
    TodoEntity savedTodo = repository.save(TodoEntity.builder().text(text).build());
    return mapper.toDto(savedTodo);
  }
}
