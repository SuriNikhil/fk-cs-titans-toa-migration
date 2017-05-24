package com.flipkart.cp.transact.toa.actions.models.request;
/**
 * Created by padmanabh.kulkarni on 28/06/15.
 */
public class UserServiceRequest {
    private String customerAccountId;

    public UserServiceRequest(String accountId) {
        this.customerAccountId = accountId;
    }

    public String getCustomerAccountId() {
        return customerAccountId;
    }

    public void setCustomerAccountId(String customerAccountId) {
        this.customerAccountId = customerAccountId;
    }
}

