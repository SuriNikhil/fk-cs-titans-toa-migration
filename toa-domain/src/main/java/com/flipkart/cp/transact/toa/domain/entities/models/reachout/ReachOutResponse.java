package com.flipkart.cp.transact.toa.domain.entities.models.reachout;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by padmanabh.kulkarni on 22/02/16.
 */
public class ReachOutResponse {
    @JsonProperty("request_id")
    private String requestId;

    @JsonProperty("error_code")
    private ResponseErrorMsg errorCode;

    @JsonProperty("detailed_error")
    private String detailedError;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public ResponseErrorMsg getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ResponseErrorMsg errorCode) {
        this.errorCode = errorCode;
    }

    public String getDetailedError() {
        return detailedError;
    }

    public void setDetailedError(String detailedError) {
        this.detailedError = detailedError;
    }
}
