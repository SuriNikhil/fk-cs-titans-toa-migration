package com.flipkart.cp.transact.toa.domain.entities.models.client;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * Created by padmanabh.kulkarni on 03/02/16.
 */
public class RemainingToaResponse {
    @JsonProperty("toa_percentage")
    private Double toaPercentage;

    @JsonProperty("toa_items")
    private Map<String, RemainingToa>  toaItems;

    public Double getToaPercentage() {
        return toaPercentage;
    }

    public void setToaPercentage(Double toaPercentage) {
        this.toaPercentage = toaPercentage;
    }

    public Map<String, RemainingToa> getToaItems() {
        return toaItems;
    }

    public void setToaItems(Map<String, RemainingToa> toaItems) {
        this.toaItems = toaItems;
    }
}
