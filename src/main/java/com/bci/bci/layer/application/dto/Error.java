package com.bci.bci.layer.application.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Error {
    private LocalDateTime timeStamp;
    private int code;
    private String message;
}
