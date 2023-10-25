package com.bci.bci.layer.application.handler;

import com.bci.bci.layer.application.dto.Error;
import com.bci.bci.layer.application.dto.ExceptionResponse;
import com.bci.bci.layer.domain.exception.InvalidDataException;
import com.bci.bci.layer.domain.exception.NotFoundException;
import com.bci.bci.layer.domain.exception.UserAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ExceptionResponse> handleUserAlreadyExistsException(
            UserAlreadyExistException userAlreadyExistException) {

        Error error = Error.builder()
                .code(1)
                .timeStamp(LocalDateTime.now())
                .message("User Already Exist")
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ExceptionResponse.builder().errors(List.of(error)).build());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNoDataFoundException(
            NotFoundException notFoundException) {

        Error error = Error.builder()
                .code(2)
                .timeStamp(LocalDateTime.now())
                .message("User not found")
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ExceptionResponse.builder().errors(List.of(error)).build());
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidDataException(
            InvalidDataException invalidDataException) {

        Error error = Error.builder()
                .code(3)
                .timeStamp(LocalDateTime.now())
                .message(invalidDataException.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ExceptionResponse.builder().errors(List.of(error)).build());
    }
}
