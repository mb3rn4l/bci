package com.bci.bci.layer.domain.model;

import com.bci.bci.layer.domain.utils.IJwt;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

import static com.bci.bci.layer.domain.validation.ArgumentValidator.validateRegex;
import static com.bci.bci.layer.domain.validation.ArgumentValidator.validateRequired;

@Builder
@Getter
@AllArgsConstructor
public class User {

    private Long id;

    private LocalDate created;

    private LocalDate lastLogin;

    private String token;

    private boolean isActive;

    private String name;

    private String email;

    private String password;

    private List<Phone> phones;

    public void updateLastLogin() {
        this.lastLogin = LocalDate.now();
    }

    public void updateToken(IJwt jwt) {
        this.token = jwt.generateJWT(this.email);
    }
}
