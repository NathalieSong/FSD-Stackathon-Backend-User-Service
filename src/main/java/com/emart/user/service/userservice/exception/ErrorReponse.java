package com.emart.user.service.userservice.exception;

import org.springframework.http.HttpStatus;

public class ErrorReponse {
    private final HttpStatus status;
    private final String message;
    private final Integer code;

    protected ErrorReponse(HttpStatus status, final String message, final Integer code) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public static ErrorReponse of(HttpStatus status, final String message, final Integer code) {
        return new ErrorReponse(status, message, code);
    }

    public Integer getStatus() {
        return status.value();
    }

    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}