//package com.flipkart.cp.transact.toa.service.impl;
//
//import com.flipkart.cp.transact.toa.actions.impl.CASCall;
//import com.flipkart.cp.transact.toa.actions.models.request.CASRequest;
//import com.flipkart.cp.transact.toa.dao.ToaDAO;
//import com.flipkart.cp.transact.toa.domain.entities.models.client.RemainingToa;
//import com.flipkart.cp.transact.toa.domain.entities.models.client.RemainingToaRequest;
//import com.flipkart.cp.transact.toa.domain.entities.models.client.RemainingToaResponse;
//import com.flipkart.cp.transact.toa.domain.entities.models.client.Toa;
//import com.flipkart.cp.transact.toa.domain.entities.models.common.Merchant;
//import com.flipkart.cp.transact.toa.domain.entities.models.common.OutboundMessage;
//import com.flipkart.cp.transact.toa.domain.entities.models.common.Transaction;
//import com.flipkart.cp.transact.toa.domain.entities.models.oms.Order;
//import com.flipkart.cp.transact.toa.domain.entities.models.oms.OrderItem;
//import com.flipkart.cp.transact.toa.domain.entities.models.oms.OrderItemAdjustment;
//import com.flipkart.cp.transact.toa.domain.entities.models.oms.OrderResponse;
//import com.flipkart.cp.transact.toa.domain.entities.models.payment.PaymentCallback;
//import com.flipkart.cp.transact.toa.domain.entities.models.payment.PaymentRequest;
//import com.flipkart.cp.transact.toa.domain.enums.*;
//import com.flipkart.cp.transact.toa.domain.exceptions.BadRequestException;
//import com.flipkart.cp.transact.toa.domain.exceptions.InvalidMerchantException;
//import com.flipkart.cp.transact.toa.domain.exceptions.OMSCallException;
//import com.flipkart.cp.transact.toa.service.api.*;
//import com.flipkart.cp.transact.toa.util.common.Constants;
//import com.flipkart.cp.transact.toa.util.common.CosmosUtil;
//import org.apache.commons.math3.util.Precision;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.inject.Inject;
//import javax.inject.Named;
//import java.io.File;
//import java.io.FileInputStream;
//import java.sql.SQLIntegrityConstraintViolationException;
//import java.util.*;
//
///**
// * Created by padmanabh.kulkarni on 10/06/15.
// */
//@Named
//public class ToaServiceImpl implements ToaService{
////    private static final Logger log = LoggerFactory.getLogger(ToaServiceImpl.class);
////    private static final Logger syslog = LoggerFactory.getLogger("sysLogger");
//
//    private static String PAYMENT_CALLBACK_URL;
//    private static String PAYMENT_URL;
//    private static Double TOA_OVERRIDE_PERCENTAGE;
//    private static Double TOA_PERCENTAGE;
//    private static Boolean BYPASS_SC_PAYMENTS;
//    private static Integer MINIMUM_EGV_DENOMINATION;
//    private static Integer MAXIMUM_EGV_DENOMINATION;
//    private static Double ALLOWED_TOA_AMOUNT_AFTER_OVERRIDE;
//    private static Boolean IS_WALLET_PROMOTION_ACTIVE;
//
//    @Inject
//    private ToaDAO toaDAO;
//
//    @Inject
//    private TransactionService transactionService;
//
//    @Inject
//    private ToaHistoryService toaHistoryService;
//
//    @Inject
//    private OutboundMessageService outboundMessageService;
//
//    @Inject
//    private WalletService walletService;
//
//    @Inject
//    private OrderService orderService;
//
//    @Inject
//    private EventService eventService;
//
//    @Inject
//    private MerchantService merchantService;
//
//    @Inject
//    private EGVService egvService;
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
//                PAYMENT_CALLBACK_URL = toaProperties.getProperty("PAYMENT_CALLBACK_URL");
//                PAYMENT_URL = toaProperties.getProperty("PAYMENT_URL");
//                TOA_OVERRIDE_PERCENTAGE = Double.parseDouble(toaProperties.getProperty("TOA_OVERRIDE_PERCENTAGE"));
//                TOA_PERCENTAGE = Double.parseDouble(toaProperties.getProperty("TOA_PERCENTAGE"));
//                BYPASS_SC_PAYMENTS = Boolean.getBoolean(toaProperties.getProperty("BYPASS_SC_PAYMENTS"));
//                MINIMUM_EGV_DENOMINATION = Integer.parseInt(toaProperties.getProperty("MINIMUM_EGV_DENOMINATION"));
//                MAXIMUM_EGV_DENOMINATION = Integer.parseInt(toaProperties.getProperty("MAXIMUM_EGV_DENOMINATION"));
//                ALLOWED_TOA_AMOUNT_AFTER_OVERRIDE = Double.parseDouble(toaProperties.getProperty("ALLOWED_TOA_AMOUNT_AFTER_OVERRIDE"));
//                IS_WALLET_PROMOTION_ACTIVE = Boolean.parseBoolean(toaProperties.getProperty("IS_WALLET_PROMOTION_ACTIVE"));
//
////                log.info("Static block - setTOAProperties -    PAYMENT_CALLBACK_URL {}, PAYMENT_URL {},  " +
////                               "TOA_OVERRIDE_PERCENTAGE {}, TOA_PERCENTAGE {}, BYPASS_SC_PAYMENTS {}" +
////                                "MINIMUM_EGV_DENOMINATION {}, MAXIMUM_EGV_DENOMINATION {} ALLOWED_TOA_AMOUNT_AFTER_OVERRIDE {}" +
////                                " IS_WALLET_PROMOTION_ACTIVE {}",
////                        PAYMENT_CALLBACK_URL, PAYMENT_URL,  TOA_OVERRIDE_PERCENTAGE, TOA_PERCENTAGE, BYPASS_SC_PAYMENTS,
////                        MINIMUM_EGV_DENOMINATION, MAXIMUM_EGV_DENOMINATION, ALLOWED_TOA_AMOUNT_AFTER_OVERRIDE,
////                        IS_WALLET_PROMOTION_ACTIVE);
//            } else {
//               // log.error("Static block - setTOAProperties - error in setting TOA properties");
//               // syslog.info(CosmosUtil.cosmosCustomMetricString("property.loading.failure", 1));
//            }
//
//        } catch (Exception e) {
//            //log.error("Static block - Error in setting TOA properties {} {} {}", e, e.getMessage(), e.getStackTrace());
//         //   syslog.info(CosmosUtil.cosmosCustomMetricString("property.loading.failure", 1));
//        }
//    }
//
//
//    @Override
//    public Integer insertInToa(Toa toa) throws SQLIntegrityConstraintViolationException{
//        return toaDAO.insertInToa(toa);
//
//    }
//
//    @Override
//    public Integer updateTOA(Toa toa) {
//        if(toa.getUpdatedAt() == null){
//            toa.setUpdatedAt(new Date());
//        }
//        return toaDAO.updateTOA(toa);
//    }
//
//    @Override
//    public Toa getTOAById(String id) {
//        //log.debug("id {}", id);
//        return toaDAO.getToaById(id);
//    }
//
//    @Override
//    public Toa getTOAByToaReferenceId(String toaReferenceId) {
//        Toa toa = toaDAO.getToaByToaReferenceId(toaReferenceId);
//
//        List<Transaction> transactionList = transactionService.getTransactionsForToa(toa);
//        List<Toa> toaList = new ArrayList<>();
//        toaList.add(toa);
//
//        List<Toa> toaTransactionList = mapTransactionsToToa(toaList, transactionList);
//
//        return toaTransactionList.get(0);
//    }
//
//    @Override
//    public boolean processTOA(Toa toa) throws SQLIntegrityConstraintViolationException, BadRequestException, OMSCallException, InvalidMerchantException {
//
//        if(basicChecks(toa)) {
//            Toa existingToa = toaDAO.getToaByToaReferenceId(toa.getToaReferenceId());
//            if (existingToa != null) {
//           //     log.debug("ToaReferenceId already exists in db. Checking for other params in request.");
//                if (existingToa.equals(toa)) {
//                    return true;
//                }
//                    return false;
//            }
//
//            if (toa.getReferenceEntityType().equals(EntityReferenceType.ORDER)) {
//                OrderResponse orderResponse = orderService.getOrderDetails(toa);
//                if(orderResponse == null){
//                    throw new OMSCallException(Constants.EXTERNAL_SERVICE_CALL_FAILED);
//                }
//                orderRelatedChecks(orderResponse, toa);
//
//                Integer merchantId = getMerchantInfo(toa);
//                if (merchantId != null && merchantId != 0) {
//                    toa.setMerchantId(merchantId);
//
//                    Double maxToaPercentage;
//                    if (toa.isToaLimitOverride()) {
//                 //       log.info("toa override with percentage {} and ALLOWED_TOA_AMOUNT_AFTER_OVERRIDE {}", TOA_OVERRIDE_PERCENTAGE, ALLOWED_TOA_AMOUNT_AFTER_OVERRIDE);
//                        maxToaPercentage = TOA_OVERRIDE_PERCENTAGE;
//                    } else {
//                     //   log.info("toa not override with percentage {}", TOA_PERCENTAGE);
//                        maxToaPercentage = TOA_PERCENTAGE;
//                    }
//
//                   // log.info("toa percentage {}", maxToaPercentage);
//
//                    if (maxToaPercentage == null)
//                        return false;
//
////            Double maximumAllowedToa = orderService.findMaximumAllowedToa(maxToaPercentage, toa);   for OMS 3.0
//                    List<String> subReferenceIds = new ArrayList<>();
//                    subReferenceIds.add(toa.getSubEntityReferenceId());
//
//                    RemainingToaRequest remainingToaRequest = new RemainingToaRequest();
//                    remainingToaRequest.setReferenceEntityType(toa.getReferenceEntityType());
//                    remainingToaRequest.setReferenceEntityId(toa.getReferenceEntityId());
//                    remainingToaRequest.setSubEntityReferenceType(toa.getSubEntityReferenceType());
//                    remainingToaRequest.setSubEntityReferenceIdList(subReferenceIds);
//
//                    RemainingToaResponse remainingToaResponse = findRemainingToa(remainingToaRequest, toa.isToaLimitOverride(), orderResponse);
//                    Map<String, RemainingToa> remainingToaMap = remainingToaResponse.getToaItems();
//                    RemainingToa remainingToa = remainingToaMap.get(toa.getSubEntityReferenceId());
//
//                    Double maximumAllowedToa = remainingToa.getRemainingToa();
//                 //   log.info("maximumAllowedToa {}", maximumAllowedToa);
//
//                    if (toa.getToaAmount() > maximumAllowedToa) {
//                        if (maximumAllowedToa < 0) {
//                            //to avoid messages with negative numbers
//                            maximumAllowedToa = 0.0;
//                        }
//                        if(maximumAllowedToa == 0){
//                            throw new BadRequestException(Constants.TOA_LIMIT_REACHED);
//                        }
//                        throw new BadRequestException(Constants.TOA_LIMIT_MESSAGE + Precision.round(maximumAllowedToa, 2));
//                    }
//
//                    toa.setStatus(Status.NEW);
//                    toa.setCreatedAt(new Date());
//
//                    if(toa.getToaMode().equals(ToaMode.EGV_IN_WALLET)){
//                        toa.setToaAmount(egvService.toaAmountToEGVDenomination(toa.getToaAmount()).doubleValue());
//                    }
//
//                    insertInToa(toa);
//
//                //    log.info("TOA entry created with id {}", toa.getId());
//
//                    //add toa history and create bigfoot entities and event
//                    toaHistoryService.addToToaHistory(toa, ChangedAttribute.STATUS, Event.CREATE, Status.NEW, toa.getUserLogin(), ChangeReason.CREATE_TOA, toa.getToaReason());
//
//                    //make entry in outbound message for payments call
////            if(!BYPASS_SC_PAYMENTS){
////            Integer outboundMessageId;
////            outboundMessageId = insertInOutboundMessage(toa);
////            return outboundMessageId != null;
////            }
////
////            else{
//                    if (toa.getToaMode().equals(ToaMode.WALLET_PROMOTION)) {
//                     //   log.info("TOA mode WALLET_PROMOTION");
////                        if(IS_WALLET_PROMOTION_ACTIVE){
////                            return walletService.addToaToWallet(toa);
////                        }
//                        throw new BadRequestException(Constants.TOA_MODE_INACTIVE_DESCRIPTION);
//
//                    } else if (toa.getToaMode().equals(ToaMode.EGV_IN_WALLET)) {
//                      //  log.info("TOA mode EGV_IN_WALLET");
//                        Integer transactionId = egvService.createTransaction(toa);
//
//                        if (transactionId == null) {
//                            return false;
//                        } else return true;
//                    }
////            }
//                } else
//                    throw new InvalidMerchantException(Constants.INVALID_MERCHANT_DESCRIPTION);
//            }
//
//        }
//        return false;
//    }
//
//    private boolean orderRelatedChecks(OrderResponse orderResponse, Toa toa) throws BadRequestException {
//        Order [] orders =  orderResponse.getOrders();
//        Order order = orders[0];
//        if(!toa.getExternalReferenceId().equals(order.getExternalId())){
//          //  log.info("Invalid external referenceId {} order exteranal id {}", toa.getExternalReferenceId(), order);
//            throw new BadRequestException(Constants.INVALID_EXTERNAL_REFERENCE_ID);
//        }
//
//        if(!order.getIdString().equals(toa.getReferenceEntityId())){
//          //  log.info("Sub entity {} doesn't belong to reference entity {}", toa.getSubEntityReferenceId(), toa.getReferenceEntityId());
//            throw new BadRequestException(Constants.ENTITY_SUB_ENTITY_MISMATCH);
//        }
//
//        if(!order.getBillToPartyId().equals(toa.getCustomerAccountId())){
//           // log.info("Order {} doesn't belong to customer {}", toa.getReferenceEntityId(), toa.getCustomerAccountId());
//            throw new BadRequestException(Constants.ENTITY_CUSTOMER_MISMATCH);
//        }
//
//        List<OrderItem> orderItemList = order.getOrderItems();
//        OrderItem orderItem;
//        ListIterator iterator = orderItemList.listIterator();
//        while(iterator.hasNext()){
//            orderItem = (OrderItem) iterator.next();
//            if(orderItem.getIdString().equals(toa.getSubEntityReferenceId())){
//                if(orderItem.getQuantity() != toa.getQuantity()){
//                   // log.info("Invalid quantity {}", toa.getQuantity());
//                    throw new BadRequestException(Constants.INVALID_QUANTITY);
//                }
//                if(!orderItem.getSellerId().equals(toa.getSellerId())){
//                   // log.info("Order {} doesn't belong to seller {}", toa.getReferenceEntityId(), toa.getSellerId());
//                    throw new BadRequestException(Constants.ENTITY_SELLER_MISMATCH);
//                }
//            }
//        }
//
//        return true;
//    }
//
//    private boolean basicChecks(Toa toa) throws BadRequestException {
//        if(toa.getQuantity() < 1){
//           // log.info("Invalid quantity {}", toa.getQuantity());
//            throw new BadRequestException(Constants.INVALID_QUANTITY);
//        }
//
//        if(toa.getSubEntityReferenceType().equals(SubEntityReferenceType.ORDER_ITEM_UNIT) && toa.getQuantity() != 1){
//           // log.info("Invalid quantity {}", toa.getQuantity());
//            throw new BadRequestException(Constants.INVALID_QUANTITY);
//        }
//
//        if(toa.getToaAmount() <= 0){
//           // log.info("Invalid amount {}", toa.getToaAmount());
//            throw new BadRequestException(Constants.INVALID_AMOUNT);
//        }
//
//        if(toa.getToaMode().equals(ToaMode.EGV_IN_WALLET)){
//            if(toa.getToaAmount() < MINIMUM_EGV_DENOMINATION){
//              //  log.info("Amount {} less than minimum EGV denomination {}", toa.getToaAmount(), MINIMUM_EGV_DENOMINATION);
//                throw new BadRequestException(Constants.AMOUNT_LESS_THAN_MINIMUM_EGV_DENOMINATION + " Rs. " + MINIMUM_EGV_DENOMINATION);
//            }
//        }
//
//        if(toa.getReferenceEntityId() == null){
//            //log.info("Invalid ReferenceEntityId {}", toa.getReferenceEntityId());
//            throw new BadRequestException(Constants.INVALID_INPUT);
//        }
//
//        if(toa.getToaReason() == null){
//          //  log.info("Invalid reason {}", toa.getToaReason());
//            throw new BadRequestException(Constants.INVALID_INPUT);
//        }
//
//        if(toa.getToaType() == null){
//           // log.info("Invalid toaType {}", toa.getToaType());
//            throw new BadRequestException(Constants.INVALID_INPUT);
//        }
//
//        if(toa.getUserLogin() == null){
//            //log.info("Invalid userLogin {}", toa.getUserLogin());
//            throw new BadRequestException(Constants.INVALID_INPUT);
//        }
//
//        if(toa.getCustomerAccountId() == null){
//            //log.info("Invalid customerAccountId {}", toa.getToaReason());
//            throw new BadRequestException(Constants.INVALID_INPUT);
//        }
//
//        if(toa.getSubEntityReferenceId() == null){
//           // log.info("Invalid SubEntityReferenceId {}", toa.getSubEntityReferenceId());
//            throw new BadRequestException(Constants.INVALID_INPUT);
//        }
//
//        if(toa.getExternalReferenceId() == null){
//           // log.info("Invalid ExternalReferenceId {}", toa.getExternalReferenceId());
//            throw new BadRequestException(Constants.INVALID_INPUT);
//        }
//
//        if(toa.getSellerId() == null){
//            //log.info("Invalid SellerId {}", toa.getSellerId());
//            throw new BadRequestException(Constants.INVALID_INPUT);
//        }
//
//        if(toa.getChannel() == null){
//           /// log.info("Invalid Channel {}", toa.getSellerId());
//            throw new BadRequestException(Constants.INVALID_INPUT);
//        }
//
//        return true;
//    }
//
//    private Integer getMerchantInfo(Toa toa) {
//        Merchant merchant = new Merchant();
//        merchant.setName(toa.getMerchantName());
//
//        merchant = merchantService.getMerchantDetails(merchant);
//        if(merchant == null){
//           // log.info("Merchant not found");
//            return 0;
//        }
//        else {
//            if(merchant.getStatus().equals(MerchantStatus.ACTIVE.ordinal())){
//              //  log.info("Active merchant found");
//                return merchant.getId();
//            }
//            else {
//              //  log.info("Merchant is not active");
//                return 0;
//            }
//        }
//    }
//
//    private Set<String> getUserRoles(String userLogin) {
//        try {
//            return CASCall.getCASResponse(new CASRequest(userLogin));
//
//        } catch (Exception e) {
//           // log.error("Exception {} {} {}", e, e.getMessage(), e.getStackTrace());
//            return null;
//        }
//    }
//
//    @Override
//    public Toa processCallback(PaymentCallback paymentCallback, Toa toa) {
//        //To distinguish between refund_id from sc-returns and toa-service
//        toa.setExternalPaymentId(paymentCallback.getPayment().getPaymentId());
//        toa.setPaymentReferenceNumber(paymentCallback.getPayment().getPaymentReferenceNumber());
//        toa.setStatus(Status.INITIATED);
//        toa.setInitDate(new Date());
//
//        //insert in toaHistory and bigfoot
//        toaHistoryService.updateToaStatusAndAddToTOAHistory(toa, ChangedAttribute.STATUS, Event.INITIATE, Status.INITIATED, toa.getUserLogin(), ChangeReason.INITIATED_BY_PAYMENTS, null);
//
//        return toa;
//    }
//
//    @Override
//    public List<Toa> getToaByReference(String reference) {
//        return toaDAO.getToaByReference(reference);
//    }
//
//    @Override
//    public void actionOnPaymentFailure(Toa toa, PaymentCallback paymentCallback, String destinationResponseStatus, String messageId) {
//        //update outbound message destinationResponseStatus and error messages
//      //  log.info("Message with id {} failed to process by Payments.", messageId);
//        OutboundMessage outboundMessage = new OutboundMessage();
//        outboundMessage.setMessageId(messageId);
//        outboundMessage.setRelayed(Constants.RELAYED);
//      //  log.debug("destinationResponseStatus {}", destinationResponseStatus);
//        outboundMessage.setDestinationResponseStatus(Integer.parseInt(destinationResponseStatus));
//        outboundMessage.setUpdatedAt(new Date());
//        outboundMessageService.updateOutboundMessage(outboundMessage);
//
//        //insert in toaHistory and bigfoot
//        toaHistoryService.updateToaStatusAndAddToTOAHistory(toa, ChangedAttribute.STATUS, Event.INITIATE, Status.FAILED, toa.getUserLogin(), ChangeReason.FAILED_AT_PAYMENTS, null);
//
//    }
//
//    @Override
//    public Double findPreviousToaSum(Toa toa) {
//        return toaDAO.findPreviousToaSum(toa);
//    }
//
//    @Override
//    public List<Toa> getToaByEntity(Toa toa) {
//        List<Toa> toaList = toaDAO.getToaByEntity(toa);
//        List<Transaction> transactionList = transactionService.getTransactionsForToa(toa);
//
//        List<Toa> toaTransactionList = mapTransactionsToToa(toaList, transactionList);
//        return  toaTransactionList;
//    }
//
//    private List<Toa> mapTransactionsToToa(List<Toa> toaList, List<Transaction> transactionList) {
//        if(toaList != null && toaList.size() > 0){
//        ListIterator toaListIterator = toaList.listIterator();
//        List<Transaction> tempTransactionList;
//        while(toaListIterator.hasNext()){
//            Toa toa = (Toa) toaListIterator.next();
//
//            tempTransactionList = new ArrayList<>();
//        //   log.debug("toa id {}", toa.getId());
//            ListIterator transactionListIterator = transactionList.listIterator();
//            while (transactionListIterator.hasNext()){
//                Transaction transaction = (Transaction) transactionListIterator.next();
//                if(transaction.getToaId().equals(toa.getId())){
//                    tempTransactionList.add(transaction);
//            //        log.debug("transaction list size {}", tempTransactionList.size());
//                }
//            }
//            if(tempTransactionList.size() > 0) {
//                toa.setTransactionList(tempTransactionList);
//            }
//        }
//
//        return toaList;
//        }
//        return null;
//    }
//
//    private Integer insertInOutboundMessage(Toa toa){
//        String messageId = UUID.randomUUID().toString();
//       // log.info("messageID {}, length {}", messageId, messageId.length());
//
//        String transactionId = UUID.randomUUID().toString();
//        //log.info("transactionId {}, length {}", transactionId, transactionId.length());
//
//        String correlationId = UUID.randomUUID().toString();
//       // log.info("correlationId {} length {}", correlationId, correlationId.length());
//
//        PaymentRequest paymentRequest = new PaymentRequest();
//        paymentRequest.setPaymentType(toa.getToaType());
//        paymentRequest.setPaymentMethod(toa.getToaMode().toString());
//        paymentRequest.setReason(toa.getToaReason());
//
//        //externalReferenceId is Flipkart order id "ODxxxx" for now we are accepting that.
//        //In case of TOA not associated with order it will be ToaReferenceId
//        if(toa.getExternalReferenceId() != null) {
//            paymentRequest.setFlipkartRefId(toa.getExternalReferenceId());
//        }
//        else {
//            paymentRequest.setFlipkartRefId(toa.getToaReferenceId());
//        }
//
//        paymentRequest.setComments(toa.getComment());
//
//        Map<String, Double> orderItemAmountsMap = new HashMap<>();
//        orderItemAmountsMap.put(toa.getReferenceEntityId(), toa.getToaAmount());
//        paymentRequest.setOrderItemAmounts(orderItemAmountsMap);
//
//        String primaryAccountId = walletService.fetchAccountId(toa.getCustomerAccountId());
//
//        if(primaryAccountId == null){
//            return null;
//        }
//        paymentRequest.setPartyIdTo(primaryAccountId);
//
//        paymentRequest.setPartyIdFrom(toa.getSellerId());
//        paymentRequest.setOrderId(toa.getReferenceEntityId());
//
//        Map<String, Integer> priceDropRefundQuantityMap = new HashMap<>();
//        priceDropRefundQuantityMap.put(toa.getReferenceEntityId(), 1);
//        paymentRequest.setPriceDropRefundQuantity(priceDropRefundQuantityMap);
//        paymentRequest.setPromisedAmount(toa.getToaAmount());
//        paymentRequest.setPaidAmount(toa.getToaAmount());
//
//        String customHeaders = "{\"X_SELLER_ID\":\""+ toa.getSellerId() + "\",\"X_TENANT_ID\":\""+ toa.getTenantId() +"\", \"X_RESTBUS_CALLBACK_ERROR_CODES\": [{\"from\": 200, \"to\": 299}, {\"from\": 400, \"to\": 499}, {\"from\": 509, \"to\": 509}]}";
//
//        //For distinguishing between autoInc ids from TOA-service and sc-returns
//        paymentRequest.setRefundId(Constants.TOA_PREFIX + toa.getId());
//
//        paymentRequest.setUserLogin(toa.getUserLogin());
//        paymentRequest.setSellerId(toa.getSellerId());
////        paymentRequest.setPaymentDate(DateUtil.formatDate(new Date(), Constants.BIGFOOT_DATE_FORMAT));
//        paymentRequest.setPaymentDate(new Date());
//
//        return  outboundMessageService.createAndInsertInOutboundMessage(paymentRequest, PAYMENT_URL, HttpMethod.POST.toString(), HttpMethod.POST.toString(), PAYMENT_CALLBACK_URL, customHeaders, Constants.PAYMENT_MESSAGE_GROUP_ID, messageId, transactionId, correlationId);
//
//    }
//
//    @Override
//    public boolean markCompletionOfTOA(Toa toa){
//        //add to toa_history
//        toaHistoryService.updateToaStatusAndAddToTOAHistory(toa, ChangedAttribute.STATUS, Event.COMPLETE, Status.COMPLETED, Constants.USER_LOGIN_SYSTEM, ChangeReason.AUTO_COMPLETED, null);
//
//        //send event to reachOut api
//        toa.setStatus(Status.COMPLETED);
//        toa.setUpdatedAt(new Date());
//
//        eventService.pushEventToReachOut(toa);
//        return true;
//    }
//
//    @Override
//    public RemainingToaResponse findRemainingToa(RemainingToaRequest remainingToaRequest, Boolean toaOverride, OrderResponse orderResponse) throws BadRequestException{
//        if(remainingToaRequest.getReferenceEntityType().equals(EntityReferenceType.ORDER)){
//            Map<String , RemainingToa> remainingToaMap = new HashMap<>();
//
//            Toa toa = new Toa();
//            toa.setReferenceEntityType(EntityReferenceType.ORDER);
//            toa.setReferenceEntityId(remainingToaRequest.getReferenceEntityId());
//
//            //issued toa from db
//            Double previousToaAmount;
//            RemainingToa remainingToa;
//            for (String subReferenceEntityId : remainingToaRequest.getSubEntityReferenceIdList()) {
//                toa.setSubEntityReferenceId(subReferenceEntityId);
//                toa.setSubEntityReferenceType(remainingToaRequest.getSubEntityReferenceType());
//                previousToaAmount = findPreviousToaSum(toa);
//
//          //      log.info("previousToaAmount {}", previousToaAmount);
//                if(previousToaAmount == null){
//                    previousToaAmount = 0.0;
//                }
//
//                remainingToa = new RemainingToa();
//                remainingToa.setIssuedToa(previousToaAmount);
//
//                remainingToaMap.put(subReferenceEntityId, remainingToa);
//            }
//
//            Double sellingPriceAndAdjustments;
//            Double remainingToaAmount;
//            Double adjustments;
//            int countOfSubReferenceEntities = remainingToaRequest.getSubEntityReferenceIdList().size();
//
//            Double maxToaPercentage;
//            Double allowedToaAmountAfterOverride;
//            if (toaOverride) {
//          //      log.info("toa override with percentage {} and ALLOWED_TOA_AMOUNT_AFTER_OVERRIDE {}", TOA_OVERRIDE_PERCENTAGE, ALLOWED_TOA_AMOUNT_AFTER_OVERRIDE);
//                maxToaPercentage = TOA_OVERRIDE_PERCENTAGE;
//                allowedToaAmountAfterOverride = ALLOWED_TOA_AMOUNT_AFTER_OVERRIDE;
//
//            } else {
//           //     log.info("toa not override with percentage {}", TOA_PERCENTAGE);
//                maxToaPercentage = TOA_PERCENTAGE;
//                allowedToaAmountAfterOverride = 0.0;
//            }
//
//           // log.info("toa percentage {}", maxToaPercentage);
//
//
//            for(Order order : orderResponse.getOrders()){
//                for(OrderItem orderItem : order.getOrderItems()){
//                    adjustments = 0.0;
//                    for(OrderItemAdjustment orderItemAdjustment : orderItem.getOrderItemAdjustments()){
//                        adjustments += orderItemAdjustment.getAmount();
//                    }
//
//                    sellingPriceAndAdjustments = (orderItem.getSellingPrice() + adjustments) * orderItem.getQuantity();
//
//             //       log.info("for order item {}", orderItem.getIdString());
//                    if(remainingToaMap.containsKey(orderItem.getIdString())){
//                        remainingToa = remainingToaMap.get(orderItem.getIdString());
//                        remainingToaAmount = (sellingPriceAndAdjustments * maxToaPercentage)/100 - remainingToa.getIssuedToa() + allowedToaAmountAfterOverride;
//
//                        if(remainingToaAmount < 0.0){
//                            remainingToa.setRemainingToa(0.0);
//                        }
//                        else{
//                            remainingToa.setRemainingToa(remainingToaAmount);
//                        }
//
//                        remainingToaMap.put(orderItem.getIdString(), remainingToa);
//                        countOfSubReferenceEntities--;
//                    }
//                    else {
//                        continue;
//                    }
//
//                }
//
//            }
//
//            if(countOfSubReferenceEntities != 0){
//          //      log.info("Sub entity {} doesn't belong to reference entity {}", toa.getSubEntityReferenceId(), toa.getReferenceEntityId());
//                throw new BadRequestException(Constants.ENTITY_SUB_ENTITY_MISMATCH);
//            }
//
//            RemainingToaResponse remainingToaResponse = new RemainingToaResponse();
//            remainingToaResponse.setToaItems(remainingToaMap);
//            remainingToaResponse.setToaPercentage(TOA_PERCENTAGE);
//            return remainingToaResponse;
//
//        }
//        return null;
//    }
//
//    @Override
//    public boolean isUniqueCustomerIdAndCustomerRequestId(Toa toa) {
//        List<Toa> toaList = toaDAO.getToaByCustomerAccountIdAndCustomerRequestId(toa);
//
//        if(toaList != null && toaList.size() > 0){
//            return false;
//        }
//        return true;
//    }
//}
