package com.yousefbee.todoappbackend.module.todo.adapter.out.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodosRepository extends JpaRepository<TodoEntity, Long> {
}
