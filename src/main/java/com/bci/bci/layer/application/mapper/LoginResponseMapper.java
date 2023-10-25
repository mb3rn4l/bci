package com.bci.bci.layer.application.mapper;

import com.bci.bci.layer.application.dto.LoginUserResponse;
import com.bci.bci.layer.domain.model.User;
import org.springframework.stereotype.Component;

@Component
public class LoginResponseMapper {

    public LoginUserResponse toResponse(final User user) {

        return LoginUserResponse.builder()
                .id(user.getId())
                .created(user.getCreated())
                .lastLogin(user.getLastLogin())
                .token(user.getToken())
                .isActive(user.isActive())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .phones(user.getPhones())
                .build();
    }
}
