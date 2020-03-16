package com.example.cass.domain.user;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.util.UUID;

@PrimaryKeyClass
public class UserByUsernameKey {

    @PrimaryKeyColumn(name = "username", type = PrimaryKeyType.PARTITIONED)
    private String username;

    @PrimaryKeyColumn(name = "id", ordering = Ordering.DESCENDING)
    private UUID id;

    public UserByUsernameKey(String username, UUID id) {
        this.username = username;
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public UUID getId() {
        return id;
    }
}
