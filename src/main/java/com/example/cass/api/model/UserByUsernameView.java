package com.example.cass.api.model;

import java.time.LocalDateTime;

public class UserByUsernameView {
    private String username;
    private String id;
    private String email;
    private LocalDateTime createdAt;
    private String status;

    public UserByUsernameView(String username, String id, String email, LocalDateTime createdAt, String status) {
        this.username = username;
        this.id = id;
        this.email = email;
        this.createdAt = createdAt;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public String getId() {
        return id;
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
