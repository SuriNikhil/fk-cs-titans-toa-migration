package com.flipkart.cp.transact.toa.domain.entities.models.payment;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by padmanabh.kulkarni on 15/06/15.
 */
public class PaymentCallback {
    @JsonProperty("transaction_id")
    private String transactionId;

    @JsonProperty("payment")
    private Payment payment;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }


    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
