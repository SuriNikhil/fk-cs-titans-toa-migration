package com.flipkart.cp.transact.toa.actions.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by padmanabh.kulkarni on 28/06/15.
 */
public class UserServiceResponse {

    @JsonProperty("primary_account_id")
    private String primaryAccountId;

    public UserServiceResponse() {
    }

    public UserServiceResponse(String primaryAccountId) {
        this.primaryAccountId = primaryAccountId;
    }

    public String getPrimaryAccountId() {
        return primaryAccountId;
    }

    public void setPrimaryAccountId(String primaryAccountId) {
        this.primaryAccountId = primaryAccountId;
    }
}

