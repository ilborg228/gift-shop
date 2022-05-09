package ru.samara.giftshop.exceptions;

import java.text.MessageFormat;

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
