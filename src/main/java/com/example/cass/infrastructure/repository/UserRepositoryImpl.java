package com.example.cass.infrastructure.repository;

import com.example.cass.domain.user.User;
import com.example.cass.domain.user.UserByStatusAndFavouriteDay;
import com.example.cass.domain.user.UserByUsername;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.cassandra.core.CassandraBatchOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.repository.query.CassandraEntityInformation;
import org.springframework.data.cassandra.repository.support.SimpleCassandraRepository;

import java.util.UUID;

public class UserRepositoryImpl extends SimpleCassandraRepository<User, UUID> implements UserRepository {
    private final UserByUsernameRepository userByUsernameRepository;
    private final UserByStatusAndFavouriteDayRepository userByStatusAndFavouriteDayRepository;
    private final ConversionService conversionService;
    private final CassandraBatchOperations batchOps;

    public UserRepositoryImpl(CassandraEntityInformation<User, UUID> metadata,
                              CassandraTemplate cassandraTemplate,
                              UserByUsernameRepository userByUsernameRepository,
                              UserByStatusAndFavouriteDayRepository userByStatusAndFavouriteDayRepository,
                              ConversionService conversionService) {
        super(metadata, cassandraTemplate);
        this.userByUsernameRepository = userByUsernameRepository;
        this.userByStatusAndFavouriteDayRepository = userByStatusAndFavouriteDayRepository;
        this.conversionService = conversionService;
        this.batchOps = cassandraTemplate.batchOps();
    }

    @Override
    public <S extends User> S insert(S user) {
        batchOps.insert(conversionService.convert(user, UserByUsername.class));
        batchOps.insert(conversionService.convert(user, UserByStatusAndFavouriteDay.class));
        batchOps.insert(user);
        batchOps.execute();
        return user;
    }

    @Override
    public void delete(User user) {
        batchOps.delete(userByUsernameRepository.findByKeyUsername(user.getUsername()).orElse(null));
        batchOps.delete(userByStatusAndFavouriteDayRepository.findByKeyStatusAndKeyFavouriteDayAndKeyId(
                user.getStatus(), user.getFavouriteDay(), user.getId()).orElse(null));
        batchOps.delete(user);
        batchOps.execute();
    }
}
