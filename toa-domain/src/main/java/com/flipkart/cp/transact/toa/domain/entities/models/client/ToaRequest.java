package com.flipkart.cp.transact.toa.domain.entities.models.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.flipkart.cp.transact.toa.domain.enums.EntityReferenceType;
import com.flipkart.cp.transact.toa.domain.enums.SubEntityReferenceType;
import com.flipkart.cp.transact.toa.domain.enums.ToaMode;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by padmanabh.kulkarni on 26/05/15.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ToaRequest {
    @NotNull
    @JsonProperty("toa_reference_id")
    private String toaReferenceId;

    @NotNull
    @JsonProperty("reference_entity_type")
    private EntityReferenceType referenceEntityType; //order, order_item etc

    @NotNull
    @JsonProperty("reference_entity_id")
    private String referenceEntityId;

    @NotNull
    @JsonProperty("toa_reason")
    private String toaReason;

    @NotNull
    @JsonProperty("toa_amount")
    private Double toaAmount;

    @NotNull
    @JsonProperty("toa_mode")
    private ToaMode toaMode;

    @JsonProperty("toa_type")
    private String toaType;     //Type promotional/goodwill etc

    @JsonProperty("comment")
    private String comment;

    @NotNull
    @JsonProperty("user_login")
    private String userLogin;   //LDAP username

    @NotNull
    @JsonProperty("quantity")
    private Integer quantity;

    @NotNull
    @JsonProperty("customer_account_id")
    private String customerAccountId;

    @JsonProperty("customer_request_id")
    private String customerRequestId;

    @JsonProperty("external_payment_id")
    private String externalPaymentId;

    @JsonProperty("promise_date")
    private Date promiseDate;

    @NotNull
    @JsonProperty("sub_entity_reference_type")
    private SubEntityReferenceType subEntityReferenceType;

    @NotNull
    @JsonProperty("sub_entity_reference_id")
    private String subEntityReferenceId;

    @JsonProperty("external_reference_id")
    private String externalReferenceId;

    @JsonProperty("toa_limit_override")
    private boolean toaLimitOverride;

    @NotNull
    @JsonProperty("merchant_name")
    private String merchantName;

    @NotNull
    @JsonProperty("seller_id")
    private String sellerId;

    @NotNull
    @JsonProperty("channel")
    private String channel;

    public ToaRequest(){}


    public String getToaReferenceId() {
        return toaReferenceId;
    }

    public void setToaReferenceId(String toaReferenceId) {
        this.toaReferenceId = toaReferenceId;
    }

    public EntityReferenceType getReferenceEntityType() {
        return referenceEntityType;
    }

    public void setReferenceEntityType(EntityReferenceType referenceEntityType) {
        this.referenceEntityType = referenceEntityType;
    }

    public String getReferenceEntityId() {
        return referenceEntityId;
    }

    public void setReferenceEntityId(String referenceEntityId) {
        this.referenceEntityId = referenceEntityId;
    }

    public String getToaReason() {
        return toaReason;
    }

    public void setToaReason(String toaReason) {
        this.toaReason = toaReason;
    }

    public Double getToaAmount() {
        return toaAmount;
    }

    public void setToaAmount(Double toaAmount) {
        this.toaAmount = toaAmount;
    }

    public ToaMode getToaMode() {
        return toaMode;
    }

    public void setToaMode(ToaMode toaMode) {
        this.toaMode = toaMode;
    }

    public String getToaType() {
        return toaType;
    }

    public void setToaType(String toaType) {
        this.toaType = toaType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getCustomerAccountId() {
        return customerAccountId;
    }

    public void setCustomerAccountId(String customerAccountId) {
        this.customerAccountId = customerAccountId;
    }

    public String getCustomerRequestId() {
        return customerRequestId;
    }

    public void setCustomerRequestId(String customerRequestId) {
        this.customerRequestId = customerRequestId;
    }

    public Date getPromiseDate() {
        return promiseDate;
    }

    public void setPromiseDate(Date promiseDate) {
        this.promiseDate = promiseDate;
    }

    public String getExternalPaymentId() {
        return externalPaymentId;
    }

    public void setExternalPaymentId(String externalPaymentId) {
        this.externalPaymentId = externalPaymentId;
    }

    public String getSubEntityReferenceId() {
        return subEntityReferenceId;
    }

    public void setSubEntityReferenceId(String subEntityReferenceId) {
        this.subEntityReferenceId = subEntityReferenceId;
    }

    public String getExternalReferenceId() {
        return externalReferenceId;
    }

    public void setExternalReferenceId(String externalReferenceId) {
        this.externalReferenceId = externalReferenceId;
    }

    public SubEntityReferenceType getSubEntityReferenceType() {
        return subEntityReferenceType;
    }

    public void setSubEntityReferenceType(SubEntityReferenceType subEntityReferenceType) {
        this.subEntityReferenceType = subEntityReferenceType;
    }

    public String  toString(){
        {   return "{\n" +
                "\t\"toa_reference_id\": \"" + this.getToaReferenceId() + "\",\n" +
                "\t\"reference_entity_type\": \""+ this.getReferenceEntityType() +"\",\n" +
                "\t\"reference_entity_id\": \""+ this.getReferenceEntityId() +"\",\n" +
                "\t\"toa_reason\": \""+ this.getToaReason()+"\",\n" +
                "\t\"toa_amount\": \""+ this.getToaAmount()+",\n" +
                "\t\"toa_mode\": \""+ this.getToaMode()+"\",\n" +
                "\t\"toa_type\": \""+ this.getToaType()+"\",\n" +
                "\t\"comment\": \""+ this.getComment()+"\",\n" +
                "\t\"user_login\": \""+ this.getUserLogin()+"\",\n" +
                "\t\"quantity\": "+ this.getQuantity()+",\n" +
                "\t\"customer_account_id\": \""+ this.getCustomerAccountId()+"\",\n" +
                "\t\"customer_request_id\": \""+ this.getCustomerRequestId()+"\",\n" +
                "\t\"external_payment_id\": \""+ this.getExternalPaymentId()+"\",\n" +
                "\t\"promise_date\": \""+ this.getPromiseDate()+"\",\n" +
                "\t\"sub_entity_reference_type\": \""+ this.getSubEntityReferenceType()+"\",\n" +
                "\t\"sub_entity_reference_id\": \""+ this.getSubEntityReferenceId()+"\",\n" +
                "\t\"external_reference_id\": \""+ this.getExternalReferenceId()+"\"\n" +
                "}" ;

            }
    }

    public boolean isToaLimitOverride() {
        return toaLimitOverride;
    }

    public void setToaLimitOverride(boolean toaLimitOverride) {
        this.toaLimitOverride = toaLimitOverride;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}

