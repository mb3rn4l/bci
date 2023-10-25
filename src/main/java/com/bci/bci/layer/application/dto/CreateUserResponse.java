package com.bci.bci.layer.application.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class CreateUserResponse {

    private Long id;

    private LocalDate created;

    private LocalDate lastLogin;

    private String token;

    private Boolean isActive;
}
