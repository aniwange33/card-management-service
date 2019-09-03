package com.vela.cardmanageservice.infrastructure.web.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {
    LocalDateTime localDateTime;
    String        message;

    private ErrorResponse(LocalDateTime localDateTime, String message) {
        this.localDateTime = localDateTime;
        this.message = message;
    }

    public static ErrorResponse createErrorResponse(LocalDateTime localDateTime, String message) {
        return new ErrorResponse(localDateTime, message);
    }
}
