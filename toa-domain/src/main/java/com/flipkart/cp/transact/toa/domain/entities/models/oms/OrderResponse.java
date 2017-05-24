package com.flipkart.cp.transact.toa.domain.entities.models.oms;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by padmanabh.kulkarni on 04/02/16.
 */
public class OrderResponse {
    @JsonProperty("total")
    private int total;

    @JsonProperty("count")
    private int count;

    @JsonProperty("orders")
    private Order orders[];

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Order[] getOrders() {
        return orders;
    }

    public void setOrders(Order[] orders) {
        this.orders = orders;
    }
}
