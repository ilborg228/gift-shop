package ru.samara.giftshop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.samara.giftshop.exceptions.*;

import javax.servlet.http.HttpServletRequest;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ControllerAdvisor.class);

    @ExceptionHandler(value = {ApiException.class})
    protected ResponseEntity<IResponse> handleAbstractDbClientException(ApiException ex,HttpServletRequest request) {
        log.error("Caught ApiException: ", ex);
        return new ResponseEntity<>(ex.getResult(),
                ex.getResult().getStatus() != null ? ex.getResult().getStatus()
                        : HttpStatus.INTERNAL_SERVER_ERROR);
    }
}