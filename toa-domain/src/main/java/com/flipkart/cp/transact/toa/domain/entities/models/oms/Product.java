package com.flipkart.cp.transact.toa.domain.entities.models.oms;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by padmanabh.kulkarni on 04/02/16.
 */
public class Product {
    protected int categoryId;
    protected String sellerId;
    protected String listingId;
    protected double sellingPrice;
    protected String productId;
    protected String tenantId;
    protected String sku;
    protected String size;
    protected String serviceProfile;
    protected boolean available;
    protected boolean serviceable;
    protected String listingState;
    protected String listingStatus;
    protected String erpTitle;
    protected String vertical;
    protected String shippingCategory;
    protected boolean fragile;
    protected boolean dangerous;
    protected String scPreferred;
    protected List<String> exchangeOptions;
    protected List<String> supplyChainHandling = new ArrayList<String>();
    protected boolean dropshipBySeller;
    protected String  length;
    protected String breadth;
    protected String height;
    protected String weight;

}
