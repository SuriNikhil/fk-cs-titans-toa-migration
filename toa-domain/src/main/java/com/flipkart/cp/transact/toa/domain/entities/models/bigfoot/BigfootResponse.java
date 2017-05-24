package com.flipkart.cp.transact.toa.domain.entities.models.bigfoot;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by padmanabh.kulkarni on 23/06/15.
 */
public class BigfootResponse {

    @JsonProperty("status")
    private String status;

    @JsonProperty("reason")
    private String reason;

    @JsonProperty("detailedReason")
    private String detailedReason;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDetailedReason() {
        return detailedReason;
    }

    public void setDetailedReason(String detailedReason) {
        this.detailedReason = detailedReason;
    }
}
