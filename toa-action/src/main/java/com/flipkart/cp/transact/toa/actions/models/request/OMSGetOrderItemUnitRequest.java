package com.flipkart.cp.transact.toa.actions.models.request;

/**
 * Created by padmanabh.kulkarni on 30/07/15.
 */
public class OMSGetOrderItemUnitRequest {
    private String orderId;
    private String orderItemUnitId;

    public OMSGetOrderItemUnitRequest(String orderId, String orderItemUnitId){
        this.orderId = orderId;
        this.orderItemUnitId = orderItemUnitId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderItemUnitId() {
        return orderItemUnitId;
    }

    public void setOrderItemUnitId(String orderItemUnitId) {
        this.orderItemUnitId = orderItemUnitId;
    }
}
