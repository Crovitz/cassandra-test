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

@Table("user_by_username")
public class UserByUsername {

    @PrimaryKey
    private UserByUsernameKey key;

    @Column("email")
    private String email;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("status")
    @CassandraType(type = DataType.Name.TEXT)
    private User.Status status;

    @Column("favourite_day")
    private LocalDate favouriteDay;

    public UserByUsername(UUID id, String username, String email, LocalDateTime createdAt, User.Status status, LocalDate favouriteDay) {
        this.key = new UserByUsernameKey(username, id);
        this.email = email;
        this.createdAt = createdAt;
        this.status = status;
        this.favouriteDay = favouriteDay;
    }

    public UserByUsername(User user) {
        this.key = new UserByUsernameKey(user.getUsername(), user.getId());
        this.email = user.getEmail();
        this.createdAt = user.getCreatedAt();
        this.status = user.getStatus();
        this.favouriteDay = user.getFavouriteDay();
    }

    @PersistenceConstructor
    private UserByUsername() {
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

    public LocalDate getFavouriteDay() {
        return favouriteDay;
    }
}
