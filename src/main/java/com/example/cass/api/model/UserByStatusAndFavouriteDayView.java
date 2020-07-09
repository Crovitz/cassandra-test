package com.example.cass.api.model;

import java.time.LocalDate;

public class UserByStatusAndFavouriteDayView {
    private String status;
    private LocalDate favouriteDay;
    private String id;
    private String username;

    public UserByStatusAndFavouriteDayView(String status, LocalDate favouriteDay, String id, String username) {
        this.status = status;
        this.favouriteDay = favouriteDay;
        this.id = id;
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getFavouriteDay() {
        return favouriteDay;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
