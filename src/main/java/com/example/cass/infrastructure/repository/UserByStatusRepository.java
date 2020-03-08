package com.example.cass.infrastructure.repository;

import com.example.cass.domain.user.User;
import com.example.cass.domain.user.UserByStatusAndFavouriteDay;
import com.example.cass.domain.user.UserByStatusAndFavouriteDayKey;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.time.LocalDate;
import java.util.List;

public interface UserByStatusRepository extends CassandraRepository<UserByStatusAndFavouriteDay, UserByStatusAndFavouriteDayKey> {

    List<UserByStatusAndFavouriteDay> findByKey_StatusAndKey_FavouriteDay(User.Status status, LocalDate favouriteDay);
}
