//package com.flipkart.cp.transact.toa.service.impl;
//
//import com.flipkart.cp.transact.toa.dao.MerchantDAO;
//import com.flipkart.cp.transact.toa.domain.entities.models.client.Toa;
//import com.flipkart.cp.transact.toa.domain.entities.models.common.Merchant;
//import com.flipkart.cp.transact.toa.domain.entities.models.common.OutboundMessage;
//import com.flipkart.cp.transact.toa.domain.entities.models.common.Transaction;
//import com.flipkart.cp.transact.toa.domain.entities.models.egv.EGV;
//import com.flipkart.cp.transact.toa.domain.entities.models.egv.Recipient;
//import com.flipkart.cp.transact.toa.domain.enums.HttpMethod;
//import com.flipkart.cp.transact.toa.domain.enums.TransactionStatus;
//import com.flipkart.cp.transact.toa.domain.enums.EGV.Format;
//import com.flipkart.cp.transact.toa.domain.enums.EGV.Medium;
//import com.flipkart.cp.transact.toa.service.api.EGVService;
//import com.flipkart.cp.transact.toa.service.api.OutboundMessageService;
//import com.flipkart.cp.transact.toa.service.api.TransactionService;
//import com.flipkart.cp.transact.toa.service.api.WalletService;
//import com.flipkart.cp.transact.toa.util.common.Constants;
//import com.flipkart.cp.transact.toa.util.common.CosmosUtil;
//import com.flipkart.cp.transact.toa.util.common.DataSecurityUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.inject.Inject;
//import javax.inject.Named;
//import java.io.File;
//import java.io.FileInputStream;
//import java.util.Date;
//import java.util.Properties;
//import java.util.UUID;
//
///**
// * Created by padmanabh.kulkarni on 26/01/16.
// */
//@Named
//@Slf4j
//public class EGVServiceImpl implements EGVService {
////    private static final Logger log = LoggerFactory.getLogger(EGVServiceImpl.class);
////    private static final Logger syslog = LoggerFactory.getLogger("sysLogger");
//
//    @Inject
//    private TransactionService transactionService;
//
//    @Inject
//    private MerchantDAO merchantDAO;
//
//    @Inject
//    private OutboundMessageService outboundMessageService;
//
//    @Inject
//    private DataSecurityUtil dataSecurityUtil;
//
//    @Inject
//    private WalletService walletService;
//
//    private static String GCMS_CREATE_TRANSACTION_URL;
//    private static String GCMS_CREATE_TRANSACTION_CALLBACK_URL;
//    private static String GCMS_CREATE_EGV_URL;
//    private static String GCMS_CREATE_EGV_CALLBACK_URL;
//    private static String WALLET_MERCHANT_ID;
//    private static Integer MAXIMUM_EGV_DENOMINATION;
//    private static Integer MINIMUM_EGV_DENOMINATION;
//
//    static {
//        // Reads the properties file based on environment and sets the TOA properties
//        try {
//            File file = new File(Constants.TOA_CONFIG_FILE);
//            FileInputStream fileInputStream = new FileInputStream(file);
//
//            Properties toaProperties = new Properties();
//            toaProperties.load(fileInputStream);
//
//            if (toaProperties != null) {
//                GCMS_CREATE_TRANSACTION_URL = toaProperties.getProperty("GCMS_CREATE_TRANSACTION_URL");
//                GCMS_CREATE_TRANSACTION_CALLBACK_URL = toaProperties.getProperty("GCMS_CREATE_TRANSACTION_CALLBACK_URL");
//                GCMS_CREATE_EGV_URL = toaProperties.getProperty("GCMS_CREATE_EGV_URL");
//                GCMS_CREATE_EGV_CALLBACK_URL = toaProperties.getProperty("GCMS_CREATE_EGV_CALLBACK_URL");
//                WALLET_MERCHANT_ID = toaProperties.getProperty("WALLET_MERCHANT_ID");
//                MAXIMUM_EGV_DENOMINATION = Integer.parseInt(toaProperties.getProperty("MAXIMUM_EGV_DENOMINATION"));
//                MINIMUM_EGV_DENOMINATION = Integer.parseInt(toaProperties.getProperty("MINIMUM_EGV_DENOMINATION"));
//
////TODO
////                log.info("Static block - setTOAProperties -  REACH_OUT_EVENT_URL {}, GCMS_CREATE_TRANSACTION_CALLBACK_URL {}" +
////                        "WALLET_MERCHANT_ID {}," +
////                        " MAXIMUM_EGV_DENOMINATION {} MINIMUM_EGV_DENOMINATION {}", "" +
////                        GCMS_CREATE_TRANSACTION_URL + "," + GCMS_CREATE_TRANSACTION_CALLBACK_URL + "," +
////                        WALLET_MERCHANT_ID + "," + MAXIMUM_EGV_DENOMINATION, MINIMUM_EGV_DENOMINATION);
//            } else {
//               // log.error("Static block - setTOAProperties - error in setting TOA properties");
//                //TODO
//
//                //   syslog.info(CosmosUtil.cosmosCustomMetricString("property.loading.failure", 1));
//            }
//
//        } catch (Exception e) {
//            //log.error("Static block - Error in setting TOA properties {} {} {}" + e, e.getMessage(), e.getStackTrace());
//            //  syslog.info(CosmosUtil.cosmosCustomMetricString("property.loading.failure", 1));
//        }
//    }
//
//    @Override
//    public Transaction getTransactionDetails(Transaction transaction) {
//        return transactionService.getTransactionDetails(transaction);
//    }
//
//    @Override
//    public Integer updateTransaction(Transaction transaction) {
//        if (transaction.getUpdatedDate() == null) {
//            transaction.setUpdatedDate(new Date());
//        }
//        return transactionService.updateTransaction(transaction);
//    }
//
//    @Override
//    public boolean createEGV(Toa toa, Transaction transaction) {
//        EGV egv = new EGV();
//        egv.setTransactionId(transaction.getCode());
//        egv.setClientPoNumber(toa.getExternalReferenceId());
//        egv.setDenomination(toaAmountToEGVDenomination(transaction.getAmount()));
//
//        Recipient recipient = new Recipient();
//        recipient.setMedium(Medium.INLINE.toString());
//        recipient.setFormat(Format.JSON.toString());
//        recipient.setWalletInfoRequired(true);
//
//        egv.setRecipient(recipient);
//
//       // log.info("create EGV request {}", egv.toString());
//
//        Merchant merchant = new Merchant();
//        merchant.setId(transaction.getMerchantId());
//
//        merchant = merchantDAO.getMerchant(merchant);
//
//        String customHeaders = "{\"" + Constants.GCMS_CLIENT_ID_HEADER + "\":\"" + decryptString(merchant.getUsername()) + "\",\""
//                + Constants.GCMS_CLIENT_TOKEN_HEADER + "\":\"" + decryptString(merchant.getPassword()) + "\"}";
//
//        String messageId = UUID.randomUUID().toString();
//        String varadhiTransactionId = UUID.randomUUID().toString();
//        String correlationId = UUID.randomUUID().toString();
//
//        String groupId = UUID.randomUUID().toString(); //Constants.GCMS_CREATE_TRANSACTION_GROUP_ID;  //todo change
//
//        outboundMessageService.createAndInsertInOutboundMessage(egv, GCMS_CREATE_EGV_URL,
//                HttpMethod.POST.toString(), HttpMethod.POST.toString(), GCMS_CREATE_EGV_CALLBACK_URL,
//                customHeaders, groupId,
//                messageId, varadhiTransactionId, correlationId
//        );
//
//        return true;
//    }
//
//    @Override
//    public Integer toaAmountToEGVDenomination(double toaAmount) {
//        //Caller will call after rounding off to a whole number still taking floor to ensure
//        return (int) toaAmount;
//    }
//
//    @Override
//    public boolean retryEGVCreation(OutboundMessage outboundMessage) {
//        outboundMessage.setRelayed(null);
//        Integer updatedRows = outboundMessageService.updateOutboundMessage(outboundMessage);
//        if (updatedRows == 1) {
//        //    log.info("updated outbound message");
//            return true;
//        } else {
//         //   log.info("Failed to update outbound message");
//            return false;
//        }
//
//
//    }
//
//    @Override
//    public Integer createTransaction(Toa toa) {
//        //get merchant
//        Merchant merchant = new Merchant();
//        merchant.setId(toa.getMerchantId());
//
//        merchant = merchantDAO.getMerchant(merchant);
//
//        if (merchant == null) {
//            return null;
//        }
//        //create db entry
//        Transaction transaction = new Transaction();
//        transaction.setToaId(toa.getId());
//        transaction.setMerchantId(merchant.getId());
//        transaction.setStatus(TransactionStatus.CREATED.getId());
//
//        Double toaAmount = toaAmountToEGVDenomination(toa.getToaAmount()).doubleValue();
//        while (toaAmount > MAXIMUM_EGV_DENOMINATION) {
//            //what if minimum EGV denomination is > 1
//            if (toaAmount - MAXIMUM_EGV_DENOMINATION >= MINIMUM_EGV_DENOMINATION) {
//                transaction.setAmount((double) MAXIMUM_EGV_DENOMINATION);
//                toaAmount -= transaction.getAmount();
//            } else {
//                transaction.setAmount(Math.ceil(toaAmount / 2));
//                toaAmount -= transaction.getAmount();
//            }
//            transactionService.addTransaction(transaction);
//            //log.debug("transaction amount {}", transaction.getAmount());
//            addTransactionToOutboundMessages(transaction, merchant);
//        }
//        transaction.setAmount(toaAmount);
//        transactionService.addTransaction(transaction);
//       // log.debug("transaction amount {}", transaction.getAmount());
//        addTransactionToOutboundMessages(transaction, merchant);
//
//        return transaction.getId();
//    }
//
//    @Override
//    public Integer createNewTransactionForFailed(Transaction oldTransaction) {
//        Transaction newTransaction = new Transaction();
//        newTransaction.setToaId(oldTransaction.getToaId());
//        newTransaction.setStatus(TransactionStatus.CREATED.getId());
//        newTransaction.setAmount(oldTransaction.getAmount());
//        newTransaction.setMerchantId(oldTransaction.getMerchantId());
//        transactionService.addTransaction(newTransaction);
//
//        Merchant merchant = new Merchant();
//        merchant.setId(oldTransaction.getMerchantId());
//        merchant = merchantDAO.getMerchant(merchant);
//
//        return addTransactionToOutboundMessages(newTransaction, merchant);
//
//    }
//
//
//    private Integer addTransactionToOutboundMessages(Transaction transaction, Merchant merchant) {
//        //log.info("createTransaction payload {}", transaction.toString());
//
//        String customHeaders = "{\"" + Constants.GCMS_CLIENT_ID_HEADER + "\":\"" + decryptString(merchant.getUsername()) + "\",\""
//                + Constants.GCMS_CLIENT_TOKEN_HEADER + "\":\"" + decryptString(merchant.getPassword()) + "\"}";
//
//        String messageId = UUID.randomUUID().toString();
//        String varadhiTransactionId = UUID.randomUUID().toString();
//        String correlationId = UUID.randomUUID().toString();
//        String groupId = UUID.randomUUID().toString(); //Constants.GCMS_CREATE_TRANSACTION_GROUP_ID; //todo change
//        String createTransactionURI = GCMS_CREATE_TRANSACTION_URL + "?ref=" + messageId;
//
//        return outboundMessageService.createAndInsertInOutboundMessage(transaction, createTransactionURI, HttpMethod.POST.toString(),
//                HttpMethod.POST.toString(), GCMS_CREATE_TRANSACTION_CALLBACK_URL, customHeaders, groupId,
//                messageId, varadhiTransactionId, correlationId);
//    }
//
//    private String decryptString(String encryptedString) {
//        if (encryptedString == null)
//            return null;
//
//        else {
//            return dataSecurityUtil.doDecrypt(encryptedString);
//        }
//
//    }
//}
