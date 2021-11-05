package ru.samara.giftshop.exceptions;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ApiException {
    private final String message;
    private final Throwable throwable;
    private final HttpStatus status;
    private final ZonedDateTime time;

    public ApiException(String message, Throwable throwable, HttpStatus status, ZonedDateTime time) {
        this.message = message;
        this.throwable = throwable;
        this.status = status;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public ZonedDateTime getTime() {
        return time;
    }
}
