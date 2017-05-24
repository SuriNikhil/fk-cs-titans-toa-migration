package com.flipkart.cp.transact.toa.domain.entities.models.oms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by padmanabh.kulkarni on 04/02/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderItemUnit {
    @JsonProperty("id")
    private String id;

    @JsonProperty("id_string")
    private String idString;

    @JsonProperty("status")
    private String status;

    @JsonProperty("warehouse")
    private String warehouse;

    @JsonProperty("seller_id")
    private String sellerId;

    @JsonProperty("source_type")
    private String sourceType;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("order_item_unit_status_histories")
    private List<ItemStatusHistory> orderItemUnitStatusHistories;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdString() {
        return idString;
    }

    public void setIdString(String idString) {
        this.idString = idString;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<ItemStatusHistory> getOrderItemUnitStatusHistories() {
        return orderItemUnitStatusHistories;
    }

    public void setOrderItemUnitStatusHistories(List<ItemStatusHistory> orderItemUnitStatusHistories) {
        this.orderItemUnitStatusHistories = orderItemUnitStatusHistories;
    }
}
