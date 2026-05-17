package com.rayalva407.todos.service;

import com.rayalva407.todos.model.Todo;
import com.rayalva407.todos.model.TodoList;
import com.rayalva407.todos.repository.TodoListRepository;

import java.util.List;
import java.util.Optional;

import com.rayalva407.todos.repository.TodoRepository;
import org.springframework.stereotype.Service;

@Service
public class TodoListService {

    TodoListRepository todoListRepository;
    TodoRepository todoRepository;

    TodoListService(TodoListRepository todoListRepository, TodoRepository todoRepository) {
        this.todoListRepository = todoListRepository;
        this.todoRepository = todoRepository;
    }

    public List<TodoList> findAll() {
        return todoListRepository.findAll();
    }

    public TodoList createTodoList(TodoList todoList) {
        return todoListRepository.save(todoList);
    }

    public TodoList updateTodoList(TodoList todoList) {
        Optional<TodoList> unverifiedTodoList = todoListRepository.findById(todoList.getId());
        TodoList existingTodoList = unverifiedTodoList.orElseThrow();

        if (todoList.getTitle() != null) {
            existingTodoList.setTitle(todoList.getTitle());
        }

        return todoListRepository.save(existingTodoList);
    }

    public Todo createTodo(Todo todo, Long todoListId) {
        TodoList todoList = todoListRepository.findById(todoListId).orElseThrow();
        todo.setTodoList(todoList);
        return todoRepository.save(todo);
    }
}
