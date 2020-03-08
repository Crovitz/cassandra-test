package com.example.cass.domain;

import com.example.cass.domain.user.User;
import com.example.cass.domain.user.UserByStatusAndFavouriteDay;
import com.example.cass.domain.user.UserByUsername;
import com.example.cass.infrastructure.repository.UserByStatusRepository;
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
    private final UserByStatusRepository userByStatusRepository;

    public UserEntityServiceImpl(UserRepository userRepository, UserByUsernameRepository userByUsernameRepository,
                                 UserByStatusRepository userByStatusRepository) {
        this.userRepository = userRepository;
        this.userByUsernameRepository = userByUsernameRepository;
        this.userByStatusRepository = userByStatusRepository;
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
        return userByUsernameRepository.findByKey_Username(username)
                .map(User::new);
    }

    @Override
    public List<User> findByStatusAndFavouriteDay(User.Status status, LocalDate favouriteDay) {
        return userByStatusRepository.findByKey_StatusAndKey_FavouriteDay(status, favouriteDay).stream()
                .map(User::new)
                .collect(Collectors.toList());
    }

    @Override
    public User save(User user) {
        userByUsernameRepository.save(new UserByUsername(user));
        userByStatusRepository.save(new UserByStatusAndFavouriteDay(user));
        return userRepository.save(user);
    }
}
