package com.bci.bci.layer.infrastructure.entry_points.controller;

import com.bci.bci.layer.application.dto.CreateUserRequest;
import com.bci.bci.layer.application.dto.CreateUserResponse;
import com.bci.bci.layer.application.dto.LoginUserResponse;
import com.bci.bci.layer.application.handler.LoginHandler;
import com.bci.bci.layer.application.handler.SingUpHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/bci/api/auth")
public class AuthController {

    private final LoginHandler loginHandler;

    private final SingUpHandler singUpHandler;

    @GetMapping(value = "/login")
    public ResponseEntity<LoginUserResponse> userLogin(@RequestHeader("Authorization") final String token) {

        LoginUserResponse loginUserResponse = this.loginHandler.execute(token);

        return ResponseEntity.ok(loginUserResponse);
    }

    @PostMapping(value = "/sign-up")
    public ResponseEntity<CreateUserResponse> userSignUp(@RequestBody final CreateUserRequest userData) {

        CreateUserResponse createUserResponse = this.singUpHandler.execute(userData);

        return ResponseEntity.ok(createUserResponse);
    }

}
