package com.example.cass.configuration;

import com.example.cass.domain.user.User;
import com.example.cass.infrastructure.repository.UserByStatusAndFavouriteDayRepository;
import com.example.cass.infrastructure.repository.UserByUsernameRepository;
import com.example.cass.infrastructure.repository.UserRepository;
import com.example.cass.infrastructure.repository.UserRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.mapping.CassandraPersistentEntity;
import org.springframework.data.cassandra.repository.query.CassandraEntityInformation;
import org.springframework.data.cassandra.repository.support.MappingCassandraEntityInformation;

import java.util.UUID;

@Configuration
public class UserConfiguration {
    private final ConversionService conversionService;

    public UserConfiguration(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

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

        return new UserRepositoryImpl(metadata, cassandraTemplate, userByUsernameRepository, userByStatusAndFavouriteDayRepository, conversionService);
    }
}
