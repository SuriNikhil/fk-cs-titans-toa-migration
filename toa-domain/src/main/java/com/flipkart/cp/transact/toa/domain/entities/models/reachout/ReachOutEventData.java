package com.flipkart.cp.transact.toa.domain.entities.models.reachout;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.flipkart.cp.transact.toa.domain.entities.models.client.Toa;
import com.flipkart.cp.transact.toa.domain.enums.EntityReferenceType;
import com.flipkart.cp.transact.toa.domain.enums.Status;
import com.flipkart.cp.transact.toa.domain.enums.SubEntityReferenceType;
import com.flipkart.cp.transact.toa.domain.enums.ToaMode;
import org.apache.commons.math3.util.Precision;

import javax.validation.constraints.NotNull;

/**
 * Created by padmanabh.kulkarni on 19/02/16.
 */
public class ReachOutEventData {
    @JsonIgnore
    private Integer id;

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

    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("status")
    private Status status;  //initiated, completed etc

    @JsonProperty("created_at")
    private Long createdAt;

    @JsonProperty("init_date")
    private Long initDate;

    @NotNull
    @JsonProperty("customer_account_id")
    private String customerAccountId;

    @JsonProperty("customer_request_id")
    private String customerRequestId;

    @JsonProperty("payment_reference_number")
    private String paymentReferenceNumber;

    @JsonProperty("external_payment_id")
    private String externalPaymentId;

    @JsonProperty("promise_date")
    private Long promiseDate;

    @JsonProperty("updated_at")
    private Long updatedAt;

    @JsonProperty("seller_id")
    private String sellerId;

    @JsonProperty("tenant_id")
    private String tenantId; //FKMP etc

    @JsonProperty("sub_entity_reference_type")
    private SubEntityReferenceType subEntityReferenceType;

    @JsonProperty("sub_entity_reference_id")
    private String subEntityReferenceId;

    @JsonProperty("external_reference_id")
    private String externalReferenceId;

    @JsonProperty("toa_limit_override")
    private boolean toaLimitOverride;

    @JsonProperty("merchant_id")
    private Integer merchantId;

    @JsonProperty("merchant_name")
    private String merchantName;

    @JsonProperty("channel")
    private String channel;

    public ReachOutEventData(){}
    
    public ReachOutEventData(Toa toa){
        this.toaReferenceId = toa.getToaReferenceId();
        this.referenceEntityType = toa.getReferenceEntityType();
        this.referenceEntityId = toa.getReferenceEntityId();
        this.toaReason = toa.getToaReason();
        this.toaAmount = Precision.round(toa.getToaAmount(), 2);
        this.toaMode = toa.getToaMode();
        this.toaType = toa.getToaType();
        this.comment = toa.getComment();
        this.userLogin = toa.getUserLogin();
        this.quantity = toa.getQuantity();
        this.customerAccountId = toa.getCustomerAccountId();
        this.customerRequestId = toa.getCustomerRequestId();
        this.externalPaymentId = toa.getExternalPaymentId();

        if(promiseDate != null) {
            this.promiseDate = toa.getPromiseDate().getTime();
        }

        this.subEntityReferenceType = toa.getSubEntityReferenceType();
        this.subEntityReferenceId = toa.getSubEntityReferenceId();
        this.externalReferenceId = toa.getExternalReferenceId();
        this.toaLimitOverride = toa.isToaLimitOverride();
        this.sellerId = toa.getSellerId();
        this.merchantName = toa.getMerchantName();
        this.channel = toa.getChannel();

        if(toa.getCreatedAt() != null) {
            this.createdAt = toa.getCreatedAt().getTime();
        }

        if(toa.getInitDate() != null) {
            this.initDate = toa.getInitDate().getTime();
        }

        if(toa.getUpdatedAt() != null) {
            this.updatedAt = toa.getUpdatedAt().getTime();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getInitDate() {
        return initDate;
    }

    public void setInitDate(Long initDate) {
        this.initDate = initDate;
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

    public String getPaymentReferenceNumber() {
        return paymentReferenceNumber;
    }

    public void setPaymentReferenceNumber(String paymentReferenceNumber) {
        this.paymentReferenceNumber = paymentReferenceNumber;
    }

    public String getExternalPaymentId() {
        return externalPaymentId;
    }

    public void setExternalPaymentId(String externalPaymentId) {
        this.externalPaymentId = externalPaymentId;
    }

    public Long getPromiseDate() {
        return promiseDate;
    }

    public void setPromiseDate(Long promiseDate) {
        this.promiseDate = promiseDate;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public SubEntityReferenceType getSubEntityReferenceType() {
        return subEntityReferenceType;
    }

    public void setSubEntityReferenceType(SubEntityReferenceType subEntityReferenceType) {
        this.subEntityReferenceType = subEntityReferenceType;
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

    public boolean isToaLimitOverride() {
        return toaLimitOverride;
    }

    public void setToaLimitOverride(boolean toaLimitOverride) {
        this.toaLimitOverride = toaLimitOverride;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
