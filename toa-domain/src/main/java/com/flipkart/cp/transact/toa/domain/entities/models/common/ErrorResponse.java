package com.flipkart.cp.transact.toa.domain.entities.models.common;

/**
 * Created by padmanabh.kulkarni on 09/06/15.
 */
public class ErrorResponse {
    private String errorMessage;

    public static ErrorResponse create() {
        return new ErrorResponse();
    }

    public ErrorResponse add(final String message) {
        this.setErrorMessage(message);
        return this;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
