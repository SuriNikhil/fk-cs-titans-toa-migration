package com.flipkart.cp.transact.toa.domain.exceptions;

/**
 * Created by padmanabh.kulkarni on 08/09/15.
 */
public class BadRequestException extends Exception {
    public BadRequestException(String message) {
        super(message);
    }
}
