package com.rayalva407.todos.service;

import com.rayalva407.todos.model.Todo;
import com.rayalva407.todos.model.TodoList;
import com.rayalva407.todos.model.User;
import com.rayalva407.todos.repository.TodoListRepository;

import java.util.List;
import java.util.Optional;

import com.rayalva407.todos.repository.TodoRepository;
import com.rayalva407.todos.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class TodoListService {

    private final TodoListRepository todoListRepository;
    private final UserRepository userRepository;

    public TodoListService(TodoListRepository todoListRepository, UserRepository userRepository) {
        this.todoListRepository = todoListRepository;
        this.userRepository = userRepository;
    }

    public List<TodoList> findAll() {
        return todoListRepository.findAll();
    }

    public TodoList createTodoList(TodoList todoList, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));

        todoList.setUser(user);

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


}
