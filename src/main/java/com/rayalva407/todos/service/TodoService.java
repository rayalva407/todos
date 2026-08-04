package com.rayalva407.todos.service;

import com.rayalva407.todos.model.Todo;
import com.rayalva407.todos.model.TodoList;
import com.rayalva407.todos.repository.TodoListRepository;
import com.rayalva407.todos.repository.TodoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class TodoService {

    private final TodoRepository todoRepository;
    private final TodoListRepository todoListRepository;

    public TodoService(TodoRepository todoRepository, TodoListRepository todoListRepository) {
        this.todoRepository = todoRepository;
        this.todoListRepository = todoListRepository;
    }

    @Transactional
    public Todo createTodo(Long todoListId, String title, String description) {
        TodoList todoList = todoListRepository.findById(todoListId).orElseThrow();
        Todo todo = new Todo(title, description);

        todo.setTodoList(todoList);

        return todoRepository.save(todo);
    }

    @Transactional
    public Todo updateStatus(Long todoId, boolean completed) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new EntityNotFoundException("Todo not found"));

        todo.setCompleted(completed);

        return todo;
    }

    @Transactional
    public Todo updateTodo(Long todoId, String title, String description) {
        Todo existingTodo = todoRepository.findById(todoId).orElseThrow(() -> new EntityNotFoundException("Todo not found with id " + todoId));

        if (title != null) {
            existingTodo.setTitle(title);
        }
        if (description != null) {
            existingTodo.setDescription(description);
        }

        return existingTodo;
    }
}