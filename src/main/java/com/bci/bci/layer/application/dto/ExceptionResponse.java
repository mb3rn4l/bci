package com.bci.bci.layer.application.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ExceptionResponse {
    List<Error> errors;
}
