package com.rayalva407.todos.repository;

import com.rayalva407.todos.model.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoListRepository extends JpaRepository<TodoList, Long> {
    Optional<TodoList> findByIdAndUserUsername(Long id, String username);
}
