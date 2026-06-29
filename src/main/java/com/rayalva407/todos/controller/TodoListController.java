package com.rayalva407.todos.controller;

import com.rayalva407.todos.model.TodoList;
import com.rayalva407.todos.service.TodoListService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/todo-lists")
public class TodoListController {

    private final TodoListService todoListService;

    public TodoListController(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    @GetMapping()
    public ResponseEntity<List<TodoList>> getAllTodoLists() {
        return new ResponseEntity<>(todoListService.findAll(), HttpStatus.OK);
    }
    

    @PostMapping
    public ResponseEntity<?> createTodoList(@RequestBody TodoList todoList, @AuthenticationPrincipal String username) {
        return new ResponseEntity<>(todoListService.createTodoList(todoList, username), HttpStatus.CREATED);
    }

    @PatchMapping("/update")
    public ResponseEntity<TodoList> updateTodoList(@RequestBody TodoList todoListDetails) {
        return new ResponseEntity<>(todoListService.updateTodoList(todoListDetails), HttpStatus.OK);
    }
}
