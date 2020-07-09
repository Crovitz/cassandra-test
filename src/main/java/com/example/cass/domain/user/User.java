package com.example.cass.domain.user;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Table("user")
public class User {

    @PrimaryKey
    private UUID id;

    @Column("username")
    private String username;

    @Column("email")
    private String email;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("status")
    private Status status;

    @Column("favourite_day")
    private LocalDate favouriteDay;

    public enum Status {
        ACTIVE,
        BLOCKED
    }

    public User(String username, String email, LocalDate favouriteDay) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.email = email;
        this.createdAt = LocalDateTime.now();
        this.status = Status.ACTIVE;
        this.favouriteDay = favouriteDay;
    }

    public UUID getId() {
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

    public Status getStatus() {
        return status;
    }

    public LocalDate getFavouriteDay() {
        return favouriteDay;
    }
}
