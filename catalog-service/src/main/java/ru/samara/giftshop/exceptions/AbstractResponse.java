package ru.samara.giftshop.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

public abstract class AbstractResponse implements IResponse{

    int code;
    @JsonIgnore
    int oldCode = Integer.MIN_VALUE;

    @JsonIgnore
    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    String error;
    String description;

    AbstractResponse(int code, String error) {
        this(code, error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    AbstractResponse(int code, String error, HttpStatus status) {
        this(code, error, status, null);
    }

    AbstractResponse(int code, String error, String description) {
        this(code, error, HttpStatus.INTERNAL_SERVER_ERROR, description);
    }

    AbstractResponse(int code, String error, HttpStatus status, String description) {
        this(code, Integer.MIN_VALUE, error, status, description);
    }

    AbstractResponse(int code, int oldCode, String error) {
        this(code, oldCode, error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    AbstractResponse(int code, int oldCode, String error, HttpStatus status) {
        this(code, oldCode, error, status, null);
    }

    AbstractResponse(int code, int oldCode, String error, HttpStatus status,
                               String description) {
        this.code = code;
        this.oldCode = oldCode;
        this.error = error;
        this.description = description;
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public String getError() {
        return error;
    }

    public String getDescription() {
        return description;
    }

    public AbstractResponse setError(String error) {
        this.error = error;
        return this;
    }

    public abstract AbstractResponse withDescription(String description);

    public abstract AbstractResponse withStatus(HttpStatus status);

    public abstract AbstractResponse withError(String error);

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.getClass().getName());
        builder.append(" [code=");
        builder.append(code);
        builder.append(", result=");
        builder.append(error);
        if (StringUtils.hasText(description)) {
            builder.append(", description=");
            builder.append(description);
        }
        if (oldCode > Integer.MIN_VALUE) {
            builder.append(", oldCode=");
            builder.append(oldCode);
        }

        builder.append(", status=");
        builder.append(status);

        builder.append("]");
        return builder.toString();
    }
}
