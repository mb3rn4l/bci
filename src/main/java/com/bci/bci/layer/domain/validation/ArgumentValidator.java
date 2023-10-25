package com.bci.bci.layer.domain.validation;

import com.bci.bci.layer.domain.exception.InvalidDataException;
import lombok.NoArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor
public class ArgumentValidator {

    public static void validateRequired(final Object value, final String message) {
        if (value == null || value.toString().isEmpty()) {
            throw new InvalidDataException(message);
        }
    }

    public static void validateRegex(String value, String regex, String message) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);

        if (!matcher.matches()) {
            throw new InvalidDataException(message);
        }
    }
}
