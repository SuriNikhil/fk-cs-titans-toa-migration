package com.flipkart.cp.transact.toa.domain.entities.models.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.Map;

/**
 * Created by padmanabh.kulkarni on 24/06/15.
 */
public class PaymentRequest {

    @JsonProperty("payment_type")
    @SerializedName("payment_type")
    private String paymentType;   //GOODWILL

    @SerializedName("payment_method")
    @JsonProperty("payment_method")
    private String paymentMethod; //   WALLET_PROMOTION

    @SerializedName("reason")
    @JsonProperty("reason")
    private String reason;

    @SerializedName("flipkart_ref_id")
    @JsonProperty("flipkart_ref_id")
    private String flipkartRefId;   //order id

    @JsonProperty("comments")
    @SerializedName("comments")
    private String comments;

    @SerializedName("order_item_amounts")
    @JsonProperty("order_item_amounts")
    private Map<String, Double> orderItemAmounts;    //map of order_item_id and and their amounts

    @SerializedName("party_id_to")
    @JsonProperty("party_id_to")
    private String partyIdTo;      //customer account id

    @SerializedName("party_id_from")
    @JsonProperty("party_id_from")
    private String partyIdFrom;    //seller id

    @SerializedName("price_drop_refund_quantity")
    @JsonProperty("price_drop_refund_quantity")
    private Map<String, Integer> priceDropRefundQuantity;

    @SerializedName("promised_amount")
    @JsonProperty("promised_amount")
    private Double promisedAmount;

    @SerializedName("paid_amount")
    @JsonProperty("paid_amount")
    private Double paidAmount;

    @SerializedName("refund_id")
    @JsonProperty("refund_id")
    private String refundId;

    @SerializedName("user_login")
    @JsonProperty("user_login")
    private String userLogin;

    @SerializedName("order_id")
    @JsonProperty("order_id")
    private String orderId;

    @SerializedName("seller_id")
    @JsonProperty("seller_id")
    private String sellerId;

    @SerializedName("payment_date")
    @JsonProperty("payment_date")
    private Date paymentDate;

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getFlipkartRefId() {
        return flipkartRefId;
    }

    public void setFlipkartRefId(String flipkartRefId) {
        this.flipkartRefId = flipkartRefId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Map<String, Double> getOrderItemAmounts() {
        return orderItemAmounts;
    }

    public void setOrderItemAmounts(Map<String, Double> orderItemAmounts) {
        this.orderItemAmounts = orderItemAmounts;
    }

    public String getPartyIdTo() {
        return partyIdTo;
    }

    public void setPartyIdTo(String partyIdTo) {
        this.partyIdTo = partyIdTo;
    }

    public String getPartyIdFrom() {
        return partyIdFrom;
    }

    public void setPartyIdFrom(String partyIdFrom) {
        this.partyIdFrom = partyIdFrom;
    }

    public Map<String, Integer> getPriceDropRefundQuantity() {
        return priceDropRefundQuantity;
    }

    public void setPriceDropRefundQuantity(Map<String, Integer> priceDropRefundQuantity) {
        this.priceDropRefundQuantity = priceDropRefundQuantity;
    }

    public Double getPromisedAmount() {
        return promisedAmount;
    }

    public void setPromisedAmount(Double promisedAmount) {
        this.promisedAmount = promisedAmount;
    }

    public Double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }


}
