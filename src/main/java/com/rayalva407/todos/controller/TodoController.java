package com.rayalva407.todos.controller;

import com.rayalva407.todos.model.Todo;
import com.rayalva407.todos.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@RequestBody Todo todoDetails, @PathVariable Long id) {
        return new ResponseEntity<>(todoService.updateTodo(todoDetails, id), HttpStatus.OK);
    }
}
