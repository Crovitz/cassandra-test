package com.example.cass.domain;

import com.example.cass.domain.user.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserEntityService {

    List<User> findAll();

    Optional<User> findById(UUID id);

    Optional<User> findByUsername(String username);

    List<User> findByStatusAndFavouriteDay(User.Status status, LocalDate favouriteDay);

    User save(User user);
}
