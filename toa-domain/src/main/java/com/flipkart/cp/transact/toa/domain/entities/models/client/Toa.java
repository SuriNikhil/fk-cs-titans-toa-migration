package com.flipkart.cp.transact.toa.domain.entities.models.client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.flipkart.cp.transact.toa.domain.entities.models.common.Transaction;
import com.flipkart.cp.transact.toa.domain.enums.EntityReferenceType;
import com.flipkart.cp.transact.toa.domain.enums.Status;
import com.flipkart.cp.transact.toa.domain.enums.SubEntityReferenceType;
import com.flipkart.cp.transact.toa.domain.enums.ToaMode;
import org.apache.commons.math3.util.Precision;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Created by padmanabh.kulkarni on 26/05/15.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Entity
@Table(name="toa")
public class Toa {
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
    private Date createdAt;

    @JsonProperty("init_date")
    private Date initDate;

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
    private Date promiseDate;

    @JsonProperty("updated_at")
    private Date updatedAt;

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

    @JsonProperty("transaction_list")
    private List<Transaction> transactionList;

    public Toa(){}

    public Toa(ToaRequest toaRequest){
        this.toaReferenceId = toaRequest.getToaReferenceId();
        this.referenceEntityType = toaRequest.getReferenceEntityType();
        this.referenceEntityId = toaRequest.getReferenceEntityId();
        this.toaReason = toaRequest.getToaReason();
        this.toaAmount = Precision.round(toaRequest.getToaAmount(), 2);
        this.toaMode = toaRequest.getToaMode();
        this.toaType = toaRequest.getToaType();
        this.comment = toaRequest.getComment();
        this.userLogin = toaRequest.getUserLogin();
        this.quantity = toaRequest.getQuantity();
        this.customerAccountId = toaRequest.getCustomerAccountId();
        this.customerRequestId = toaRequest.getCustomerRequestId();
        this.externalPaymentId = toaRequest.getExternalPaymentId();
        this.promiseDate = toaRequest.getPromiseDate();
        this.subEntityReferenceType = toaRequest.getSubEntityReferenceType();
        this.subEntityReferenceId = toaRequest.getSubEntityReferenceId();
        this.externalReferenceId = toaRequest.getExternalReferenceId();
        this.toaLimitOverride = toaRequest.isToaLimitOverride();
        this.sellerId = toaRequest.getSellerId();
        this.merchantName = toaRequest.getMerchantName();
        this.channel = toaRequest.getChannel();
    }

    public Toa(BulkToaRequest bulkToaRequest){
        this.referenceEntityType = bulkToaRequest.getReferenceEntityType();
        this.referenceEntityId = bulkToaRequest.getReferenceEntityId();
        this.toaReason = bulkToaRequest.getToaReason();
        this.toaMode = bulkToaRequest.getToaMode();
        this.toaType = bulkToaRequest.getToaType();
        this.comment = bulkToaRequest.getComment();
        this.userLogin = bulkToaRequest.getUserLogin();
        this.customerAccountId = bulkToaRequest.getCustomerAccountId();
        this.customerRequestId = bulkToaRequest.getCustomerRequestId();
        this.externalPaymentId = bulkToaRequest.getExternalPaymentId();
        this.promiseDate = bulkToaRequest.getPromiseDate();
        this.referenceEntityType = bulkToaRequest.getReferenceEntityType();
        this.subEntityReferenceType = bulkToaRequest.getSubEntityReferenceType();
        this.externalReferenceId = bulkToaRequest.getExternalReferenceId();
        this.toaLimitOverride = bulkToaRequest.isToaLimitOverride();
        this.channel = bulkToaRequest.getChannel();
        this.merchantName = bulkToaRequest.getMerchantName();
    }

    public Toa(
            Integer id,
            String toaReferenceId,
            EntityReferenceType referenceEntityType,
            String referenceEntityId,
            String toaReason,
            Double toaAmount,
            ToaMode toaMode,
            String toaType,
            String comment,
            String userLogin,
            Integer quantity,
            Status status,
            Date initDate,
            String customerAccountId,
            String customerRequestId,
            String paymentReferenceNumber,
            String externalPaymentId,
            Date promiseDate,
            Date updatedAt,
            String sellerId,
            String tenantId,
            String subEntityReferenceId,
            String externalReferenceId,
            boolean toaLimitOverride){

        this.id = id;
        this.toaReferenceId = toaReferenceId;
        this.referenceEntityType = referenceEntityType;
        this.referenceEntityId = referenceEntityId;
        this.toaReason = toaReason;
        this.toaAmount = Precision.round(toaAmount, 2);
        this.toaMode = toaMode;
        this.toaType = toaType;
        this.comment = comment;
        this.userLogin = userLogin;
        this.quantity = quantity;
        this.status = status;
        this.initDate = initDate;
        this.customerAccountId = customerAccountId;
        this.customerRequestId = customerRequestId;
        this.paymentReferenceNumber = paymentReferenceNumber;
        this.externalPaymentId = externalPaymentId;
        this.promiseDate = promiseDate;
        this.updatedAt = updatedAt;
        this.sellerId = sellerId;
        this.tenantId = tenantId;
        this.subEntityReferenceId = subEntityReferenceId;
        this.externalReferenceId = externalReferenceId;
        this.toaLimitOverride = toaLimitOverride;
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
        toaAmount=  Precision.round(toaAmount, 2);
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

    public Date getInitDate() {
        return initDate;
    }

    public void setInitDate(Date initDate) {
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

    public Date getPromiseDate() {
        return promiseDate;
    }

    public void setPromiseDate(Date promiseDate) {
        this.promiseDate = promiseDate;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public SubEntityReferenceType getSubEntityReferenceType() {
        return subEntityReferenceType;
    }

    public void setSubEntityReferenceType(SubEntityReferenceType subEntityReferenceType) {
        this.subEntityReferenceType = subEntityReferenceType;
    }

    public String toString(){
        return "{\n" +
                "\t\"toa_reference_id\": \""+ this.getToaReferenceId()+"\",\n" +
                "\t\"reference_entity_type\": \""+ this.getReferenceEntityType()+"\",\n" +
                "\t\"reference_entity_id\": \""+ this.getReferenceEntityId()+"\",\n" +
                "\t\"toa_reason\": \""+ this.getToaReason()+"\",\n" +
                "\t\"toa_amount\": "+ this.getToaAmount()+",\n" +
                "\t\"toa_mode\": \"WALLET_PROMOTION\",\n" +
                "\t\"toa_type\": \""+ this.getToaType()+"\",\n" +
                "\t\"comment\": \""+ this.getComment()+"\",\n" +
                "\t\"user_login\": \""+ this.getUserLogin()+"\",\n" +
                "\t\"quantity\": "+ this.getQuantity()+",\n" +
                "\t\"status\": \""+ this.getStatus()+"\",\n" +
                "\t\"created_at\": \""+ this.getCreatedAt()+"\",\n" +
                "\t\"init_date\": \""+ this.getInitDate()+"\",\n" +
                "\t\"customer_account_id\": \""+ this.getCustomerAccountId()+"\",\n" +
                "\t\"customer_request_id\": \""+ this.getCustomerRequestId()+"\",\n" +
                "\t\"payment_reference_number\": \""+ this.getPaymentReferenceNumber()+"\",\n" +
                "\t\"external_payment_id\": \""+ this.getExternalPaymentId()+"\",\n" +
                "\t\"promise_date\": \""+ this.getPromiseDate()+"\",\n" +
                "\t\"updated_at\": \""+ this.getUpdatedAt()+"\",\n" +
                "\t\"seller_id\": \""+ this.getSellerId()+"\",\n" +
                "\t\"tenant_id\": \""+ this.getTenantId()+"\",\n" +
                "\t\"sub_entity_reference_type\": \""+ this.getSubEntityReferenceType()+"\",\n" +
                "\t\"sub_entity_reference_id\": \""+ this.getSubEntityReferenceId()+"\",\n" +
                "\t\"external_reference_id\": \""+ this.getExternalReferenceId()+"\"\n" +
                "\t\"toa_limit_override\":" +this.isToaLimitOverride() + "\n"+
                "}";
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

    public List<Transaction> getTransactionList(){
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList){
        this.transactionList = transactionList;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        Toa toaRequest = (Toa) obj;
        if(this.referenceEntityType.equals(toaRequest.getReferenceEntityType())){
            if(this.referenceEntityId .equals(toaRequest.getReferenceEntityId())){
                if(this.toaReason.equals(toaRequest.getToaReason())){
                    if(this.toaAmount.equals(Precision.round(toaRequest.getToaAmount(), 2))){
                        if(this.toaMode.equals(toaRequest.getToaMode())){
                            if(this.toaType.equals(toaRequest.getToaType())){
                                if(this.userLogin.equals(toaRequest.getUserLogin())){
                                    if(this.quantity.equals(toaRequest.getQuantity())){
                                        if(this.customerAccountId.equals(toaRequest.getCustomerAccountId())){
                                            if(this.subEntityReferenceType.equals(toaRequest.getSubEntityReferenceType())){
                                                if(this.subEntityReferenceId.equals(toaRequest.getSubEntityReferenceId())){
                                                    if(this.externalReferenceId.equals(toaRequest.getExternalReferenceId())){
                                                        if(this.sellerId.equals(toaRequest.getSellerId())){
                                                            if(this.channel.equals(toaRequest.getChannel())){
                                                                return true;
                                                            }

                                                        }


                                                    }

                                                }

                                            }

                                        }

                                    }

                                }

                            }
                        }

                    }

                }
            }
        }

        return false;
    }
}
