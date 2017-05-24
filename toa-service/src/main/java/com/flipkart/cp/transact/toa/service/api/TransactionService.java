package com.flipkart.cp.transact.toa.service.api;

import com.flipkart.cp.transact.toa.domain.entities.models.client.Toa;
import com.flipkart.cp.transact.toa.domain.entities.models.common.Transaction;

import java.util.List;

/**
 * Created by padmanabh.kulkarni on 14/03/16.
 */
public interface TransactionService {
    Integer addTransaction(Transaction transaction);

    Transaction getTransactionDetails(Transaction transaction);

    Integer updateTransaction(Transaction transaction);

    Integer getTransactionState(Transaction transaction);

    List<Transaction> getTransactionsForToa(Toa toa);

    boolean areAllTransactionsInStateOrAbove(Transaction transaction);
}
