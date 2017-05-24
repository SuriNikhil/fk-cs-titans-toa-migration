package com.flipkart.cp.transact.toa.domain.entities.models.oms;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by padmanabh.kulkarni on 04/02/16.
 */
public class OrderItemAdjustment {
    @JsonProperty("id")
    private String id;

    @JsonProperty("order_item_id")
    private String orderItemId;

    @JsonProperty("amount")
    private float amount;

    @JsonProperty("percentage")
    private float percentage;

    @JsonProperty("adjustment_type")
    private String adjustmentType;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("details")
    private String details;

    @JsonProperty("seller_id")
    private String sellerId;

    @JsonProperty("tenant_id")
    private String tenantId;

    @JsonProperty("updated_at")
    private String updatedAt;

    @JsonProperty("id_string")
    private String idString;

    @JsonProperty("order_item_id_string")
    private String orderItemIdString;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public String getAdjustmentType() {
        return adjustmentType;
    }

    public void setAdjustmentType(String adjustmentType) {
        this.adjustmentType = adjustmentType;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(String orderItemId) {
        this.orderItemId = orderItemId;
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

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getIdString() {
        return idString;
    }

    public void setIdString(String idString) {
        this.idString = idString;
    }

    public String getOrderItemIdString() {
        return orderItemIdString;
    }

    public void setOrderItemIdString(String orderItemIdString) {
        this.orderItemIdString = orderItemIdString;
    }
}
