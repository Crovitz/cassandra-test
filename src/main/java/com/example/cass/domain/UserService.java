package com.example.cass.domain;

import com.example.cass.api.AddUserRequest;
import com.example.cass.api.model.UserByStatusAndFavouriteDayView;
import com.example.cass.api.model.UserByUsernameView;
import com.example.cass.api.model.UserView;
import com.example.cass.domain.user.User;
import com.example.cass.infrastructure.repository.UserByStatusAndFavouriteDayRepository;
import com.example.cass.infrastructure.repository.UserByUsernameRepository;
import com.example.cass.infrastructure.repository.UserRepository;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserByUsernameRepository userByUsernameRepository;
    private final UserByStatusAndFavouriteDayRepository userByStatusAndFavouriteDayRepository;
    private final ConversionService conversionService;

    public UserService(UserRepository userRepository,
                       UserByUsernameRepository userByUsernameRepository,
                       UserByStatusAndFavouriteDayRepository userByStatusAndFavouriteDayRepository,
                       ConversionService conversionService) {
        this.userRepository = userRepository;
        this.userByUsernameRepository = userByUsernameRepository;
        this.userByStatusAndFavouriteDayRepository = userByStatusAndFavouriteDayRepository;
        this.conversionService = conversionService;
    }

    public List<UserView> getUsers() {
        return userRepository.findAll().stream()
                .map(user -> conversionService.convert(user, UserView.class))
                .collect(Collectors.toList());
    }

    public UserView getById(UUID id) {
        return userRepository.findById(id)
                .map(user -> conversionService.convert(user, UserView.class))
                .orElseThrow(IllegalArgumentException::new);
    }

    public UserByUsernameView getUserByUsername(String username) {
        return userByUsernameRepository.findByKeyUsername(username)
                .map(user -> conversionService.convert(user, UserByUsernameView.class))
                .orElse(null);
    }

    public List<UserByStatusAndFavouriteDayView> getByStatusAndFavouriteDay(User.Status status, LocalDate favouriteDay) {
        return userByStatusAndFavouriteDayRepository.findAllByKeyStatusAndKeyFavouriteDay(status, favouriteDay).stream()
                .map(user -> conversionService.convert(user, UserByStatusAndFavouriteDayView.class))
                .collect(Collectors.toList());
    }

    public UserView addUser(AddUserRequest request) {
        userByUsernameRepository.findByKeyUsername(request.getUsername())
                .ifPresent(user -> {
                    throw new IllegalArgumentException("User already exists");
                });

        User user = userRepository.insert(
                new User(request.getUsername(), request.getEmail(), LocalDate.parse(request.getFavouriteDay())));
        return conversionService.convert(user, UserView.class);
    }

    public void deleteUser(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        userRepository.delete(user);
    }
}
