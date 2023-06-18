package ru.samara.giftshop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.samara.giftshop.exceptions.*;
import ru.samara.giftshop.helpers.ExceptionHandlerUtil;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
@Order(2)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<IResponse> handleRuntimeException(Exception ex, HttpServletRequest request) {
        log.error("Caught exception: {}", ex.getMessage(), ex);
        ExceptionHandlerUtil.logRequest(request);
        return new ResponseEntity<>(GenericResponse.UNKNOWN_SERVER_ERROR,
                GenericResponse.UNKNOWN_SERVER_ERROR.getStatus());
    }
}