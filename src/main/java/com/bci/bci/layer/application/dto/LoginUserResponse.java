package com.bci.bci.layer.application.dto;

import com.bci.bci.layer.domain.model.Phone;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class LoginUserResponse {

    private Long id;

    private LocalDate created;

    private LocalDate lastLogin;

    private String token;

    private boolean isActive;

    private String name;

    private String email;

    private String password;

    private List<Phone> phones;
}
