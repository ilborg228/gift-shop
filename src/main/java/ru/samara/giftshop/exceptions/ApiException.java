package ru.samara.giftshop.exceptions;

import org.springframework.http.HttpStatus;

import java.text.MessageFormat;
import java.time.ZonedDateTime;

public class ApiException extends AbstractException{
    public ApiException(IResponse error) {
        super(error);
    }

    public ApiException(IResponse error, Object... params) {
        super(error);
        if (params != null) {
            error = error.withError(MessageFormat.format(error.getError(), params));
            setResult(error);
        }
    }
}
