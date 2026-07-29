package com.rayalva407.todos.service;

import com.rayalva407.todos.model.TodoList;
import com.rayalva407.todos.model.User;
import com.rayalva407.todos.repository.TodoListRepository;

import java.util.List;

import com.rayalva407.todos.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class TodoListService {

    private final TodoListRepository todoListRepository;
    private final UserRepository userRepository;

    public TodoListService(TodoListRepository todoListRepository, UserRepository userRepository) {
        this.todoListRepository = todoListRepository;
        this.userRepository = userRepository;
    }

    public List<TodoList> findAllByUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        return user.getTodoLists();
    }

    @Transactional
    public TodoList createTodoList(String title, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        TodoList todoList = new TodoList(title);

        todoList.setUser(user);

        return todoListRepository.save(todoList);
    }

    @Transactional
    public TodoList updateTodoList(Long id, String title) {
        TodoList existingTodoList = todoListRepository.findById(id).orElseThrow();

        if (title != null) {
            existingTodoList.setTitle(title);
        }

        return existingTodoList;
    }


}
