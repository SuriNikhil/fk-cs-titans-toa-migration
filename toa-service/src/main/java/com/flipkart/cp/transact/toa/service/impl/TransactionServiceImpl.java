//package com.flipkart.cp.transact.toa.service.impl;
//
//import com.flipkart.cp.transact.toa.dao.TransactionDAO;
//import com.flipkart.cp.transact.toa.domain.entities.models.client.Toa;
//import com.flipkart.cp.transact.toa.domain.entities.models.common.Transaction;
//import com.flipkart.cp.transact.toa.service.api.TransactionService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.inject.Inject;
//import javax.inject.Named;
//import java.util.List;
//
///**
// * Created by padmanabh.kulkarni on 14/03/16.
// */
//@Named
//public class TransactionServiceImpl implements TransactionService{
//   // private static final Logger log = LoggerFactory.getLogger(TransactionServiceImpl.class);
//
//    @Inject
//    private TransactionDAO transactionDAO;
//
//    @Override
//    public Integer addTransaction(Transaction transaction) {
//        return transactionDAO.addTransaction(transaction);
//    }
//
//    @Override
//    public Transaction getTransactionDetails(Transaction transaction) {
//        return transactionDAO.getTransactionDetails(transaction);
//    }
//
//    @Override
//    public Integer updateTransaction(Transaction transaction) {
//        return transactionDAO.updateTransaction(transaction);
//    }
//
//    @Override
//    public Integer getTransactionState(Transaction transaction) {
//        return transactionDAO.getTransactionState(transaction);
//    }
//
//    @Override
//    public List<Transaction> getTransactionsForToa(Toa toa) {
//        return transactionDAO.getTransactionsForToa(toa);
//    }
//
//    @Override
//    public boolean areAllTransactionsInStateOrAbove(Transaction transaction) {
//        Integer transactionCount = getTransactionState(transaction);
//        if(transactionCount != null && transactionCount > 0){
//            return false;
//        }
//        return true;
//    }
//
//}
