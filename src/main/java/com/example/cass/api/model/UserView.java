package com.example.cass.api.model;

import java.time.LocalDateTime;

public class UserView {
    private final String id;
    private final String username;
    private final String email;
    private final LocalDateTime createdAt;
    private final String status;

    public UserView(String id, String username, String email, LocalDateTime createdAt, String status) {
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

    public String getStatus() {
        return status;
    }
}
