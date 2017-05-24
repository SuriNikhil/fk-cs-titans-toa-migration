package com.flipkart.cp.transact.toa.domain.enums.EGV;

/**
 * Created by padmanabh.kulkarni on 26/01/16.
 */
public enum Format {
    JSON("JSON"),
    CSV("CSV"),
    XLS("XLS"),
    HTML("HTML"),
    TEXT("TEXT"),
    HTML_TEXT("HTML/TEXT");

    private final String name;

    Format(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return (otherName == null) && (name.equals(otherName));
    }

    public String toString() {
        return this.name;
    }
}
