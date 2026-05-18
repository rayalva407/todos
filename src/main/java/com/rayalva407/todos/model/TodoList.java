package com.rayalva407.todos.model;

import com.fasterxml.jackson.annotation.JsonBackReference; // Import JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class TodoList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @OneToMany(mappedBy = "todoList", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Todo> todos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    public TodoList() {}

    public TodoList(String title) {
        this.title = title;
    }

    public TodoList(String title, User user) {
        this.title = title;
        this.user = user;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public List<Todo> getTodos() { return todos; }
    public void setTodos(List<Todo> todos) { this.todos = todos; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}