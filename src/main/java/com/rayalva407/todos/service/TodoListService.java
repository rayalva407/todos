package com.rayalva407.todos.service;

import com.rayalva407.todos.model.TodoList;
import com.rayalva407.todos.repository.TodoListRepository;
import org.springframework.stereotype.Service;

@Service
public class TodoListService {

    TodoListRepository todoListRepository;

    TodoListService(TodoListRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }

    public TodoList createTodoList(TodoList todoList) {
        return todoListRepository.save(todoList);
    }
}
