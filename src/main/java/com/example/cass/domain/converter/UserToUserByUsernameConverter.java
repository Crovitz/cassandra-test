package com.example.cass.domain.converter;

import com.example.cass.domain.user.User;
import com.example.cass.domain.user.UserByUsername;
import com.example.cass.domain.user.UserByUsernameKey;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserByUsernameConverter implements Converter<User, UserByUsername> {

    @Override
    public UserByUsername convert(User source) {
        return new UserByUsername(
                new UserByUsernameKey(source.getUsername(), source.getId()),
                source.getEmail(),
                source.getCreatedAt(),
                source.getStatus());
    }
}
