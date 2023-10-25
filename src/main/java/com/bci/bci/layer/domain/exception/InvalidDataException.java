package com.bci.bci.layer.domain.exception;

public class InvalidDataException extends RuntimeException {

    public InvalidDataException(final String message) {
        super(message);
    }
}
