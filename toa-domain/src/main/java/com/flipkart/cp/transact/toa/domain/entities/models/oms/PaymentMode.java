package com.flipkart.cp.transact.toa.domain.entities.models.oms;

import java.util.Arrays;
import java.util.List;

public enum PaymentMode {
    COD(PaymentMethod.COD, PaymentMethod.POS),
    NETBANKING(PaymentMethod.NETBANKING),
    SCLP_WALLET(PaymentMethod.SCLP_WALLET),
    PREPAID(PaymentMethod.CREDIT_CARD, PaymentMethod.DEBIT_CARD, PaymentMethod.NETBANKING, PaymentMethod.PAYZIPPY),
    PREPAID_EMI(PaymentMethod.CREDIT_CARD_EMI),
    EGV(PaymentMethod.GIFT_VOUCHER),
    OFFLINE(PaymentMethod.DDCHEQUE, PaymentMethod.NEFT),
    ADVANCE(PaymentMethod.WALLET_TOPUP, PaymentMethod.WALLET_SC, PaymentMethod.WALLET_PROMOTION, PaymentMethod.WALLET_FLYTE_PROMO, PaymentMethod.WALLET_FLYTE_PREPAID),
    VAULT(PaymentMethod.QC_SCLP,PaymentMethod.QC_CLP,PaymentMethod.ICICI_SCLP,PaymentMethod.ICICI_WALLET,PaymentMethod.WSR_WALLET,PaymentMethod.SCLP_WALLET);
    PaymentMode(PaymentMethod... supportedMethods) {
        this.supportedMethods = Arrays.asList(supportedMethods);
    }

    List<PaymentMethod> supportedMethods;

    public static PaymentMode mapPaymentMode(PaymentMethod method) {
        for (PaymentMode mode : PaymentMode.values()) {
            if (mode.supportedMethods.contains(method)) {
                return mode;
            }
        }
        return null;
    }
}
