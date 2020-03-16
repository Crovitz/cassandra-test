package com.example.cass.domain;

import com.example.cass.domain.user.User;
import com.example.cass.infrastructure.repository.UserByStatusAndFavouriteDayRepository;
import com.example.cass.infrastructure.repository.UserByUsernameRepository;
import com.example.cass.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserEntityServiceImpl implements UserEntityService {
    private final UserRepository userRepository;
    private final UserByUsernameRepository userByUsernameRepository;
    private final UserByStatusAndFavouriteDayRepository userByStatusAndFavouriteDayRepository;

    public UserEntityServiceImpl(UserRepository userRepository, UserByUsernameRepository userByUsernameRepository,
                                 UserByStatusAndFavouriteDayRepository userByStatusAndFavouriteDayRepository) {
        this.userRepository = userRepository;
        this.userByUsernameRepository = userByUsernameRepository;
        this.userByStatusAndFavouriteDayRepository = userByStatusAndFavouriteDayRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userByUsernameRepository.findByKeyUsername(username)
                .map(User::new);
    }

    @Override
    public List<User> findByStatusAndFavouriteDay(User.Status status, LocalDate favouriteDay) {
        return userByStatusAndFavouriteDayRepository.findAllByKeyStatusAndKeyFavouriteDay(status, favouriteDay).stream()
                .map(User::new)
                .collect(Collectors.toList());
    }

    @Override
    public User save(User user) {
        return userRepository.insert(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }
}
