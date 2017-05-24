package com.flipkart.cp.transact.toa.domain.entities.models.oms;


import java.util.HashMap;

public enum PaymentMethod {
    CREDIT_CARD("CREDIT_CARD"),
    DEBIT_CARD("DEBIT_CARD"),
    DDCHEQUE("DDCHEQUE"),
    NETBANKING("NETBANKING"),
    WALLET_TOPUP("WALLET_TOPUP"),
    WALLET_SC("WALLET_SC"),
    WALLET_PROMOTION("WALLET_PROMOTION"),
    GIFT_VOUCHER("GIFT_VOUCHER"),
    NEFT("NEFT"),
    IMPS("IMPS"),
    COD("COD"),
    CREDIT_CARD_EMI("CREDIT_CARD_EMI"),
    POS("POS"),
    WALLET_FLYTE_PROMO("WALLET_FLYTE_PROMO"),
    WALLET_FLYTE_PREPAID("WALLET_FLYTE_PREPAID"),
    PAYZIPPY("PAYZIPPY"),
    QC_CLP("QC-CLP"),
    QC_SCLP("QC-SCLP"),
    ICICI_SCLP("ICICI-SCLP"),
    ICICI_WALLET("ICICI-WALLET"),
    WSR_WALLET("WSR-WALLET"),
    SCLP_WALLET("SCLP-WALLET");

    String method;
    PaymentMethod(String paymentMethod){
        this.method = paymentMethod;
    }

    public String getMethod(){
        return method;
    }

    static HashMap<String,PaymentMethod> reverseLookup = new HashMap<String, PaymentMethod>();
    static {
        reverseLookup.put(PaymentMethod.COD.getMethod(),PaymentMethod.COD);
        reverseLookup.put(PaymentMethod.CREDIT_CARD.getMethod(),PaymentMethod.CREDIT_CARD);
        reverseLookup.put(PaymentMethod.CREDIT_CARD_EMI.getMethod(),PaymentMethod.CREDIT_CARD_EMI);
        reverseLookup.put(PaymentMethod.DDCHEQUE.getMethod(),PaymentMethod.DDCHEQUE);
        reverseLookup.put(PaymentMethod.DEBIT_CARD.getMethod(),PaymentMethod.DEBIT_CARD);
        reverseLookup.put(PaymentMethod.GIFT_VOUCHER.getMethod(),PaymentMethod.GIFT_VOUCHER);
        reverseLookup.put(PaymentMethod.IMPS.getMethod(),PaymentMethod.IMPS);
        reverseLookup.put(PaymentMethod.NEFT.getMethod(),PaymentMethod.NEFT);
        reverseLookup.put(PaymentMethod.NETBANKING.getMethod(),PaymentMethod.NETBANKING);
        reverseLookup.put(PaymentMethod.PAYZIPPY.getMethod(),PaymentMethod.PAYZIPPY);
        reverseLookup.put(PaymentMethod.POS.getMethod(),PaymentMethod.POS);
        reverseLookup.put(PaymentMethod.WALLET_FLYTE_PREPAID.getMethod(),PaymentMethod.WALLET_FLYTE_PREPAID);
        reverseLookup.put(PaymentMethod.WALLET_FLYTE_PROMO.getMethod(),PaymentMethod.WALLET_FLYTE_PROMO);
        reverseLookup.put(PaymentMethod.WALLET_PROMOTION.getMethod(),PaymentMethod.WALLET_PROMOTION);
        reverseLookup.put(PaymentMethod.WALLET_SC.getMethod(),PaymentMethod.WALLET_SC);
        reverseLookup.put(PaymentMethod.WALLET_TOPUP.getMethod(),PaymentMethod.WALLET_TOPUP);
        reverseLookup.put(PaymentMethod.QC_CLP.getMethod(),PaymentMethod.QC_CLP);
        reverseLookup.put(PaymentMethod.QC_SCLP.getMethod(),PaymentMethod.QC_SCLP);
        reverseLookup.put(PaymentMethod.ICICI_SCLP.getMethod(),PaymentMethod.ICICI_SCLP);
        reverseLookup.put(PaymentMethod.ICICI_WALLET.getMethod(),PaymentMethod.ICICI_WALLET);
        reverseLookup.put(PaymentMethod.WSR_WALLET.getMethod(),PaymentMethod.WSR_WALLET);
        reverseLookup.put(PaymentMethod.SCLP_WALLET.getMethod(),PaymentMethod.SCLP_WALLET);

    }
    public static PaymentMethod getPaymentMethod(String method){
        return reverseLookup.get(method);
    }
}

