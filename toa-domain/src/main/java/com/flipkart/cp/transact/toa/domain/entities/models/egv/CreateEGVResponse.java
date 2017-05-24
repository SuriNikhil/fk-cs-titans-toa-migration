package com.flipkart.cp.transact.toa.domain.entities.models.egv;

/**
 * Created by padmanabh.kulkarni on 02/02/16.
 */
public class CreateEGVResponse {
    private String statusCode;
    private String statusMessage;
    private EGV egv;

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

    public EGV getEgv() {
        return egv;
    }

    public void setEgv(EGV egv) {
        this.egv = egv;
    }
}
