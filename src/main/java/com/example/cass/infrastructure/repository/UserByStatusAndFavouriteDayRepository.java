package com.example.cass.infrastructure.repository;

import com.example.cass.domain.user.User;
import com.example.cass.domain.user.UserByStatusAndFavouriteDay;
import com.example.cass.domain.user.UserByStatusAndFavouriteDayKey;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserByStatusAndFavouriteDayRepository extends CassandraRepository<UserByStatusAndFavouriteDay, UserByStatusAndFavouriteDayKey> {

    Optional<UserByStatusAndFavouriteDay> findByKeyStatusAndKeyFavouriteDayAndKeyId(User.Status status, LocalDate favouriteDay, UUID id);

    List<UserByStatusAndFavouriteDay> findAllByKeyStatusAndKeyFavouriteDay(User.Status status, LocalDate favouriteDay);
}
