package com.bci.bci.layer.domain.constants;

public class UserConstants {

    public static final String INVALID_PASSWORD_FORMAT = "invalid password format, it should contain one uppercase letter, " +
            "two numbers and a length between 8 and 12 characters";

    public static final String INVALID_EMAIL_FORMAT = "invalid email format";

    public static final String EMAIL_REQUIRED  = "email is required";

    public static final String PASSWORD_REQUIRED = "password is required";

    public static final String PASSWORD_LENGTH_AND_ONE_UPPERCASE_REGEX = "^(?=.{8,12}$)[a-z0-9]*[A-Z][a-z0-9]*$";

    public static final String PASSWORD_TWO_NUMBERS_REGEX = "^[a-zA-Z]*(?:\\d[a-zA-Z]*){0,2}$";

    public static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,4}$";

    public static final boolean ACTIVE = true;

    public static final String EMAIL_KEY = "email";
}
