package com.rayalva407.todos.controller;

import com.rayalva407.todos.dto.TodoListRequestDto;
import com.rayalva407.todos.model.Todo;
import com.rayalva407.todos.model.TodoList;
import com.rayalva407.todos.service.TodoListService;

import java.util.List;

import com.rayalva407.todos.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/todo-lists")
public class TodoListController {

    private final TodoListService todoListService;
    private final TodoService todoService;

    public TodoListController(TodoListService todoListService, TodoService todoService) {
        this.todoListService = todoListService;
        this.todoService = todoService;
    }

    @GetMapping()
    public ResponseEntity<List<TodoList>> getAllTodoListsByUser(@AuthenticationPrincipal String username) {
        return new ResponseEntity<>(todoListService.findAllByUser(username), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createTodoList(@RequestBody TodoListRequestDto todoListRequestDto, @AuthenticationPrincipal String username) {
        return new ResponseEntity<>(todoListService.createTodoList(todoListRequestDto.title(), username), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TodoList> updateTodoList(@PathVariable Long id, @RequestBody TodoListRequestDto todoListRequestDto) {
        return new ResponseEntity<>(todoListService.updateTodoList(id, todoListRequestDto.title()), HttpStatus.OK);
    }

    @PostMapping("/{todoListId}/todos")
    public ResponseEntity<Todo> createTodo(@PathVariable Long todoListId, @RequestBody Todo todo) {
        return new ResponseEntity<>(todoService.createTodo(todoListId, todo), HttpStatus.CREATED);
    }
}
