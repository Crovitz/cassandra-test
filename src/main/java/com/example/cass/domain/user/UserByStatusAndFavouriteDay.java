package com.example.cass.domain.user;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Table("user_by_status_and_favourite_day")
public class UserByStatusAndFavouriteDay {

    @PrimaryKey
    private UserByStatusAndFavouriteDayKey key;

    @Column("username")
    private String username;

    @Column("email")
    private String email;

    @Column("created_at")
    private LocalDateTime createdAt;

    public UserByStatusAndFavouriteDay(UUID id, String username, String email, LocalDateTime createdAt, User.Status status, LocalDate favouriteDay) {
        this.key = new UserByStatusAndFavouriteDayKey(status, favouriteDay, id);
        this.username = username;
        this.email = email;
        this.createdAt = createdAt;
    }

    public UserByStatusAndFavouriteDay(User user) {
        this.key = new UserByStatusAndFavouriteDayKey(user.getStatus(), user.getFavouriteDay(), user.getId());
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.createdAt = user.getCreatedAt();
    }

    @PersistenceConstructor
    private UserByStatusAndFavouriteDay() {
    }

    public UserByStatusAndFavouriteDayKey getKey() {
        return key;
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
}
