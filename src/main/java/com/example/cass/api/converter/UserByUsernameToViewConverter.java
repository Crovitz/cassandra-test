package com.example.cass.api.converter;

import com.example.cass.api.model.UserByUsernameView;
import com.example.cass.domain.user.UserByUsername;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserByUsernameToViewConverter implements Converter<UserByUsername, UserByUsernameView> {

    @Override
    public UserByUsernameView convert(UserByUsername source) {
        return new UserByUsernameView(
                source.getKey().getUsername(),
                source.getKey().getId().toString(),
                source.getEmail(),
                source.getCreatedAt(),
                source.getStatus().name());
    }
}
