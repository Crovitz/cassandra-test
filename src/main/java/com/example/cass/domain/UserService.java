package com.example.cass.domain;

import com.example.cass.api.AddUserRequest;
import com.example.cass.api.UserView;
import com.example.cass.domain.user.User;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserEntityService userEntityService;
    private final ConversionService conversionService;

    public UserService(UserEntityService userEntityService, ConversionService conversionService) {
        this.userEntityService = userEntityService;
        this.conversionService = conversionService;
    }

    public List<UserView> getUsers() {
        return userEntityService.findAll().stream()
                .map(user -> conversionService.convert(user, UserView.class))
                .collect(Collectors.toList());
    }

    public UserView getById(UUID id) {
        return userEntityService.findById(id)
                .map(user -> conversionService.convert(user, UserView.class))
                .orElseThrow(IllegalArgumentException::new);
    }

    public UserView getUserByUsername(String username) {
        return userEntityService.findByUsername(username)
                .map(user -> conversionService.convert(user, UserView.class))
                .orElse(null);
    }

    public List<UserView> getByStatusAndFavouriteDay(User.Status status, LocalDate favouriteDay) {
        return userEntityService.findByStatusAndFavouriteDay(status, favouriteDay).stream()
                .map(user -> conversionService.convert(user, UserView.class))
                .collect(Collectors.toList());
    }

    public UserView addUser(AddUserRequest request) {
        User user = userEntityService.save(new User(request.getUsername(), request.getEmail(), LocalDate.parse(request.getFavouriteDay())));
        return conversionService.convert(user, UserView.class);
    }
}
