package com.bci.bci.layer.domain.model;

import com.bci.bci.layer.domain.utils.IJwt;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

import static com.bci.bci.layer.domain.validation.ArgumentValidator.validateRegex;
import static com.bci.bci.layer.domain.validation.ArgumentValidator.validateRequired;

@Builder
@Getter
public class User {

    public static final String INVALID_PASSWORD_FORMAT = "invalid password format, it should contain one uppercase letter, " +
            "two numbers and a length between 8 and 12 characters";

    public static final String INVALID_EMAIL_FORMAT = "invalid email format";

    private final String PASSWORD_LENGTH_AND_ONE_UPPERCASE_REGEX = "^(?=.{8,12}$)[a-z0-9]*[A-Z][a-z0-9]*$";

    private final String PASSWORD_TWO_NUMBERS_REGEX = "^[a-zA-Z]*(?:\\d[a-zA-Z]*){0,2}$";

    private final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,4}$";

    public static final boolean ACTIVE = true;

    private Long id;

    private LocalDate created;

    private LocalDate lastLogin;

    private String token;

    private boolean isActive;

    private String name;

    private String email;

    private String password;

    private List<Phone> phones;

    public User(Long id, LocalDate created, LocalDate lastLogin, String token, boolean isActive, String name, String email, String password, List<Phone> phones) {

        validateRequired(email, "email is required");
        validateRequired(password, "password is required");

        // validate one upper case and length with regex
        validateRegex(password, PASSWORD_LENGTH_AND_ONE_UPPERCASE_REGEX, INVALID_PASSWORD_FORMAT);

        // validate two numbers not in a row
        validateRegex(password, PASSWORD_TWO_NUMBERS_REGEX, INVALID_PASSWORD_FORMAT);

        // validate email
        validateRegex(email, EMAIL_REGEX, INVALID_EMAIL_FORMAT);

        this.id = id;
        this.created = created;
        this.lastLogin = lastLogin;
        this.token = token;
        this.isActive = isActive;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phones = phones;
    }


    public void updateLastLogin() {
        this.lastLogin = LocalDate.now();
    }

    public void updateToken(IJwt jwt) {
        this.token = jwt.generateJWT(this.email);
    }
}
