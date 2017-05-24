package com.flipkart.cp.transact.toa.actions.models.request;

/**
 * Created by padmanabh.kulkarni on 30/07/15.
 */
public class OMSGetOrderItemRequest {
    private String orderId;
    private String orderItemId; //orderItemId or orderItemUnitId

    public OMSGetOrderItemRequest(String orderId, String orderItemId){
        this.orderId = orderId;
        this.orderItemId = orderItemId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(String orderItemId) {
        this.orderItemId = orderItemId;
    }
}
