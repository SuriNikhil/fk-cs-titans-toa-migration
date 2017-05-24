package com.flipkart.cp.transact.toa.domain.entities.models.bigfoot;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.flipkart.seraph.exceptions.EmptyStringException;
import com.flipkart.seraph.exceptions.NullAttributeException;
import com.flipkart.seraph.schema.BaseSchema;
import com.flipkart.seraph.schema.validator.DateTimeValidator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by padmanabh.kulkarni on 23/02/16.
 */
public class BigfootToaEntity extends BaseSchema{
    @JsonProperty("toa_type")
    private String toaType;

    @JsonProperty("tenant_id")
    private String tenantId;

    @JsonProperty("sub_entity_reference_type")
    private String subEntityReferenceType;

    @JsonProperty("promise_date")
    private String promiseDate;

    @JsonProperty("channel")
    private String channel;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("reference_entity_type")
    private String referenceEntityType;

    @JsonProperty("sub_entity_reference_id")
    private String subEntityReferenceId;

    @JsonProperty("external_payment_id")
    private String externalPaymentId;

    @JsonProperty("updated_at")
    private String updatedAt;

    @JsonProperty("toa_reference_id")
    private String toaReferenceId;

    @JsonProperty("user_login")
    private String userLogin;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("seller_id")
    private String sellerId;

    @JsonProperty("init_date")
    private String initDate;

    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("comments")
    private String comments;

    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("toa_reason")
    private String toaReason;

    @JsonProperty("customer_request_id")
    private String customerRequestId;

    @JsonProperty("merchant_name")
    private String merchantName;

    @JsonProperty("toa_mode")
    private String toaMode;

    @JsonProperty("reference_entity_id")
    private String referenceEntityId;

    @JsonProperty("customer_account_id")
    private String customerAccountId;

    @JsonProperty("payment_ref_num")
    private String paymentRefNum;

    @JsonProperty("status")
    private String status;

    @JsonProperty("external_reference_id")
    private String externalReferenceId;

    @JsonIgnore
    private String schemaVersion = "1.1";

    public String getToaType() {
        return toaType;
    }

    public void setToaType(String toaType) {
        this.toaType = toaType;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getSubEntityReferenceType() {
        return subEntityReferenceType;
    }

    public void setSubEntityReferenceType(String subEntityReferenceType) {
        this.subEntityReferenceType = subEntityReferenceType;
    }

    public String getPromiseDate() {
        return promiseDate;
    }

    public void setPromiseDate(String promiseDate) {
        this.promiseDate = promiseDate;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getReferenceEntityType() {
        return referenceEntityType;
    }

    public void setReferenceEntityType(String referenceEntityType) {
        this.referenceEntityType = referenceEntityType;
    }

    public String getSubEntityReferenceId() {
        return subEntityReferenceId;
    }

    public void setSubEntityReferenceId(String subEntityReferenceId) {
        this.subEntityReferenceId = subEntityReferenceId;
    }

    public String getExternalPaymentId() {
        return externalPaymentId;
    }

    public void setExternalPaymentId(String externalPaymentId) {
        this.externalPaymentId = externalPaymentId;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getToaReferenceId() {
        return toaReferenceId;
    }

    public void setToaReferenceId(String toaReferenceId) {
        this.toaReferenceId = toaReferenceId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getInitDate() {
        return initDate;
    }

    public void setInitDate(String initDate) {
        this.initDate = initDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getToaReason() {
        return toaReason;
    }

    public void setToaReason(String toaReason) {
        this.toaReason = toaReason;
    }

    public String getCustomerRequestId() {
        return customerRequestId;
    }

    public void setCustomerRequestId(String customerRequestId) {
        this.customerRequestId = customerRequestId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getToaMode() {
        return toaMode;
    }

    public void setToaMode(String toaMode) {
        this.toaMode = toaMode;
    }

    public String getReferenceEntityId() {
        return referenceEntityId;
    }

    public void setReferenceEntityId(String referenceEntityId) {
        this.referenceEntityId = referenceEntityId;
    }

    public String getCustomerAccountId() {
        return customerAccountId;
    }

    public void setCustomerAccountId(String customerAccountId) {
        this.customerAccountId = customerAccountId;
    }

    public String getPaymentRefNum() {
        return paymentRefNum;
    }

    public void setPaymentRefNum(String paymentRefNum) {
        this.paymentRefNum = paymentRefNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExternalReferenceId() {
        return externalReferenceId;
    }

    public void setExternalReferenceId(String externalReferenceId) {
        this.externalReferenceId = externalReferenceId;
    }


    @JsonIgnore
    public String getSchemaVersion() {
        return this.schemaVersion;
    }

    @Override
    public void unSetSchemaVersion() {

    }

    private void validateTenantId() throws Exception {
        if (this.tenantId == null) {
            throw new NullAttributeException("tenant_id");
        }
        if (tenantId.isEmpty()) {
            throw new EmptyStringException("tenant_id");
        }
    }

    private void validatePromiseDate() throws Exception {
        if (this.promiseDate == null) {
            return;
        }
        Map<String, Object> dateTimeValidatorParams = new HashMap<String, Object>();
        DateTimeValidator dateTimeValidator = new DateTimeValidator(dateTimeValidatorParams);
        dateTimeValidator.validate("promise_date", this.promiseDate);

    }

    private void validateCreatedAt() throws Exception {
        if (this.createdAt == null) {
            throw new NullAttributeException("created_at");
        }
        Map<String, Object> dateTimeValidatorParams = new HashMap<String, Object>();
        DateTimeValidator dateTimeValidator = new DateTimeValidator(dateTimeValidatorParams);
        dateTimeValidator.validate("created_at", this.createdAt);

    }

    private void validateReferenceEntityType() throws Exception {
        if (this.referenceEntityType == null) {
            throw new NullAttributeException("reference_entity_type");
        }
        if (referenceEntityType.isEmpty()) {
            throw new EmptyStringException("reference_entity_type");
        }
    }

    private void validateUpdatedAt() throws Exception {
        if (this.updatedAt == null) {
            throw new NullAttributeException("updated_at");
        }
        Map<String, Object> dateTimeValidatorParams = new HashMap<String, Object>();
        DateTimeValidator dateTimeValidator = new DateTimeValidator(dateTimeValidatorParams);
        dateTimeValidator.validate("updated_at", this.updatedAt);

    }

    private void validateToaReferenceId() throws Exception {
        if (this.toaReferenceId == null) {
            throw new NullAttributeException("toa_reference_id");
        }
        if (toaReferenceId.isEmpty()) {
            throw new EmptyStringException("toa_reference_id");
        }
    }

    private void validateId() throws Exception {
        if (this.id == null) {
            throw new NullAttributeException("id");
        }
    }

    private void validateSellerId() throws Exception {
        if (this.sellerId == null) {
            throw new NullAttributeException("seller_id");
        }
        if (sellerId.isEmpty()) {
            throw new EmptyStringException("seller_id");
        }
    }

    private void validateInitDate() throws Exception {
        if (this.initDate == null) {
            return;
        }
        Map<String, Object> dateTimeValidatorParams = new HashMap<String, Object>();
        DateTimeValidator dateTimeValidator = new DateTimeValidator(dateTimeValidatorParams);
        dateTimeValidator.validate("init_date", this.initDate);

    }

    private void validateAmount() throws Exception {
        if (this.amount == null) {
            throw new NullAttributeException("amount");
        }
    }

    private void validateToaReason() throws Exception {
        if (this.toaReason == null) {
            throw new NullAttributeException("toa_reason");
        }
        if (toaReason.isEmpty()) {
            throw new EmptyStringException("toa_reason");
        }
    }

    private void validateMerchantName() throws Exception {
        if (this.merchantName == null) {
            throw new NullAttributeException("merchant_name");
        }
        if (merchantName.isEmpty()) {
            throw new EmptyStringException("merchant_name");
        }
    }

    private void validateToaMode() throws Exception {
        if (this.toaMode == null) {
            throw new NullAttributeException("toa_mode");
        }
        if (toaMode.isEmpty()) {
            throw new EmptyStringException("toa_mode");
        }
    }

    private void validateReferenceEntityId() throws Exception {
        if (this.referenceEntityId == null) {
            throw new NullAttributeException("reference_entity_id");
        }
        if (referenceEntityId.isEmpty()) {
            throw new EmptyStringException("reference_entity_id");
        }
    }

    private void validateCustomerAccountId() throws Exception {
        if (this.customerAccountId == null) {
            throw new NullAttributeException("customer_account_id");
        }
        if (customerAccountId.isEmpty()) {
            throw new EmptyStringException("customer_account_id");
        }
    }

    private void validateStatus() throws Exception {
        if (this.status == null) {
            throw new NullAttributeException("status");
        }
        if (status.isEmpty()) {
            throw new EmptyStringException("status");
        }
    }

    public void validate() throws Exception {
        validateTenantId();
        validatePromiseDate();
        validateCreatedAt();
        validateReferenceEntityType();
        validateUpdatedAt();
        validateToaReferenceId();
        validateId();
        validateSellerId();
        validateInitDate();
        validateAmount();
        validateToaReason();
        validateMerchantName();
        validateToaMode();
        validateReferenceEntityId();
        validateCustomerAccountId();
        validateStatus();
    }
}
