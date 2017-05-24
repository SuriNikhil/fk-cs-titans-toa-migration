package com.flipkart.cp.transact.toa.domain.entities.models.common;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by padmanabh.kulkarni on 26/01/16.
 */
@Entity
@Table(name = "transaction")
public class Transaction {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("toa_id")
    private Integer toaId;

    @JsonProperty("code")
    private String code;

    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("merchant_id")
    private Integer merchantId;

    @JsonProperty("status")
    private Integer status;

    @JsonProperty("created_date")
    private Date createdDate;

    @JsonProperty("updated_date")
    private Date updatedDate;

    @JsonProperty("card_number")
    private String cardNumber;

    public String toString(){
        if(this == null){
            return null;
        }

        return "{\n" +
                "    \"id\" : "+ this.id +",\n" +
                "    \"toa_id\": "+ this.toaId +",\n" +
                "    \"code\" : "+ this.code +",\n" +
                "    \"merchant_id\" : "+ this.merchantId +",\n" +
                "    \"status\" : "+ this.status +",\n" +
                "    \"created_date\" : "+ this.createdDate +",\n" +
                "    \"updated_date\" : "+ this.updatedDate +"\n" +
                "}";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Integer getToaId() {
        return toaId;
    }

    public void setToaId(Integer toaId) {
        this.toaId = toaId;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
