package com.example.cass.domain.user;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("user_by_status_and_favourite_day")
public class UserByStatusAndFavouriteDay {

    @PrimaryKey
    private UserByStatusAndFavouriteDayKey key;

    @Column("username")
    private String username;

    public UserByStatusAndFavouriteDay(UserByStatusAndFavouriteDayKey key, String username) {
        this.key = key;
        this.username = username;
    }

    public UserByStatusAndFavouriteDayKey getKey() {
        return key;
    }

    public String getUsername() {
        return username;
    }
}
