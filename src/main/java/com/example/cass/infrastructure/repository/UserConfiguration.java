package com.example.cass.infrastructure.repository;

import com.example.cass.domain.user.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.mapping.CassandraPersistentEntity;
import org.springframework.data.cassandra.repository.query.CassandraEntityInformation;
import org.springframework.data.cassandra.repository.support.MappingCassandraEntityInformation;

import java.util.UUID;

@Configuration
public class UserConfiguration {

    @Bean
    public UserRepository userRepository(CassandraTemplate cassandraTemplate,
                                         UserByUsernameRepository userByUsernameRepository,
                                         UserByStatusAndFavouriteDayRepository userByStatusAndFavouriteDayRepository) {
        CassandraPersistentEntity<?> entity = cassandraTemplate
                .getConverter()
                .getMappingContext()
                .getRequiredPersistentEntity(User.class);
        CassandraEntityInformation<User, UUID> metadata = new MappingCassandraEntityInformation<>(
                (CassandraPersistentEntity<User>) entity, cassandraTemplate.getConverter());

        return new UserRepositoryImpl(metadata, cassandraTemplate, userByUsernameRepository, userByStatusAndFavouriteDayRepository);
    }
}
