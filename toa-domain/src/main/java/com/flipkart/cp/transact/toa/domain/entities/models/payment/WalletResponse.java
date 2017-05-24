package com.flipkart.cp.transact.toa.domain.entities.models.payment;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * Created by padmanabh.kulkarni on 19/08/15.
 */
public class WalletResponse {
    @JsonProperty("txnstatus")
    private String transactionStatus;

    @JsonProperty("refid")
    private String referenceId;

    @JsonProperty("clientrefid")
    private String clientReferenceId;

    @JsonProperty("balance")
    private Map<String, Integer> balance;

    @JsonProperty("wid")
    private String walletId;

    @JsonProperty("is_active")
    private Integer isActive;

    @JsonProperty("hash")
    private String hash;

    @JsonProperty("responsecode")
    private String responseCode;

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getClientReferenceId() {
        return clientReferenceId;
    }

    public void setClientReferenceId(String clientReferenceId) {
        this.clientReferenceId = clientReferenceId;
    }

    public Map<String, Integer> getBalance() {
        return balance;
    }

    public void setBalance(Map<String, Integer> balance) {
        this.balance = balance;
    }

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }
}
