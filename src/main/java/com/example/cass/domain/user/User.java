package com.example.cass.domain.user;

import com.datastax.driver.core.DataType;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.cassandra.core.mapping.CassandraType;
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
    @CassandraType(type = DataType.Name.TEXT)
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

    public User(UserByUsername userByUsername) {
        this.id = userByUsername.getKey().getId();
        this.username = userByUsername.getKey().getUsername();
        this.email = userByUsername.getEmail();
        this.createdAt = userByUsername.getCreatedAt();
        this.status = userByUsername.getStatus();
        this.favouriteDay = favouriteDay;
    }

    public User(UserByStatusAndFavouriteDay userByStatusAndFavouriteDay) {
        this.id = userByStatusAndFavouriteDay.getKey().getId();
        this.username = userByStatusAndFavouriteDay.getUsername();
        this.email = userByStatusAndFavouriteDay.getEmail();
        this.createdAt = userByStatusAndFavouriteDay.getCreatedAt();
        this.status = userByStatusAndFavouriteDay.getKey().getStatus();
    }

    @PersistenceConstructor
    private User() {
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
