package com.example.cass.domain.user;

import com.datastax.driver.core.DataType;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.time.LocalDate;
import java.util.UUID;

@PrimaryKeyClass
public class UserByStatusAndFavouriteDayKey {

    @PrimaryKeyColumn(name = "status", type = PrimaryKeyType.PARTITIONED)
    @CassandraType(type = DataType.Name.TEXT)
    private User.Status status;

    @PrimaryKeyColumn(value = "favourite_day", type = PrimaryKeyType.PARTITIONED)
    private LocalDate favouriteDay;

    @PrimaryKeyColumn(name = "id", type = PrimaryKeyType.CLUSTERED)
    private UUID id;

    public UserByStatusAndFavouriteDayKey(User.Status status, LocalDate favouriteDay, UUID id) {
        this.status = status;
        this.favouriteDay = favouriteDay;
        this.id = id;
    }

    public User.Status getStatus() {
        return status;
    }

    public LocalDate getFavouriteDay() {
        return favouriteDay;
    }

    public UUID getId() {
        return id;
    }
}
