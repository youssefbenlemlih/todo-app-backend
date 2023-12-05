package com.yousefbee.todoappbackend.module.todo.adapter.out.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoEntity {
  @Id
  private Long id;
  @Column
  String text;
  @Column
  boolean checked;
}
