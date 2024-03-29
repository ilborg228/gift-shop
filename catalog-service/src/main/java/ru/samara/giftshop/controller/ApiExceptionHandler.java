package ru.samara.giftshop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.samara.giftshop.exceptions.ApiException;
import ru.samara.giftshop.exceptions.DataValidationResponse;
import ru.samara.giftshop.exceptions.IResponse;
import ru.samara.giftshop.helpers.ExceptionHandlerUtil;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
@Order(1)
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler(value = {ApiException.class})
    protected ResponseEntity<IResponse> handleAbstractDbClientException(ApiException ex,
                                                                        HttpServletRequest request) {
        log.error("Caught ApiException: ", ex);
        return new ResponseEntity<>(ex.getResult(),
                ex.getResult().getStatus() != null ? ex.getResult().getStatus()
                        : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body,
                                                             HttpHeaders headers, HttpStatusCode statusCode,
                                                             WebRequest request) {
        log.error("Caught Exception: ", ex);
        ExceptionHandlerUtil.logRequest(request);
        return new ResponseEntity<>(
                DataValidationResponse.INVALID_REQUEST.withDescription(ex.getMessage()),
                headers, HttpStatus.BAD_REQUEST.value()
        );
    }
}
