//package com.flipkart.cp.transact.toa.service.impl;
//import com.flipkart.caishen.api.enums.*;
//import com.flipkart.caishen.api.models.CreditTransactionRequest;
//import com.flipkart.caishen.api.models.ProductDetail;
//import com.flipkart.caishen.api.models.UserInformation;
//import com.flipkart.caishen.api.models.WSRCompartment;
//import com.flipkart.caishen.api.transaction.requests.BatchEgvLinkRequest;
//import com.flipkart.caishen.api.transaction.requests.CreditRefundRequest;
//import com.flipkart.caishen.api.transaction.requests.CreditWSRRequest;
//import com.flipkart.caishen.api.transaction.requests.TransactionTypeRequest;
//import com.flipkart.caishen.api.wallet.requests.LimitRequest;
//import com.flipkart.caishen.api.wallet.responses.LimitResponse;
//import com.flipkart.cp.transact.toa.actions.impl.UserServiceAuthTokenCall;
//import com.flipkart.cp.transact.toa.actions.impl.UserServiceCall;
//import com.flipkart.cp.transact.toa.actions.impl.WalletLimitCheckCall;
//import com.flipkart.cp.transact.toa.actions.models.request.UserServiceRequest;
//import com.flipkart.cp.transact.toa.actions.models.response.UserServiceResponse;
//import com.flipkart.cp.transact.toa.domain.entities.models.client.Toa;
//import com.flipkart.cp.transact.toa.domain.entities.models.common.Transaction;
//import com.flipkart.cp.transact.toa.domain.entities.models.egv.EGV;
//import com.flipkart.cp.transact.toa.domain.entities.models.oms.Order;
//import com.flipkart.cp.transact.toa.domain.entities.models.oms.OrderResponse;
//import com.flipkart.cp.transact.toa.domain.enums.*;
//import com.flipkart.cp.transact.toa.service.api.*;
//import com.flipkart.cp.transact.toa.util.common.Constants;
//import com.flipkart.cp.transact.toa.util.common.CosmosUtil;
//import flipkart.cp.oms.models.orderitem.OrderItem;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.inject.Inject;
//import javax.inject.Named;
//import java.io.File;
//import java.io.FileInputStream;
//import java.util.*;
//
///**
// * Created by padmanabh.kulkarni on 15/06/15.
// */
//@Named
//public class WalletServiceImpl implements WalletService {
////    private static final Logger log = LoggerFactory.getLogger(WalletServiceImpl.class);
////    private static final Logger syslog = LoggerFactory.getLogger("sysLogger");
//
//    private static String WALLET_CREDIT_URL;
//    private static String CREDIT_CALLBACK_URL;
//    private static String WALLET_TRANSACTION_CATEGORY;
//    private static String WALLET_MERCHANT_ID;
//    private static String WALLET_CALLBACK_URL;
//    private static String WALLET_WSR_CREDIT_URL;
//    private static String WALLET_AUTH_CLIENT_ID;
//    private static String EGV_ADD_TO_WALLET_WITH_TOKEN_URL;
//    private static String EGV_ADD_TO_WALLET_CALLBACK_URI;
//
//
//    @Inject
//    private ToaService toaService;
//
//    @Inject
//    private ToaHistoryService toaHistoryService;
//
//    @Inject
//    private OutboundMessageService outboundMessageService;
//
//    @Inject
//    private OrderService orderService;
//
//    @Inject
//    private EGVService egvService;
//
//    @Inject
//    private TransactionService transactionService;
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
//                WALLET_CREDIT_URL = toaProperties.getProperty("WALLET_CREDIT_URL");
//                CREDIT_CALLBACK_URL = toaProperties.getProperty("CREDIT_CALLBACK_URL");
//                WALLET_TRANSACTION_CATEGORY = toaProperties.getProperty("WALLET_TRANSACTION_CATEGORY");
//                WALLET_MERCHANT_ID = toaProperties.getProperty("WALLET_MERCHANT_ID");
//                WALLET_CALLBACK_URL = toaProperties.getProperty("WALLET_CALLBACK_URL");
//                WALLET_WSR_CREDIT_URL = toaProperties.getProperty("WALLET_WSR_CREDIT_URL");
//                WALLET_AUTH_CLIENT_ID = toaProperties.getProperty("WALLET_AUTH_CLIENT_ID");
//                EGV_ADD_TO_WALLET_WITH_TOKEN_URL = toaProperties.getProperty("EGV_ADD_TO_WALLET_WITH_TOKEN_URL");
//                EGV_ADD_TO_WALLET_CALLBACK_URI = toaProperties.getProperty("EGV_ADD_TO_WALLET_CALLBACK_URI");
//
//
////                log.info("Static block - setTOAProperties -  WALLET_CREDIT_URL {}, CREDIT_CALLBACK_URL {},   " +
////                                "WALLET_MERCHANT_ID {}, WALLET_TRANSACTION_CATEGORY {}, " +
////                                " EGV_ADD_TO_WALLET_WITH_TOKEN_URL {}, EGV_ADD_TO_WALLET_CALLBACK_URI {}," +
////                                "WALLET_CALLBACK_URL {}, " +
////                                "WALLET_WSR_CREDIT_URL {}, WALLET_AUTH_CLIENT_ID {} ",
////                        WALLET_CREDIT_URL, CREDIT_CALLBACK_URL, WALLET_MERCHANT_ID, WALLET_TRANSACTION_CATEGORY,EGV_ADD_TO_WALLET_WITH_TOKEN_URL,
////                        EGV_ADD_TO_WALLET_CALLBACK_URI, WALLET_CALLBACK_URL, WALLET_WSR_CREDIT_URL, WALLET_AUTH_CLIENT_ID);
//            } else {
////                log.error("Static block - setTOAProperties - error in setting TOA properties");
////                syslog.info(CosmosUtil.cosmosCustomMetricString("property.loading.failure", 1));
//            }
//
//        } catch (Exception e) {
////            log.error("Static block - Error in setting TOA properties {} {} {}", e, e.getMessage(), e.getStackTrace());
////            syslog.info(CosmosUtil.cosmosCustomMetricString("property.loading.failure", 1));
//        }
//    }
//
//
//    @Override
//    public boolean addToaToWallet(Toa toa) {
////        log.info("Processing TOA to wallet for TOA id {}", toa.getId());
////        String  accountId =  fetchAccountId(toa.getCustomerAccountId());
//
//        Long transactionLimit = checkLimit(toa.getCustomerAccountId());
//
//        if(transactionLimit != null) {
//            if (toa.getToaAmount() * 100 > transactionLimit) {
////                return addToWSRWallet(toa, toa.getCustomerAccountId());  //WSR wallet
//                Integer transactionId = egvService.createTransaction(toa);
//
//                if (transactionId == null) {
//                    return false;
//                } else return true;
//            } else {
//                return addToWallet(toa, toa.getCustomerAccountId());
//            }
//        }
//        else {
////            log.debug("checkLimit returned null");
//            return false;
//        }
//    }
//
//    private boolean addToWallet(Toa toa, String accountId) {
////        log.info("TOA amount is less than transaction limit adding to caishen");
//
//        CreditRefundRequest creditRefundRequest = new CreditRefundRequest();
//        creditRefundRequest.setMerchantId(WALLET_MERCHANT_ID);
//        creditRefundRequest.setCurrencyCode(CurrencyCode.INR);
//
//        long amount = (long) (toa.getToaAmount() * 100);
////        log.debug("toaAmount {} wallet topup in paise {}", toa.getToaAmount(), amount);
//
//        creditRefundRequest.setAmount(amount);
//        creditRefundRequest.setMerchantReferenceId(toa.getExternalReferenceId());
//
////        creditRefundRequest.setProductDetails(getProductDetails(toa)); // for oms 3.0
//        creditRefundRequest.setProductDetails(getProductDetailsOMS2(toa));
//
//        //Transaction details
//        CreditTransactionRequest creditTransactionRequest = new CreditTransactionRequest();
//        creditTransactionRequest.setCreditCategory(Category.TOA);
//        creditTransactionRequest.setMerchantTxnId(toa.getToaReferenceId());
//        creditRefundRequest.setCreditTransactionRequest(creditTransactionRequest);
//
//        //User information
//        UserInformation userInformation = new UserInformation();
//        userInformation.setMerchantUserId(accountId);
//        creditRefundRequest.setUserInformation(userInformation);
//
//        Integer processedRows = addToOutBoundMessages(creditRefundRequest, WALLET_CREDIT_URL);
//        if(processedRows == null || processedRows == 0){
//            return false;
//        }
//        else {
//            return true;
//        }
//    }
//
//    private List<ProductDetail> getProductDetailsOMS2(Toa toa) {
//        OrderResponse orderResponse = orderService.getOrderDetails(toa);
//
//        for(Order order : orderResponse.getOrders()){
//            if(order.getIdString().equals(toa.getReferenceEntityId())){
//                List<com.flipkart.cp.transact.toa.domain.entities.models.oms.OrderItem> orderItemList = order.getOrderItems();
//
//                for(com.flipkart.cp.transact.toa.domain.entities.models.oms.OrderItem orderItem: orderItemList){
//                    if(orderItem.getIdString().equals(toa.getSubEntityReferenceId())){
//                        ProductDetail productDetail = new ProductDetail();
//                        productDetail.setId(orderItem.getProductId());
//                        productDetail.setTitle(orderItem.getTitle());
//                        productDetail.setQuantity(toa.getQuantity());
//                        productDetail.setProviderId(orderItem.getSellerId());
//                        productDetail.setTotalPrice((long) (orderItem.getSellingPrice() * 100));
//                        productDetail.setCategory(orderItem.getCategoryId());
//
//                        List<ProductDetail> productDetails = new ArrayList<>();
//                        productDetails.add(productDetail);
//                        return productDetails;
//                    }
//                    else
//                        continue;
//                }
//
//            }
//            else
//                continue;
//        }
//        return null;
//    }
//
//    private List<ProductDetail> getProductDetails(Toa toa) {
//        OrderItem orderItem = orderService.getOrderItem(toa);
//        if(orderItem != null) {
//            ProductDetail productDetail = new ProductDetail();
//            productDetail.setId(orderItem.getProductData().getProductId());
//            productDetail.setTitle(orderItem.getProductData().getTitle());
//            productDetail.setQuantity(toa.getQuantity());
//            productDetail.setProviderId(orderItem.getProductData().getSellerId());
//            productDetail.setTotalPrice((long) (orderItem.getProductData().getListPrice().getValue() * 100));
//            productDetail.setCategory(orderItem.getProductData().getCategoryId());
//
//            List<ProductDetail> productDetails = new ArrayList<>();
//            productDetails.add(productDetail);
//            return productDetails;
//        }
//        return null;
//    }
//
//    private Integer addToOutBoundMessages(TransactionTypeRequest transactionTypeRequest, String httpURL) {
//        String messageId = UUID.randomUUID().toString();
////        log.info("message id {}, length {}", messageId, messageId.length());
//
//        String transactionId = UUID.randomUUID().toString();
////        log.info("transaction id {}, length {}", transactionId, transactionId.length());
//
//        String correlationId = UUID.randomUUID().toString();
////        log.info("correlationId {} length {}", correlationId, correlationId.length());
//
//        return outboundMessageService.createAndInsertInOutboundMessage(transactionTypeRequest, httpURL, HttpMethod.POST.toString(),
//                HttpMethod.POST.toString(), WALLET_CALLBACK_URL, null, Constants.WALLET_MESSAGE_GROUP_ID, messageId, transactionId, correlationId);
//
//    }
//
//    private boolean addToWSRWallet(Toa toa, String accountId) {
////        log.info("Amount more than transaction limit adding to WSR wallet");
//
//        CreditWSRRequest creditWSRRequest = new CreditWSRRequest();
//
//        creditWSRRequest.setMerchantId(WALLET_MERCHANT_ID);
//        creditWSRRequest.setMerchantReferenceId(toa.getExternalReferenceId());
//        creditWSRRequest.setCreditReason(toa.getToaReason());
//
//        //call oms to get order details
//        creditWSRRequest.setProductDetails(getProductDetails(toa));
//
//        //Transaction details
//        CreditTransactionRequest creditTransactionRequest = new CreditTransactionRequest();
//        creditTransactionRequest.setCreditCategory(Category.TOA);
//        creditTransactionRequest.setMerchantTxnId(toa.getToaReferenceId());
//        creditWSRRequest.setCreditTransactionRequest(creditTransactionRequest);
//
//        //User information
//        UserInformation userInformation = new UserInformation();
//        userInformation.setMerchantUserId(accountId);
//        creditWSRRequest.setUserInformation(userInformation);
//
//        WSRCompartment wsrCompartment = new WSRCompartment();
//
//        long amount = (long) (toa.getToaAmount() * 100);
////        log.debug("toaAmount {} WSR wallet top up amount in Paise {}", toa.getToaAmount(), amount);
//        wsrCompartment.setAmount(amount);
//
//        wsrCompartment.setWsrCompartment(WSRCompartmentName.WSR_PROMOTIONAL);
//
//        List<WSRCompartment> wsrCompartments = new ArrayList<>();
//        wsrCompartments.add(wsrCompartment);
//
//        creditWSRRequest.setWsrCompartments(wsrCompartments);
//
//        //add to outbound messages
//        Integer processedRows =  addToOutBoundMessages(creditWSRRequest, WALLET_WSR_CREDIT_URL);
//
//        if(processedRows == null || processedRows == 0){
//            return false;
//        }
//        else {
//            return true;
//        }
//    }
//
//    private Long checkLimit(String accountId) {
//        LimitRequest limitRequest = new LimitRequest();
//        limitRequest.setTxnCategory(Category.TOA.name());
//        limitRequest.setMerchantId(WALLET_MERCHANT_ID);
//
//        UserInformation userInformation = new UserInformation();
//
//        userInformation.setMerchantUserId(accountId);
//
//        limitRequest.setUserInformation(userInformation);
//
////        log.debug("limitRequest {}", limitRequest.toString());
//
//        Map<String , String > headers = new HashMap<>();
//        headers.put("Content-Type", "application/json");
////        headers.put("Authorization", AuthUtil.getAuthorizationHeader(WALLET_AUTH_CLIENT_ID));
//
//        //check limit call through simple-proxy
//        try {
//            LimitResponse limitResponse = WalletLimitCheckCall.getWalletLimit(limitRequest, headers);
//
////            log.info("transction limit in paise is {}", limitResponse.getTransactionLimit());
//            //return limit
//            return limitResponse.getTransactionLimit();
//        } catch (Exception e) {
//            //log.error("Exception {} {} {}", e, e.getMessage(), e.getStackTrace());
//            return null;
//        }
//    }
//
//    @Override
//    public void actionOnSuccess(Toa  toa, TransactionResponseCode transactionResponseCode, TransactionState creditState) {
////        log.info("TOA with referenceId {} successfully processed by wallet. txnStatus {}, credit state {}", toa.getToaReferenceId(), transactionResponseCode, creditState);
//
//        toaService.markCompletionOfTOA(toa);
//    }
//
//
//    public String fetchAccountId(String customerAccountId) {
//
//        try {
//            //through simple-proxy
//            String token = UserServiceAuthTokenCall.getUserServiceAuthToken();
//
//            Map<String, String> headers = new HashMap<>();
//            headers.put("Authorization", token);
//
//            UserServiceResponse userServiceResponse = UserServiceCall.getUserServiceResponse(new UserServiceRequest(customerAccountId), headers);
//            return userServiceResponse.getPrimaryAccountId();
//        } catch (Exception e) {
////            log.error("Exception e {} {} {}", e, e.getMessage(), e.getStackTrace());
//            return null;
//        }
//    }
//
//    @Override
//    public void actionOnFailure(Toa toa, TransactionResponseCode creditResponseCode, TransactionState creditState) {
//
//        //add to toa_history
//        toaHistoryService.updateToaStatusAndAddToTOAHistory(toa, ChangedAttribute.STATUS, Event.COMPLETE, Status.FAILED, Constants.USER_LOGIN_SYSTEM, ChangeReason.FAILED_AT_WALLET, null);
//
//    }
//
//    @Override
//    public boolean addEGVToWallet(Toa toa, EGV egv, Transaction transaction) {
//
//        BatchEgvLinkRequest batchEgvLinkRequest = new BatchEgvLinkRequest();
//        batchEgvLinkRequest.setEgvToken(egv.getWalletToken());
//        batchEgvLinkRequest.setAuthToken(egv.getCreationGroupId());
//        batchEgvLinkRequest.setEgvCount(1);
//        batchEgvLinkRequest.setMerchantId(WALLET_MERCHANT_ID);
//
//
//        CreditTransactionRequest creditTransactionRequest = new CreditTransactionRequest();
//        creditTransactionRequest.setMerchantTxnId(transaction.getCode());
//        creditTransactionRequest.setCreditCategory(Category.EGV_BATCH_LINK);
//        batchEgvLinkRequest.setCreditTransactionRequest(creditTransactionRequest);
//
//        UserInformation userInformation = new UserInformation();
//        try {
//            String token = UserServiceAuthTokenCall.getUserServiceAuthToken();
//
//            if(token == null){
////                log.error("Auth token is null");
//                return false;
//            }
//
//            Map<String, String> headers = new HashMap<>();
//            headers.put("Authorization", token);
//
//            UserServiceResponse userServiceResponse = UserServiceCall.getUserServiceResponse(new UserServiceRequest(toa.getCustomerAccountId()), headers);
//
////            log.debug("primary account Id for {} is {}", toa.getCustomerAccountId(), userServiceResponse.getPrimaryAccountId());
//            userInformation.setMerchantUserId(userServiceResponse.getPrimaryAccountId());//toa.getCustomerAccountId()
////        userInformation.setMerchantUserId(toa.getCustomerAccountId());//toa.getCustomerAccountId()
//
//        } catch (Exception e) {
////            log.error("Exception {} {} {}", e, e.getMessage(), e.getStackTrace());
//            return false;
//        }
//        batchEgvLinkRequest.setUserInformation(userInformation);
//
//        batchEgvLinkRequest.setMerchantReferenceId(toa.getToaReferenceId());
//
////        log.info("linkToWalletRequest {}", batchEgvLinkRequest.toString());
//
//        String messageId = UUID.randomUUID().toString();
//        String groupId = UUID.randomUUID().toString(); //Constants.EGV_ADD_TO_WALLET_GROUP_ID;
//
//        outboundMessageService.createAndInsertInOutboundMessage(batchEgvLinkRequest, EGV_ADD_TO_WALLET_WITH_TOKEN_URL, HttpMethod.POST.name(),
//                HttpMethod.POST.name(), EGV_ADD_TO_WALLET_CALLBACK_URI, null, groupId, messageId, null, null);
//
//        return true;
//    }
//
//    @Override
//    public void retryLinkToWallet(BatchEgvLinkRequest batchEgvLinkRequest) {
//        String merchantTransactionId = batchEgvLinkRequest.getCreditTransactionRequest().getMerchantTxnId();
//        String transactionId;
//        Integer merchantTransactionIdSuffix = 0;
//
//        if(merchantTransactionId.contains("_")) {
//            transactionId =  merchantTransactionId.substring(0, merchantTransactionId.indexOf("_"));
//            merchantTransactionIdSuffix = Integer.parseInt(merchantTransactionId.substring(merchantTransactionId.indexOf("_") + 1));
//        } else {
//            transactionId =  merchantTransactionId;
//        }
//
//        if(merchantTransactionIdSuffix <= Constants.OUTBOUND_MESSAGE_RETRIES) {
////            syslog.info(CosmosUtil.cosmosCustomMetricString("add.to.wallet.failure", 1));
////            log.info("link to wallet failed retrying");
//            String messageId = UUID.randomUUID().toString();
//            String groupId = UUID.randomUUID().toString(); //Constants.EGV_ADD_TO_WALLET_GROUP_ID;
//
//            batchEgvLinkRequest.getCreditTransactionRequest().setMerchantTxnId(transactionId + Constants.WALLET_MERCHANT_TXN_ID_SEPARATOR + (merchantTransactionIdSuffix+1));
//            outboundMessageService.createAndInsertInOutboundMessage(batchEgvLinkRequest, EGV_ADD_TO_WALLET_WITH_TOKEN_URL, HttpMethod.POST.name(),
//                    HttpMethod.POST.name(), EGV_ADD_TO_WALLET_CALLBACK_URI, null, groupId, messageId, null, null);
//        } else {
////            log.error("Link to wallet retry limit exceeded for {}", transactionId);
//        }
//
//
//    }
//
//    @Override
//    public boolean actionOnLinkSuccess(BatchEgvLinkRequest batchEgvLinkRequest) {
//
//        String merchantTransactionId = batchEgvLinkRequest.getCreditTransactionRequest().getMerchantTxnId();
//
//        String transactionId;
//        if(merchantTransactionId.contains("_")) {
//            transactionId =  merchantTransactionId.substring(0, merchantTransactionId.indexOf("_"));
//        } else {
//            transactionId =  merchantTransactionId;
//        }
//
//        Transaction transaction = new Transaction();
//        transaction.setCode(transactionId);
//        transaction = egvService.getTransactionDetails(transaction);
//
//        if(transaction.getStatus().equals(com.flipkart.cp.transact.toa.domain.enums.TransactionStatus.COMPLETED)){
//           // log.info("Got duplicate request for successful transaction with id {} toa Id {} code {}", transaction.getId(), transaction.getToaId(), transaction.getCode());
//            return false;
//        }
//
//        transaction.setStatus(com.flipkart.cp.transact.toa.domain.enums.TransactionStatus.COMPLETED.getId());
//        egvService.updateTransaction(transaction);
//
//        Toa toa = toaService.getTOAById(transaction.getToaId().toString());
//
//        if (transactionService.areAllTransactionsInStateOrAbove(transaction) && toa.getStatus().equals(Status.INITIATED)){
//          //  log.info("link to wallet success for toa {}", toa.getId());
//            toaService.markCompletionOfTOA(toa);
//        }
//
//        else {
//           // log.info("toa status {} not equal to INITIATED need to wait for other transaction to get complete", toa.getStatus());
//        }
//        return true;
//    }
//
//}