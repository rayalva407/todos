package com.rayalva407.todos.controller;

import com.rayalva407.todos.dto.TodoRequestDto;
import com.rayalva407.todos.dto.TodoStatusDto;
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

    @PatchMapping("/{id}/status")
    public ResponseEntity<Todo> toggleTodoStatus(@PathVariable Long id, @RequestBody TodoStatusDto todoStatusDto) {
        return new ResponseEntity<>(todoService.updateStatus(id, todoStatusDto.completed()), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody TodoRequestDto todoRequestDto) {
        return new ResponseEntity<>(todoService.updateTodo(id, todoRequestDto.title(), todoRequestDto.description()), HttpStatus.OK);
    }
}
