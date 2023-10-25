package com.bci.bci.layer.application.handler;


import com.bci.bci.layer.application.dto.LoginUserResponse;
import com.bci.bci.layer.application.mapper.LoginResponseMapper;
import com.bci.bci.layer.domain.model.User;
import com.bci.bci.layer.domain.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
public class LoginHandler {

    private final LoginService loginService;

    private final LoginResponseMapper responseMapper;

    public LoginUserResponse execute(final String token) {

        User user = this.loginService.execute(token);

        return this.responseMapper.toResponse(user);
    }


}
