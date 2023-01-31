package ru.samara.giftshop.exceptions;

import org.springframework.http.HttpStatus;

public class GenericResponse extends AbstractResponse {
    static final int MIN_CODE = 100001;
    static final int MAX_CODE = 100001;
    public static final GenericResponse UNKNOWN_SERVER_ERROR =
            new GenericResponse(100001, "Unknown server result");

    GenericResponse(int code, String error) {
        super(code, error);
    }

    @Override
    public AbstractResponse withDescription(String description) {
        AbstractResponse code = new GenericResponse(this.code, this.error);
        code.status = this.status;
        code.description = description;
        code.oldCode = this.oldCode;
        return code;
    }

    @Override
    public AbstractResponse withStatus(HttpStatus status) {
        AbstractResponse code = new GenericResponse(this.code, this.error);
        code.status = status;
        code.description = this.description;
        code.oldCode = this.oldCode;
        return code;
    }

    @Override
    public AbstractResponse withError(String error) {
        AbstractResponse code = new GenericResponse(this.code, error);
        code.status = this.status;
        code.description = this.description;
        code.oldCode = this.oldCode;
        return code;
    }
}

