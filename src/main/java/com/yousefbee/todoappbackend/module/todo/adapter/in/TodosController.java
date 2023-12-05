package com.yousefbee.todoappbackend.module.todo.adapter.in;

import com.yousefbee.todoappbackend.module.todo.adapter.in.mapper.TodosControllerMapper;
import com.yousefbee.todoappbackend.module.todo.adapter.in.model.CreateTodoRequest;
import com.yousefbee.todoappbackend.module.todo.adapter.in.model.CreateTodoResponse;
import com.yousefbee.todoappbackend.module.todo.adapter.in.model.GetTodosResponse;
import com.yousefbee.todoappbackend.module.todo.port.dto.TodoDto;
import com.yousefbee.todoappbackend.module.todo.port.in.TodosUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodosController {
  private final TodosUseCase todosUseCase;
  private final TodosControllerMapper mapper;

  @GetMapping("/todos")
  ResponseEntity<GetTodosResponse> getTodos() {
    List<TodoDto> todos = todosUseCase.getTodos();
    var body = GetTodosResponse.builder().todos(mapper.toResponse(todos)).build();
    return ResponseEntity.ok(body);
  }

  @PostMapping("/todos")
  ResponseEntity<CreateTodoResponse> createTodo(@RequestBody @Valid CreateTodoRequest request) {
    TodosUseCase.CreateTodoResult result = todosUseCase.createTodo(request.getText());
    if (result instanceof TodosUseCase.CreateTodoResult.Success success) {
      var body = CreateTodoResponse.builder().createdTodo(mapper.toResponse(success.createdTodo())).build();
      return ResponseEntity.ok(body);
    } else if (result instanceof TodosUseCase.CreateTodoResult.InvalidText) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    } else {
      return ResponseEntity.internalServerError().build();
    }

  }
}
