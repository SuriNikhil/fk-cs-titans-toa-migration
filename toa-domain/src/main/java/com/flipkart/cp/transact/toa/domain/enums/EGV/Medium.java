package com.flipkart.cp.transact.toa.domain.enums.EGV;

/**
 * Created by padmanabh.kulkarni on 26/01/16.
 */
public enum  Medium {
    INLINE("INLINE"),
    SMS("SMS"),
    EMAIL("EMAIL"),
    EMAIL_AND_SMS("EMAIL/SMS");

    private final String name;

    Medium(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return (otherName == null) && (name.equals(otherName));
    }

    public String toString() {
        return this.name;
    }
}
