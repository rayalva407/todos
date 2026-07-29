package com.rayalva407.todos.service;

import com.rayalva407.todos.model.Todo;
import com.rayalva407.todos.model.TodoList;
import com.rayalva407.todos.repository.TodoListRepository;
import com.rayalva407.todos.repository.TodoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final TodoListRepository todoListRepository;

    public TodoService(TodoRepository todoRepository, TodoListRepository todoListRepository) {
        this.todoRepository = todoRepository;
        this.todoListRepository = todoListRepository;
    }

    public Todo createTodo(Long todoListId, Todo todo) {
        TodoList todoList = todoListRepository.findById(todoListId).orElseThrow();

        todo.setTodoList(todoList);

        return todoRepository.save(todo);
    }

    public Todo updateTodo(Todo todoDetails, Long todoId) {
        Todo existingTodo = todoRepository.findById(todoId).orElseThrow(() -> new EntityNotFoundException("Todo not found with id " + todoId));

        if (todoDetails.getTitle() != null) {
            existingTodo.setTitle(todoDetails.getTitle());
        }
        if (todoDetails.getDescription() != null) {
            existingTodo.setDescription(todoDetails.getDescription());
        }
        if (todoDetails.isCompleted() != existingTodo.isCompleted()) {
            existingTodo.setCompleted(todoDetails.isCompleted());
        }

        return todoRepository.save(existingTodo);
    }
}