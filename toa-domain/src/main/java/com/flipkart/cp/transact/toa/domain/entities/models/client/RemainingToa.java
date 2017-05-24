package com.flipkart.cp.transact.toa.domain.entities.models.client;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by padmanabh.kulkarni on 03/02/16.
 */
public class RemainingToa {
    @JsonProperty("issued_toa")
    private Double issuedToa;

    @JsonProperty("remaining_toa")
    private Double remainingToa;


    public Double getIssuedToa() {
        return issuedToa;
    }

    public void setIssuedToa(Double issuedToa) {
        this.issuedToa = issuedToa;
    }

    public Double getRemainingToa() {
        return remainingToa;
    }

    public void setRemainingToa(Double remainingToa) {
        this.remainingToa = remainingToa;
    }
}
