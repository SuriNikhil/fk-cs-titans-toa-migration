package com.flipkart.cp.transact.toa.service.api;

import com.flipkart.caishen.api.enums.TransactionResponseCode;
import com.flipkart.caishen.api.enums.TransactionState;
import com.flipkart.caishen.api.transaction.requests.BatchEgvLinkRequest;
import com.flipkart.cp.transact.toa.domain.entities.models.client.Toa;
import com.flipkart.cp.transact.toa.domain.entities.models.common.Transaction;
import com.flipkart.cp.transact.toa.domain.entities.models.egv.EGV;

/**
 * Created by padmanabh.kulkarni on 15/06/15.
 */
public interface WalletService {
    /**
     *
     * @param toa
     * @return
     */
    boolean addToaToWallet(Toa toa);

    /**
     *
     * @param toa
     * @param creditState
     */
    void actionOnSuccess(Toa toa, TransactionResponseCode transactionResponseCode, TransactionState creditState);

    /**
     *
     * @param customerAccountId
     * @return
     */
    String fetchAccountId(String customerAccountId);

    void actionOnFailure(Toa toa, TransactionResponseCode creditResponseCode, TransactionState creditState);

    boolean addEGVToWallet(Toa toa, EGV egv, Transaction transaction);

    void retryLinkToWallet(BatchEgvLinkRequest batchEgvLinkRequest);

    boolean actionOnLinkSuccess(BatchEgvLinkRequest batchEgvLinkRequest);
}
