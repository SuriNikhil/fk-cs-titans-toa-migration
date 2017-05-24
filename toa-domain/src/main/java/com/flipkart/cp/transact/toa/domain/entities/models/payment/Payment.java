package com.flipkart.cp.transact.toa.domain.entities.models.payment;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by padmanabh.kulkarni on 22/07/15.
 */
public class Payment {
    @JsonProperty("id")
    private String paymentId;

    @JsonProperty("refund_id")
    private String refundId;

    @JsonProperty("payment_ref_num")
    private String paymentReferenceNumber;

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    public String getPaymentReferenceNumber() {
        return paymentReferenceNumber;
    }

    public void setPaymentReferenceNumber(String paymentReferenceNumber) {
        this.paymentReferenceNumber = paymentReferenceNumber;
    }
}
