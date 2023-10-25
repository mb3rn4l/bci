package com.bci.bci.layer.application.mapper;

import com.bci.bci.layer.application.dto.CreateUserResponse;
import com.bci.bci.layer.domain.model.User;
import org.springframework.stereotype.Component;

@Component
public class CreateUserResponseMapper {

    public CreateUserResponse toResponse(final User user) {

        return CreateUserResponse.builder()
                .id(user.getId())
                .created(user.getCreated())
                .lastLogin(user.getLastLogin())
                .token(user.getToken())
                .isActive(user.isActive())
                .build();
    }

}
