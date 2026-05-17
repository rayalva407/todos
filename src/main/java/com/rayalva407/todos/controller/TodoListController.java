package com.rayalva407.todos.controller;

import com.rayalva407.todos.model.Todo;
import com.rayalva407.todos.model.TodoList;
import com.rayalva407.todos.service.TodoListService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/todo-lists")
public class TodoListController {

    TodoListService todoListService;

    TodoListController(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    @GetMapping()
    public ResponseEntity<List<TodoList>> getAllTodoLists() {
        return new ResponseEntity<>(todoListService.findAll(), HttpStatus.OK);
    }
    

    @PostMapping("/create")
    public ResponseEntity<TodoList> createTodoList(@RequestBody TodoList todoList) {
        return new ResponseEntity<>(todoListService.createTodoList(todoList), HttpStatus.CREATED);
    }

    @PostMapping("/{todoListId}/todos/create")
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo, @PathVariable Long todoListId) {
        return new ResponseEntity<>(todoListService.createTodo(todo, todoListId), HttpStatus.CREATED);
    }

    @PatchMapping("/update")
    public ResponseEntity<TodoList> updateTodoList(@RequestBody TodoList todoListDetails) {
        return new ResponseEntity<>(todoListService.updateTodoList(todoListDetails), HttpStatus.OK);
    }
}
