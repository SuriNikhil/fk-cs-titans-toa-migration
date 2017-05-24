package com.flipkart.cp.transact.toa.domain.entities.models.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.constraints.NotNull;

/**
 * Created by padmanabh.kulkarni on 10/09/15.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ToaItem {
    @NotNull
    @JsonProperty("toa_reference_id")
    private String toaReferenceId;

    @NotNull
    @JsonProperty("sub_entity_reference_id")
    private String subEntityReferenceId;

    @NotNull
    @JsonProperty("toa_amount")
    private Double toaAmount;

    @NotNull
    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("seller_id")
    private String sellerId;

    public String getToaReferenceId() {
        return toaReferenceId;
    }

    public void setToaReferenceId(String toaReferenceId) {
        this.toaReferenceId = toaReferenceId;
    }

    public Double getToaAmount() {
        return toaAmount;
    }

    public void setToaAmount(Double toaAmount) {
        this.toaAmount = toaAmount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


    public String getSubEntityReferenceId() {
        return subEntityReferenceId;
    }

    public void setSubEntityReferenceId(String subEntityReferenceId) {
        this.subEntityReferenceId = subEntityReferenceId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }
}
