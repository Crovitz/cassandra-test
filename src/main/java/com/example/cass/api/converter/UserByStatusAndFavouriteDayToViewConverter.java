package com.example.cass.api.converter;

import com.example.cass.api.model.UserByStatusAndFavouriteDayView;
import com.example.cass.domain.user.UserByStatusAndFavouriteDay;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserByStatusAndFavouriteDayToViewConverter implements Converter<UserByStatusAndFavouriteDay, UserByStatusAndFavouriteDayView> {

    @Override
    public UserByStatusAndFavouriteDayView convert(UserByStatusAndFavouriteDay source) {
        return new UserByStatusAndFavouriteDayView(
                source.getKey().getStatus().name(),
                source.getKey().getFavouriteDay(),
                source.getKey().getId().toString(),
                source.getUsername());
    }
}
