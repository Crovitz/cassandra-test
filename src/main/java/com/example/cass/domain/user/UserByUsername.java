package com.example.cass.domain.user;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;

@Table("user_by_username")
public class UserByUsername {

    @PrimaryKey
    private UserByUsernameKey key;

    @Column("email")
    private String email;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("status")
    private User.Status status;

    public UserByUsername(UserByUsernameKey key, String email, LocalDateTime createdAt, User.Status status) {
        this.key = key;
        this.email = email;
        this.createdAt = createdAt;
        this.status = status;
    }

    public UserByUsernameKey getKey() {
        return key;
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
