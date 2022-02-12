package ru.samara.giftshop.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class AbstractException extends RuntimeException{
    @JsonProperty("result")
    protected IResponse result;

    public AbstractException(IResponse error) {
        this.result = error;
    }

    public IResponse getResult() {
        return result;
    }

    public void setResult(IResponse error) {
        this.result = error;
    }

    @Override
    public String getMessage() {
        return result != null ? result.toString() : "";
    }

    @Override
    public String getLocalizedMessage() {
        return result != null ? result.toString() : "";
    }
}
