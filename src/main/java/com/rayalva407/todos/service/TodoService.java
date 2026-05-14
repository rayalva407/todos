package com.rayalva407.todos.service;

import com.rayalva407.todos.model.Todo;
import com.rayalva407.todos.repository.TodoRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo updateTodo(Todo todo) {
        return todoRepository.save(todo);
    }
}
