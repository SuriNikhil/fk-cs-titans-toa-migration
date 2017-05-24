package com.flipkart.cp.transact.toa.domain.enums.EGV;

/**
 * Created by padmanabh.kulkarni on 26/01/16.
 */
public enum DeliveryStatus {
    DISPATCHED("DISPATCHED"),
    PENDING("PENDING"),
    FAILED("FAILED");

    private final String name;

    DeliveryStatus(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return (otherName == null) && (name.equals(otherName));
    }

    public String toString() {
        return this.name;
    }

}
