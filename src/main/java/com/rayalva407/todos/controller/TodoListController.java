package com.rayalva407.todos.controller;

import com.rayalva407.todos.model.TodoList;
import com.rayalva407.todos.service.TodoListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/todo-lists")
public class TodoListController {

    TodoListService todoListService;

    TodoListController(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    @PostMapping("/create")
    public ResponseEntity<TodoList> createTodoList(@RequestBody TodoList todoList) {
        return new ResponseEntity<>(todoListService.createTodoList(todoList), HttpStatus.CREATED);
    }
}
