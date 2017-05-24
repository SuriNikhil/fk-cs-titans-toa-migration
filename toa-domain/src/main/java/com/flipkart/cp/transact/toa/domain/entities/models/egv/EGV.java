package com.flipkart.cp.transact.toa.domain.entities.models.egv;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Created by padmanabh.kulkarni on 26/01/16.
 */
public class EGV {
    private String transactionId;
    private String clientPoNumber;
    private String code;
    private Integer denomination;
    private Integer quantity;
    private String pin;
    private Double balance;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date expiryDate;
    private String status;
    private Recipient recipient;
    private String walletToken;
    private String creationGroupId;

    public String toString(){
        if(this == null){
            return null;
        }

        String recipientDetails = null;
        if(this.recipient != null){
            recipientDetails = this.recipient.toString();
        }

        return "{\n" +
                "    \"transactionId\" : "+ this.transactionId +" ,\n" +
                "    \"clientPoNumber\" : "+ this.clientPoNumber +" ,\n" +
                "    \"denomination\" : "+ this.denomination +",\n" +
                "    \"quantity\" : "+ this.quantity +",\n" +
                "    \"balance\" : "+ this.balance +",\n" +
                "    \"status\" : "+ this.status +",\n" +
                "    \"recipient\" : "+ recipientDetails +",\n" +
                "    \"walletToken\" "+ this.walletToken +":\n" +
                "    \"creationGroupId\" : "+ this.creationGroupId +" \n" +
                "}";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getDenomination() {
        return denomination;
    }

    public void setDenomination(Integer denomination) {
        this.denomination = denomination;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getWalletToken() {
        return walletToken;
    }

    public void setWalletToken(String walletToken) {
        this.walletToken = walletToken;
    }

    public String getCreationGroupId() {
        return creationGroupId;
    }

    public void setCreationGroupId(String creationGroupId) {
        this.creationGroupId = creationGroupId;
    }

    public String getClientPoNumber() {
        return clientPoNumber;
    }

    public void setClientPoNumber(String clientPoNumber) {
        this.clientPoNumber = clientPoNumber;
    }
}
