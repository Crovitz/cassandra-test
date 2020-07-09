package com.example.cass.api.converter;

import com.example.cass.api.model.UserView;
import com.example.cass.domain.user.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToViewConverter implements Converter<User, UserView> {

    @Override
    public UserView convert(User source) {
        return new UserView(
                source.getId().toString(),
                source.getUsername(),
                source.getEmail(),
                source.getCreatedAt(),
                source.getStatus().name());
    }
}
