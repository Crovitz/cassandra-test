package com.example.cass.infrastructure.repository;

import com.example.cass.domain.user.User;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.UUID;

@NoRepositoryBean
public interface UserRepository extends CassandraRepository<User, UUID> {
}
