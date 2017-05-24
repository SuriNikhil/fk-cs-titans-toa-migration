package com.flipkart.cp.transact.toa.domain.enums;

/**
 * Created by padmanabh.kulkarni on 17/06/15.
 */
public enum ChangeReason {
    CREATE_TOA,
    REQUEST_TO_PAYMENTS,
    INITIATED_BY_PAYMENTS,
    AUTO_PICKED,
    AUTO_COMPLETED,
    PICKED,
    COMPLETE,
    FAILED_AT_PAYMENTS,
    FAILED_AT_WALLET,
    CREATED_EGV_TRANSACTION,
    CREATED_EGV,
    LINKED_TO_WALLET;

}
