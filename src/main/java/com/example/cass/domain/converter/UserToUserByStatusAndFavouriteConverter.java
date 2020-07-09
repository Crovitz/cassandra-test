package com.example.cass.domain.converter;

import com.example.cass.domain.user.User;
import com.example.cass.domain.user.UserByStatusAndFavouriteDay;
import com.example.cass.domain.user.UserByStatusAndFavouriteDayKey;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserByStatusAndFavouriteConverter implements Converter<User, UserByStatusAndFavouriteDay> {

    @Override
    public UserByStatusAndFavouriteDay convert(User source) {
        return new UserByStatusAndFavouriteDay(
                new UserByStatusAndFavouriteDayKey(source.getStatus(), source.getFavouriteDay(), source.getId()),
                source.getUsername());
    }
}
