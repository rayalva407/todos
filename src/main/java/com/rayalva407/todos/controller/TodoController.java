package com.rayalva407.todos.controller;

import com.rayalva407.todos.model.Todo;
import com.rayalva407.todos.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.http.HttpResponse;

@Controller
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PutMapping("/todo/{todoId}")
    public ResponseEntity<Todo> updateTodo(Todo todo, @PathVariable Long todoId) {
        return new ResponseEntity<>(todoService.updateTodo(todo), HttpStatus.OK);
    }
}
