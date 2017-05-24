package com.flipkart.cp.transact.toa.domain.entities.models.reachout;

/**
 * Created by padmanabh.kulkarni on 22/02/16.
 */
public enum  ResponseErrorMsg {
    VALIDATION_ERROR,
    INVALID_CLIENT_ID,
    DUPLICATE_REQUEST,
    INVALID_TEMPLATE,
    RUNTIME_ERROR,
    NOT_CANCELABLE,
    ALREADY_CANCELED,
    NOT_AVAILABLE,
    ALREADY_CONCLUDED,
    NO_MORE_CODES_AVAILABLE,
    CAN_NOT_POPULATE_VARIABLES,
    INVALID_REQUEST,
    INVALID_EVENT,
    NO_TEMPLATE_FOUND,
    NO_TEMPLATE_EXPERIMENT_FOUND,
    NOT_INITIALISED;

    private ResponseErrorMsg() {
    }

}
