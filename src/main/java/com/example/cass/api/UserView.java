package com.example.cass.api;

import com.example.cass.domain.user.User;

import java.time.LocalDateTime;

public class UserView {
    private final String id;
    private final String username;
    private final String email;
    private final LocalDateTime createdAt;
    private final User.Status status;

    public UserView(String id, String username, String email, LocalDateTime createdAt, User.Status status) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.createdAt = createdAt;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public User.Status getStatus() {
        return status;
    }
}
