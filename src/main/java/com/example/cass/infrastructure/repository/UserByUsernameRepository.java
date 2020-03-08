package com.example.cass.infrastructure.repository;

import com.example.cass.domain.user.UserByUsername;
import com.example.cass.domain.user.UserByUsernameKey;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.Optional;

public interface UserByUsernameRepository extends CassandraRepository<UserByUsername, UserByUsernameKey> {

    Optional<UserByUsername> findByKey_Username(String username);
}
