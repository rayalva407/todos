package com.rayalva407.todos.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    private boolean completed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_list_id", nullable = false)
    @JsonBackReference
    private TodoList todoList;

    public Todo() {}

    public Todo(String title, String description) {
        this.title = title;
        this.description = description;
        this.completed = false;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean getStatus() { return completed; }
    public void setStatus(boolean completed) { this.completed = completed; }

    public TodoList getTodoList() { return todoList; }
    public void setTodoList(TodoList todoList) { this.todoList = todoList; }
}