package com.rayalva407.todos.service;

import com.rayalva407.todos.model.Todo;
import com.rayalva407.todos.repository.TodoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo updateTodo(Todo todoDetails, Long todoId) {
        Optional<Todo> unverifiedTodo = todoRepository.findById(todoId);
        Todo existingTodo = unverifiedTodo.orElseThrow(() -> new EntityNotFoundException("Todo not found with id " + todoId));

        if (todoDetails.getTitle() != null) {
            existingTodo.setTitle(todoDetails.getTitle());
        }
        if (todoDetails.getDescription() != null) {
            existingTodo.setDescription(todoDetails.getDescription());
        }
        if (todoDetails.getStatus() != existingTodo.getStatus()) {
            existingTodo.setStatus(todoDetails.getStatus());
        }

        return todoRepository.save(existingTodo);
    }
}