package com.example.cass.infrastructure.repository;

import com.example.cass.domain.user.UserByUsername;
import com.example.cass.domain.user.UserByUsernameKey;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserByUsernameRepository extends CassandraRepository<UserByUsername, UserByUsernameKey> {

    Optional<UserByUsername> findByKeyUsername(String username);
}
