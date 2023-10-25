package com.bci.bci.layer.infrastructure.entry_points.controller;

import com.bci.bci.layer.application.dto.CreateUserRequest;
import com.bci.bci.layer.application.dto.CreateUserResponse;
import com.bci.bci.layer.application.handler.SingUpHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/bci/api/sign-up")
public class SignUpController {

    private final SingUpHandler singUpHandler;

    @PostMapping
    public ResponseEntity<CreateUserResponse> userSignUp(@RequestBody final CreateUserRequest userData) {

        CreateUserResponse createUserResponse = this.singUpHandler.execute(userData);

        return ResponseEntity.ok(createUserResponse);
    }

}
