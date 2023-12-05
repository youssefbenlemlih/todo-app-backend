package com.yousefbee.todoappbackend.module.todo.adapter.out.repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "todo")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column
  String text;
  @Column
  boolean checked;
}
