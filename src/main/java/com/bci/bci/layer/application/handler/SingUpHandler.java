package com.bci.bci.layer.application.handler;


import com.bci.bci.layer.application.dto.CreateUserRequest;
import com.bci.bci.layer.application.dto.CreateUserResponse;
import com.bci.bci.layer.application.mapper.CreateUserRequestMapper;
import com.bci.bci.layer.application.mapper.CreateUserResponseMapper;
import com.bci.bci.layer.domain.model.User;
import com.bci.bci.layer.domain.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
public class SingUpHandler {

    private final SignUpService signUpService;

    private final CreateUserRequestMapper requestMapper;

    private final CreateUserResponseMapper responseMapper;

    public CreateUserResponse execute(final CreateUserRequest createUserRequest) {

        User user = this.requestMapper.toUser(createUserRequest);

        this.signUpService.execute(user);

        return this.responseMapper.toResponse(user);
    }

}
