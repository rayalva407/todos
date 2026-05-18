package com.rayalva407.todos.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.List; // Using List for TodoList as per existing TodoList setup

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<TodoList> todoLists = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<String> roles = new HashSet<>();


    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.roles.add("ROLE_USER"); // Default role
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<TodoList> getTodoLists() {
        return todoLists;
    }

    public void setTodoLists(Set<TodoList> todoLists) {
        this.todoLists = todoLists;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public void addTodoList(TodoList todoList) {
        this.todoLists.add(todoList);
        todoList.setUser(this);
    }

    public void removeTodoList(TodoList todoList) {
        this.todoLists.remove(todoList);
        todoList.setUser(null);
    }
}
