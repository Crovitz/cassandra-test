package com.example.cass.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class AddUserRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String email;

    @NotBlank
    private String favouriteDay;

    @JsonCreator
    public AddUserRequest(
            @JsonProperty("username") String username,
            @JsonProperty("email") String email,
            @JsonProperty("favouriteDay") String favouriteDay) {
        this.username = username;
        this.email = email;
        this.favouriteDay = favouriteDay;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getFavouriteDay() {
        return favouriteDay;
    }
}
