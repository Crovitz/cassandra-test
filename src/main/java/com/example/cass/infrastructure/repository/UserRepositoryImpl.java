package com.example.cass.infrastructure.repository;

import com.example.cass.domain.user.User;
import com.example.cass.domain.user.UserByStatusAndFavouriteDay;
import com.example.cass.domain.user.UserByUsername;
import org.springframework.data.cassandra.core.CassandraBatchOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.repository.query.CassandraEntityInformation;
import org.springframework.data.cassandra.repository.support.SimpleCassandraRepository;

import java.util.UUID;

public class UserRepositoryImpl extends SimpleCassandraRepository<User, UUID> implements UserRepository {
    private final CassandraTemplate cassandraTemplate;
    private final UserByUsernameRepository userByUsernameRepository;
    private final UserByStatusAndFavouriteDayRepository userByStatusAndFavouriteDayRepository;

    public UserRepositoryImpl(CassandraEntityInformation<User, UUID> metadata, CassandraTemplate cassandraTemplate,
                              UserByUsernameRepository userByUsernameRepository,
                              UserByStatusAndFavouriteDayRepository userByStatusAndFavouriteDayRepository) {
        super(metadata, cassandraTemplate);
        this.cassandraTemplate = cassandraTemplate;
        this.userByUsernameRepository = userByUsernameRepository;
        this.userByStatusAndFavouriteDayRepository = userByStatusAndFavouriteDayRepository;
    }

    @Override
    public <S extends User> S insert(S user) {
        CassandraBatchOperations batchOps = cassandraTemplate.batchOps();
        insertByUsername(user, batchOps);
        insertByStatusAndFavouriteDay(user, batchOps);
        batchOps.insert(user);
        batchOps.execute();
        return user;
    }

    @Override
    public void delete(User user) {
        CassandraBatchOperations batchOps = cassandraTemplate.batchOps();
        deleteByUsername(user, batchOps);
        deleteByStatusAndFavouriteDay(user, batchOps);
        batchOps.delete(user);
        batchOps.execute();
    }

    private void insertByUsername(User user, CassandraBatchOperations batchOps) {
        batchOps.insert(new UserByUsername(user));
    }

    private void insertByStatusAndFavouriteDay(User user, CassandraBatchOperations batchOps) {
        batchOps.insert(new UserByStatusAndFavouriteDay(user));
    }

    private void deleteByUsername(User user, CassandraBatchOperations batchOps) {
        batchOps.delete(userByUsernameRepository.findByKeyUsername(user.getUsername()).orElse(null));
    }

    private void deleteByStatusAndFavouriteDay(User user, CassandraBatchOperations batchOps) {
        batchOps.delete(userByStatusAndFavouriteDayRepository.findByKeyStatusAndKeyFavouriteDayAndKeyId(
                user.getStatus(), user.getFavouriteDay(), user.getId()).orElse(null));
    }
}
