package com.flipkart.cp.transact.toa.service.api;

import com.flipkart.cp.transact.toa.domain.entities.models.client.Toa;
import com.flipkart.cp.transact.toa.domain.entities.models.common.OutboundMessage;
import com.flipkart.cp.transact.toa.domain.entities.models.common.Transaction;

/**
 * Created by padmanabh.kulkarni on 26/01/16.
 */
public interface EGVService {
    Integer createTransaction(Toa toa);

    Transaction getTransactionDetails(Transaction transaction);

    boolean createEGV(Toa toa, Transaction transaction);

    Integer updateTransaction(Transaction transaction);

    Integer createNewTransactionForFailed(Transaction transaction);

    Integer toaAmountToEGVDenomination(double toaAmount);

    boolean retryEGVCreation(OutboundMessage outboundMessage);
}
