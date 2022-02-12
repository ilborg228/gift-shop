package ru.samara.giftshop.exceptions;

import org.springframework.http.HttpStatus;

public interface IResponse {
    /**
     * Short code which represents a business issue.
     *
     * @return int business code.
     */
    int getCode();

    String getError();

    /**
     * Full description of the business code.
     *
     * @return String description.
     */
    String getDescription();

    HttpStatus getStatus();

    public IResponse withDescription(String description);

    public IResponse withStatus(HttpStatus status);

    public IResponse withError(String error);
}
