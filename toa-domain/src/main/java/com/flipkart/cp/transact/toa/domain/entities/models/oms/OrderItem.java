package com.flipkart.cp.transact.toa.domain.entities.models.oms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Set;

/**
 * Created by padmanabh.kulkarni on 04/02/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderItem extends Product{
    @JsonProperty("id")
    private String id;

    @JsonProperty("id_string")
    private String idString;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("order_item_adjustments")
    private List<OrderItemAdjustment> orderItemAdjustments;

    @JsonProperty("list_price")
    private double listPrice;

    @JsonProperty("selling_price")
    private double sellingPrice;

    @JsonProperty("seller_id")
    private String sellerId;

    @JsonProperty("order_date")
//    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss'+5:30'") //2015-05-14T00:00:00+05:30
    private String orderDate;

    @JsonProperty("status")
    private String status;

    @JsonProperty("sub_status")
    private String subStatus;

    @JsonProperty("deliver_before_date")
//    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss'+5:30'")
    private String deliverBeforeDate;

    @JsonProperty("order_item_type_id")
    private String orderItemTypeId;

    @JsonProperty("order_item_units")
    private List<OrderItemUnit> orderItemUnits;

    @JsonProperty("policy_groups")
    private List<PolicyGroup> policyGroups;

    @JsonProperty("policies")
    private List<Policy> policies;

    @JsonProperty("items_assoc_to")
    private List<ItemAssoc> itemsAssocTo;

    @JsonProperty("entity_attributes")
    private List<EntityAttribute> entityAttributes;

    @JsonProperty("sale_package")
    private String salePackage;

    @JsonProperty("return_denial_reason")
    private String returnDenialReason;

    @JsonProperty("return_allowed")
    private boolean returnAllowed;

    @JsonProperty("allowed_actions")
    private List<ReturnAction> allowedActions;

    @JsonProperty("restricted_actions")
    private Set<ReturnAction> restrictedActions;

    @JsonProperty("category_path")
    private List<String> categoryPath;

    @JsonProperty("category_id_path")
    private List<String> categoryIdPath;

    @JsonProperty("return_actions")
    private List<ReturnAction> reverseActions;

    @JsonProperty("customer_will_ship")
    private boolean customerWillShip;

    @JsonProperty("allowed_refund_mode")
    private List<RefundMode> allowedRefundMode;

    @JsonProperty("exchange_products")
    private List<Product> exchangeProducts;

    @JsonProperty("auto_authorize")
    private boolean autoAuthorize;

    @JsonProperty("can_override_auto_authorize")
    private boolean canOverrideAutoAuthorize;

    @JsonProperty("auto_auth_reason")
    private AutoAuthorizationReason autoAuthReason;

    @JsonProperty("eligible_for_slot")
    private boolean eligibleForSlot;

    @JsonProperty("bill_from_party_id")
    private String billFromPartyId;

    @JsonProperty("product_id")
    private String productId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("category_id")
    private String categoryId;


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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<OrderItemAdjustment> getOrderItemAdjustments() {
        return orderItemAdjustments;
    }

    public void setOrderItemAdjustments(List<OrderItemAdjustment> orderItemAdjustments) {
        this.orderItemAdjustments = orderItemAdjustments;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public double getListPrice() {
        return listPrice;
    }

    public void setListPrice(double listPrice) {
        this.listPrice = listPrice;
    }

    public String getBillFromPartyId() {
        return billFromPartyId;
    }

    public void setBillFromPartyId(String billFromPartyId) {
        this.billFromPartyId = billFromPartyId;
    }

    public boolean isEligibleForSlot() {
        return eligibleForSlot;
    }

    public void setEligibleForSlot(boolean eligibleForSlot) {
        this.eligibleForSlot = eligibleForSlot;
    }

    public AutoAuthorizationReason getAutoAuthReason() {
        return autoAuthReason;
    }

    public void setAutoAuthReason(AutoAuthorizationReason autoAuthReason) {
        this.autoAuthReason = autoAuthReason;
    }

    public boolean isCanOverrideAutoAuthorize() {
        return canOverrideAutoAuthorize;
    }

    public void setCanOverrideAutoAuthorize(boolean canOverrideAutoAuthorize) {
        this.canOverrideAutoAuthorize = canOverrideAutoAuthorize;
    }

    public boolean isAutoAuthorize() {
        return autoAuthorize;
    }

    public void setAutoAuthorize(boolean autoAuthorize) {
        this.autoAuthorize = autoAuthorize;
    }

    public List<Product> getExchangeProducts() {
        return exchangeProducts;
    }

    public void setExchangeProducts(List<Product> exchangeProducts) {
        this.exchangeProducts = exchangeProducts;
    }

    public List<RefundMode> getAllowedRefundMode() {
        return allowedRefundMode;
    }

    public void setAllowedRefundMode(List<RefundMode> allowedRefundMode) {
        this.allowedRefundMode = allowedRefundMode;
    }

    public boolean isCustomerWillShip() {
        return customerWillShip;
    }

    public void setCustomerWillShip(boolean customerWillShip) {
        this.customerWillShip = customerWillShip;
    }

    public List<ReturnAction> getReverseActions() {
        return reverseActions;
    }

    public void setReverseActions(List<ReturnAction> reverseActions) {
        this.reverseActions = reverseActions;
    }

    public List<String> getCategoryIdPath() {
        return categoryIdPath;
    }

    public void setCategoryIdPath(List<String> categoryIdPath) {
        this.categoryIdPath = categoryIdPath;
    }

    public List<String> getCategoryPath() {
        return categoryPath;
    }

    public void setCategoryPath(List<String> categoryPath) {
        this.categoryPath = categoryPath;
    }

    public Set<ReturnAction> getRestrictedActions() {
        return restrictedActions;
    }

    public void setRestrictedActions(Set<ReturnAction> restrictedActions) {
        this.restrictedActions = restrictedActions;
    }

    public List<ReturnAction> getAllowedActions() {
        return allowedActions;
    }

    public void setAllowedActions(List<ReturnAction> allowedActions) {
        this.allowedActions = allowedActions;
    }

    public boolean isReturnAllowed() {
        return returnAllowed;
    }

    public void setReturnAllowed(boolean returnAllowed) {
        this.returnAllowed = returnAllowed;
    }

    public String getReturnDenialReason() {
        return returnDenialReason;
    }

    public void setReturnDenialReason(String returnDenialReason) {
        this.returnDenialReason = returnDenialReason;
    }

    public String getSalePackage() {
        return salePackage;
    }

    public void setSalePackage(String salePackage) {
        this.salePackage = salePackage;
    }

    public List<EntityAttribute> getEntityAttributes() {
        return entityAttributes;
    }

    public void setEntityAttributes(List<EntityAttribute> entityAttributes) {
        this.entityAttributes = entityAttributes;
    }

    public List<ItemAssoc> getItemsAssocTo() {
        return itemsAssocTo;
    }

    public void setItemsAssocTo(List<ItemAssoc> itemsAssocTo) {
        this.itemsAssocTo = itemsAssocTo;
    }

    public List<Policy> getPolicies() {
        return policies;
    }

    public void setPolicies(List<Policy> policies) {
        this.policies = policies;
    }

    public List<PolicyGroup> getPolicyGroups() {
        return policyGroups;
    }

    public void setPolicyGroups(List<PolicyGroup> policyGroups) {
        this.policyGroups = policyGroups;
    }

    public List<OrderItemUnit> getOrderItemUnits() {
        return orderItemUnits;
    }

    public void setOrderItemUnits(List<OrderItemUnit> orderItemUnits) {
        this.orderItemUnits = orderItemUnits;
    }

    public String getOrderItemTypeId() {
        return orderItemTypeId;
    }

    public void setOrderItemTypeId(String orderItemTypeId) {
        this.orderItemTypeId = orderItemTypeId;
    }

    public String getDeliverBeforeDate() {
        return deliverBeforeDate;
    }

    public void setDeliverBeforeDate(String deliverBeforeDate) {
        this.deliverBeforeDate = deliverBeforeDate;
    }

    public String getSubStatus() {
        return subStatus;
    }

    public void setSubStatus(String subStatus) {
        this.subStatus = subStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
