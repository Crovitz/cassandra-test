package com.example.cass.infrastructure.repository;

import com.example.cass.domain.user.User;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface UserRepository extends CassandraRepository<User, UUID> {
}
