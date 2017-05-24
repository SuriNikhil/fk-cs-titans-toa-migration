package com.flipkart.cp.transact.toa.domain.entities.models.oms;

/**
 * Created by padmanabh.kulkarni on 10/02/16.
 */
public enum RefundMode {
    WALLET_SC("WALLET_SC"),
    NEFT("NEFT"),
    IMPS("IMPS"),
    BACK_TO_SOURCE("back_to_source"),
    DDCHEQUE("DDCHEQUE"),
    WALLET_PROMOTION("WALLET_PROMOTION"),
    SCLP_WALLET("SCLP_WALLET");

    private String code;

    RefundMode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
