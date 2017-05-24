package com.flipkart.cp.transact.toa.domain.enums;

/**
 * Created by padmanabh.kulkarni on 28/01/16.
 */
public enum  TransactionStatus {
    CREATED(0), //transaction creation requested
    REQUESTED(1), //egv creation requested
    PROCESSED(2), //egv created
    COMPLETED(3), //egv added to wallet
    FAILED(4);

   private final int id;

    TransactionStatus(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

   /* private static Map<Integer, TransactionStatus> reverseLookup;

    static {
        reverseLookup = new HashMap<Integer, TransactionStatus>();
        for (TransactionStatus giftCardActiveStatus : TransactionStatus.values()) {
            if (reverseLookup.containsKey(giftCardActiveStatus.getId()))
                throw new IllegalStateException("codes of type must be unique");
            reverseLookup.put(giftCardActiveStatus.getId(), giftCardActiveStatus);
        }
    }

    public static TransactionStatus get(int code) {
        return reverseLookup.get(code);
    }
    */

    }
