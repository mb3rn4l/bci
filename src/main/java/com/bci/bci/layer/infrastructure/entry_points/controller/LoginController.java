package com.bci.bci.layer.infrastructure.entry_points.controller;

import com.bci.bci.layer.application.dto.LoginUserResponse;
import com.bci.bci.layer.application.handler.LoginHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/bci/api/login")
public class LoginController {

    private final LoginHandler loginHandler;

    @GetMapping(value = "/{token}")
    public ResponseEntity<LoginUserResponse> userSignUp(@PathVariable final String token) {

        LoginUserResponse loginUserResponse = this.loginHandler.execute(token);

        return ResponseEntity.ok(loginUserResponse);
    }

}
