package com.bci.bci.layer.application.mapper;

import com.bci.bci.layer.application.dto.CreateUserRequest;
import com.bci.bci.layer.domain.constants.UserConstants;
import com.bci.bci.layer.domain.model.User;
import com.bci.bci.layer.domain.utils.IJwt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class CreateUserRequestMapper {

    private final IJwt jwt;

    public User toUser(final CreateUserRequest createUserRequest) {

        String token = this.jwt.generateJWT(createUserRequest.getEmail());

        return User.builder()
                .created(LocalDate.now())
                .lastLogin(null)
                .token(token)
                .isActive(UserConstants.ACTIVE)
                .name(createUserRequest.getName())
                .email(createUserRequest.getEmail())
                .password(createUserRequest.getPassword())
                .phones(createUserRequest.getPhones())
                .build();
    }
}
