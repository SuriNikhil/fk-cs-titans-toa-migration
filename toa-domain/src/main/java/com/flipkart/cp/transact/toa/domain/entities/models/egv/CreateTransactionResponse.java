package com.flipkart.cp.transact.toa.domain.entities.models.egv;

/**
 * Created by padmanabh.kulkarni on 02/02/16.
 */
public class CreateTransactionResponse {
    private String statusCode;
    private String statusMessage;
    private String transactionId;
    private String transactionStatus;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }
}
