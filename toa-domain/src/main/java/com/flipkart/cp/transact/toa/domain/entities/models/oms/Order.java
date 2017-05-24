package com.flipkart.cp.transact.toa.domain.entities.models.oms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Set;

/**
 * Created by padmanabh.kulkarni on 04/02/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {
    @JsonProperty("bill_from_party_id")
    private String billFromPartyId;

    @JsonProperty("bill_to_party_id")
    private String billToPartyId;

    @JsonProperty("billing_address_id")
    private String billingAddressId;

    @JsonProperty("id")
    private String id;

    @JsonProperty("id_string")
    private String idString;

    @JsonProperty("seller_id")
    private String sellerId;

    @JsonProperty("external_id")
    private String externalId;

    @JsonProperty("shipping_address_id")
    private String shippingAddressId;

    @JsonProperty("order_items")
    private List<OrderItem> orderItems;

    @JsonProperty("reverse_serviceable")
    private boolean reverseServiceable;

    @JsonProperty("reverse_serviceable_hand_in_hand")
    private boolean reverseServiceableHandInHand;

    @JsonProperty("serviceable_by_fsd")
    private boolean serviceableByFSD;

    @JsonProperty("serviceable_by_drop_ship")
    private boolean serviceableByDropShip;

    @JsonProperty("payment_modes")
    private List<PaymentMode> paymentModes;

    @JsonProperty("refund_modes")
    private Set<RefundMode> refundModes;

    @JsonProperty("payment_methods")
    private List<PaymentMethod> paymentMethods;

    @JsonProperty("partial_refund")
    private boolean partialRefund;

    public String getBillFromPartyId() {
        return billFromPartyId;
    }

    public void setBillFromPartyId(String billFromPartyId) {
        this.billFromPartyId = billFromPartyId;
    }

    public String getBillToPartyId() {
        return billToPartyId;
    }

    public void setBillToPartyId(String billToPartyId) {
        this.billToPartyId = billToPartyId;
    }

    public String getBillingAddressId() {
        return billingAddressId;
    }

    public void setBillingAddressId(String billingAddressId) {
        this.billingAddressId = billingAddressId;
    }

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

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getShippingAddressId() {
        return shippingAddressId;
    }

    public void setShippingAddressId(String shippingAddressId) {
        this.shippingAddressId = shippingAddressId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public boolean isReverseServiceable() {
        return reverseServiceable;
    }

    public void setReverseServiceable(boolean reverseServiceable) {
        this.reverseServiceable = reverseServiceable;
    }

    public boolean isReverseServiceableHandInHand() {
        return reverseServiceableHandInHand;
    }

    public void setReverseServiceableHandInHand(boolean reverseServiceableHandInHand) {
        this.reverseServiceableHandInHand = reverseServiceableHandInHand;
    }

    public boolean isServiceableByFSD() {
        return serviceableByFSD;
    }

    public void setServiceableByFSD(boolean serviceableByFSD) {
        this.serviceableByFSD = serviceableByFSD;
    }

    public boolean isServiceableByDropShip() {
        return serviceableByDropShip;
    }

    public void setServiceableByDropShip(boolean serviceableByDropShip) {
        this.serviceableByDropShip = serviceableByDropShip;
    }

    public List<PaymentMode> getPaymentModes() {
        return paymentModes;
    }

    public void setPaymentModes(List<PaymentMode> paymentModes) {
        this.paymentModes = paymentModes;
    }

    public Set<RefundMode> getRefundModes() {
        return refundModes;
    }

    public void setRefundModes(Set<RefundMode> refundModes) {
        this.refundModes = refundModes;
    }

    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(List<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public boolean isPartialRefund() {
        return partialRefund;
    }

    public void setPartialRefund(boolean partialRefund) {
        this.partialRefund = partialRefund;
    }
}
