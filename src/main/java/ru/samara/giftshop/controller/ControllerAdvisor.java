package ru.samara.giftshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.samara.giftshop.exceptions.ApiException;
import ru.samara.giftshop.exceptions.NoSuchCategoryException;
import ru.samara.giftshop.exceptions.NoSuchProductException;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NoSuchCategoryException.class, NoSuchProductException.class})
    public ResponseEntity<?> handleCityNotFoundException(RuntimeException ex) {

        ApiException apiException = new ApiException(ex.getMessage(),ex,
                HttpStatus.NOT_FOUND, ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }
}