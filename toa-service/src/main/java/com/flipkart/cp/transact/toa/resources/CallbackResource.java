//package com.flipkart.cp.transact.toa.api.common.resources;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.flipkart.caishen.api.enums.TransactionState;
//import com.flipkart.caishen.api.transaction.requests.BatchEgvLinkRequest;
//import com.flipkart.caishen.api.transaction.responses.BatchEgvCreditResponse;
//import com.flipkart.caishen.api.transaction.responses.CreditRefundResponse;
//import com.flipkart.cp.transact.toa.domain.entities.models.bigfoot.BigfootResponse;
//import com.flipkart.cp.transact.toa.domain.entities.models.client.StatusResponse;
//import com.flipkart.cp.transact.toa.domain.entities.models.client.Toa;
//import com.flipkart.cp.transact.toa.domain.entities.models.common.OutboundMessage;
//import com.flipkart.cp.transact.toa.domain.entities.models.common.Transaction;
//import com.flipkart.cp.transact.toa.domain.entities.models.egv.CreateEGVResponse;
//import com.flipkart.cp.transact.toa.domain.entities.models.egv.CreateTransactionResponse;
//import com.flipkart.cp.transact.toa.domain.entities.models.egv.EGV;
//import com.flipkart.cp.transact.toa.domain.entities.models.payment.PaymentCallback;
//import com.flipkart.cp.transact.toa.domain.entities.models.payment.PaymentRequest;
//import com.flipkart.cp.transact.toa.domain.entities.models.reachout.ReachOutResponse;
//import com.flipkart.cp.transact.toa.domain.enums.*;
//import com.flipkart.cp.transact.toa.metrics.Counter;
//import com.flipkart.cp.transact.toa.metrics.Meter;
//import com.flipkart.cp.transact.toa.metrics.Timer;
//import com.flipkart.cp.transact.toa.service.api.*;
//import com.flipkart.cp.transact.toa.util.common.Constants;
//import com.flipkart.cp.transact.toa.util.common.CosmosUtil;
//import com.flipkart.cp.transact.toa.util.common.JsonUtils;
//import org.apache.commons.httpclient.HttpStatus;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.TransactionDefinition;
//import org.springframework.transaction.support.DefaultTransactionDefinition;
//
//import javax.servlet.http.HttpServletResponse;
//import javax.validation.constraints.NotNull;
//import javax.ws.rs.*;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//
///**
// * Created by padmanabh.kulkarni on 29/05/15.
// */
//
//@Path("/callback")
//public class CallbackResource {
//    private static final Logger log = LoggerFactory.getLogger(CallbackResource.class);
//    private static final Logger syslog = LoggerFactory.getLogger("sysLogger");
//
//
//    private PlatformTransactionManager transactionManager;
//
//    @Autowired
//    private ToaService toaService;
//
//    @Autowired
//    private WalletService walletService;
//
//    @Autowired
//    private BigfootService bigfootService;
//
//    @Autowired
//    private OutboundMessageService outboundMessageService;
//
//    @Autowired
//    private EGVService egvService;
//
//    @Autowired
//    private ToaHistoryService toaHistoryService;
//
//    @Autowired
//    private EventService eventService;
//
//    @Autowired
//    private TransactionService transactionService;
//
//    @POST
//    @Timer("paymentCallback")
//    @Counter("paymentCallback")
//    @Meter("paymentCallback")
//    @Path("/payment")
//    @Consumes({MediaType.APPLICATION_JSON})
//    @Produces({MediaType.APPLICATION_JSON})
//    public Response paymentCallback(PaymentCallback paymentCallback, @HeaderParam("X_RESTBUS_MESSAGE_ID") String messageId, @HeaderParam("X_RESTBUS_DESTINATION_RESPONSE_STATUS") String responseStatus) {
//        try {
//            log.info("messageId {} responseStatus {}", messageId, responseStatus);
//
//            try {
//                log.info("Callback from Payment with payload {}", JsonUtils.getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(paymentCallback));
//                log.info("responseStatus {}", responseStatus);
//            } catch (JsonProcessingException e) {
//                log.error("Exception {} {} {}", e, e.getMessage(), e.getStackTrace());
//            }
//
//            if (responseStatus != null && messageId != null && !messageId.isEmpty() && paymentCallback != null) {
//                OutboundMessage outboundMessage = outboundMessageService.getOutboundMessageByMessageId(messageId);
//
//                if (outboundMessage != null) {
//                    PaymentRequest paymentRequest = JsonUtils.getObjectMapper().readValue(outboundMessage.getMessage(), PaymentRequest.class);
//
//                    try {
//                        log.info("Payment payload {}", JsonUtils.getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(paymentRequest));
//                    } catch (JsonProcessingException e) {
//                        log.error("Exception {} {} {}", e, e.getMessage(), e.getStackTrace());
//                    }
//
//                    Toa toa = toaService.getTOAById(paymentRequest.getRefundId().replace(Constants.TOA_PREFIX, ""));
//
//                    if (toa == null) {
//                        log.info("Toa id {} not found", paymentRequest.getRefundId().replace(Constants.TOA_PREFIX, ""));
//                        syslog.info(CosmosUtil.cosmosCustomMetricString("payment.callback.failure", 1));
//                        return Response.status(HttpServletResponse.SC_BAD_REQUEST).build();
//                    }
//
//                    if (!(toa.getStatus().equals(Status.NEW))) {
//                        log.info("Toa id {} status {} is not in status NEW", toa.getId(), toa.getStatus());
//                        syslog.info(CosmosUtil.cosmosCustomMetricString("payment.callback.failure", 1));
//                        return Response.status(HttpServletResponse.SC_BAD_REQUEST).build();
//                    }
//
//                    if (Integer.parseInt(responseStatus) == HttpServletResponse.SC_CREATED) {
//
//                        TransactionDefinition def = new DefaultTransactionDefinition();
//                        TransactionStatus dbStatus = transactionManager.getTransaction(def);
//
//                        try {
//                            toa = toaService.processCallback(paymentCallback, toa);
//                        } catch (Exception e) {
//                            log.error("Exception {} {} {}", e, e.getMessage(), e.getStackTrace());
//                            syslog.info(CosmosUtil.cosmosCustomMetricString("payment.callback.failure", 1));
//                            transactionManager.rollback(dbStatus);
//                            return Response.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).build();
//                        }
//                        transactionManager.commit(dbStatus);
//
//                        //complete toa
//                        if (toa.getToaMode().equals(ToaMode.WALLET_PROMOTION)) {
//                            log.info("TOA mode WALLET_PROMOTION");
//                            walletService.addToaToWallet(toa);
//                        } else if (toa.getToaMode().equals(ToaMode.EGV_IN_WALLET)) {
//                            log.info("TOA mode EGV_IN_WALLET");
//                            Integer transactionId = egvService.createTransaction(toa);
//
//                            if (transactionId == null) {
//                                return Response.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).build();
//                            }
//                        }
//                        syslog.info(CosmosUtil.cosmosCustomMetricString("payment.callback.success", 1));
//                        return Response.ok().build();
//                    } else {
//                        syslog.info(CosmosUtil.cosmosCustomMetricString("payment.failure", 1));
//                        toaService.actionOnPaymentFailure(toa, paymentCallback, responseStatus, messageId);
//                    }
//                }
//            }
//
//            return Response.status(HttpStatus.SC_BAD_REQUEST).build();
//        }catch(Exception e) {
//            log.error("Exception {} {} {}", e, e.getMessage(), e.getStackTrace());
//            return Response.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).entity(new StatusResponse(HttpStatus.SC_INTERNAL_SERVER_ERROR, Constants.SERVER_ERROR_DESCRIPTION)).build();
//        }
//    }
//
//    @Path("/wallet")
//    @Timer("walletCallback")
//    @Counter("walletCallback")
//    @Meter("walletCallback")
//    @POST
//    @Consumes({MediaType.APPLICATION_JSON})
//    @Produces({MediaType.APPLICATION_JSON})
//    public Response walletCallback(CreditRefundResponse creditRefundResponse, @HeaderParam("X_RESTBUS_MESSAGE_ID") String messageId, @HeaderParam("X_RESTBUS_DESTINATION_RESPONSE_STATUS") String responseStatus) {
//        try {
//            log.info("messageId {} responseStatus {}", messageId, responseStatus);
//
//            try {
//                log.info("Callback from Wallet with payload {}", JsonUtils.getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(creditRefundResponse));
//                log.info("responseStatus {}", responseStatus);
//            } catch (JsonProcessingException e) {
//                log.error("Exception {} {} {}", e, e.getMessage(), e.getStackTrace());
//            }
//
//            if (responseStatus != null && Integer.parseInt(responseStatus) == HttpServletResponse.SC_OK && messageId != null && !messageId.isEmpty() && creditRefundResponse != null) {
//                Toa toa = toaService.getTOAByToaReferenceId(creditRefundResponse.getCreditTransactionResponse().getMerchantTxnId());
//
//                if(toa.getStatus().equals(Status.NEW)) {
//                    if (!creditRefundResponse.getCreditTransactionResponse().getCreditState().equals(TransactionState.CANCELLED) ||
//                            !creditRefundResponse.getCreditTransactionResponse().getCreditState().equals(TransactionState.FAILED)) {
//
//                        walletService.actionOnSuccess(toa, creditRefundResponse.getCreditTransactionResponse().getCreditResponseCode(),
//                                creditRefundResponse.getCreditTransactionResponse().getCreditState());
//                    } else {
//                        syslog.info(CosmosUtil.cosmosCustomMetricString("wallet.credit.failure", 1));
//                        walletService.actionOnFailure(toa, creditRefundResponse.getCreditTransactionResponse().getCreditResponseCode(),
//                                creditRefundResponse.getCreditTransactionResponse().getCreditState());
//                    }
//                    return Response.status(HttpStatus.SC_OK).build();
//                }
//                else{
//                    log.debug("toa status {} not equal to NEW", toa.getStatus());
//                    return Response.status(HttpStatus.SC_CONFLICT).build();
//                }
//            }
//            syslog.info(CosmosUtil.cosmosCustomMetricString("wallet.callback.failure.bad.request", 1));
//            return Response.status(HttpStatus.SC_BAD_REQUEST).build();
//        }catch(Exception e) {
//            log.error("Exception {} {} {}", e, e.getMessage(), e.getStackTrace());
//            syslog.info(CosmosUtil.cosmosCustomMetricString("wallet.callback.failure", 1));
//            return Response.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).entity(new StatusResponse(HttpStatus.SC_INTERNAL_SERVER_ERROR, Constants.SERVER_ERROR_DESCRIPTION)).build();
//        }
//    }
//
//
//    @Path("/bigfoot")
//    @Timer("bigfootCallback")
//    @Counter("bigfootCallback")
//    @Meter("bigfootCallback")
//    @POST
//    @Consumes({MediaType.APPLICATION_JSON})
//    @Produces({MediaType.APPLICATION_JSON})
//    public Response bigfootCallback(BigfootResponse bigfootResponse, @HeaderParam("X_RESTBUS_MESSAGE_ID") String messageId, @HeaderParam("X_RESTBUS_DESTINATION_RESPONSE_STATUS") String responseStatus) {
//        try {
//            log.info("bigfoot callback");
//            if (responseStatus != null && Integer.parseInt(responseStatus) == HttpServletResponse.SC_OK && bigfootResponse.getStatus().equalsIgnoreCase(Constants.BIGFOOT_RESPONSE_SUCCESS)) {
//                bigfootService.bigfootActionOnSuccess(messageId);
//            } else {
//                Integer destinationResponseStatus = Integer.parseInt(responseStatus);
//                syslog.info(CosmosUtil.cosmosCustomMetricString("bigfoot.call.failure", 1));
//                bigfootService.bigfootActionOnFailure(bigfootResponse, messageId, destinationResponseStatus);
//            }
//            return Response.ok().build();
//        }catch(Exception e) {
//            log.error("Exception {} {} {}", e, e.getMessage(), e.getStackTrace());
//            syslog.info(CosmosUtil.cosmosCustomMetricString("bigfoot.callback.failure", 1));
//            return Response.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).entity(new StatusResponse(HttpStatus.SC_INTERNAL_SERVER_ERROR, Constants.SERVER_ERROR_DESCRIPTION)).build();
//        }
//    }
//
//    @Path("/create_transaction_callback")
//    @Timer("createTransactionCallback")
//    @Counter("createTransactionCallback")
//    @Meter("createTransactionCallback")
//    @POST
//    @Consumes({MediaType.APPLICATION_JSON})
//    @Produces({MediaType.APPLICATION_JSON})
//    public Response createTransactionCallback(@NotNull CreateTransactionResponse returnedTransaction, @NotNull @HeaderParam("X_RESTBUS_MESSAGE_ID")
//    String messageId, @HeaderParam("X_RESTBUS_DESTINATION_RESPONSE_STATUS") String responseStatus) {
//        try{
//            log.info("messageId {} responseStatus {}", messageId, responseStatus);
//
//            OutboundMessage outboundMessage = outboundMessageService.getOutboundMessageByMessageId(messageId);
//
//            if (Integer.parseInt(responseStatus) == (HttpServletResponse.SC_CREATED)) {
//                Transaction transaction = JsonUtils.getObjectMapper().readValue(outboundMessage.getMessage(), Transaction.class);
//                transaction.setCode(returnedTransaction.getTransactionId());
//
//                Transaction transactionFromDatabase =  egvService.getTransactionDetails(transaction);
//                transaction.setAmount(transactionFromDatabase.getAmount());
//                Toa toa = toaService.getTOAById(transaction.getToaId().toString());
//                if(toa.getStatus().equals(Status.NEW)) {
//                    transaction.setStatus(com.flipkart.cp.transact.toa.domain.enums.TransactionStatus.REQUESTED.getId());
//                    egvService.updateTransaction(transaction);
//
//                    if (transactionService.areAllTransactionsInStateOrAbove(transaction)){
//                        toa.setStatus(Status.REQUESTED);
//                    }
//                    toaService.updateTOA(toa);
//
//                    toaHistoryService.addToToaHistory(toa, ChangedAttribute.STATUS, Event.REQUEST, Status.REQUESTED, toa.getUserLogin(), ChangeReason.CREATED_EGV_TRANSACTION, null);
//                    transaction.setMerchantId(toa.getMerchantId());
//
//                    log.info("transactionCode {}", transaction.getCode());
//                    log.info("toaId {} toaReferenceId {}", toa.getId(), toa.getToaReferenceId());
//
//                    egvService.createEGV(toa, transaction);
//
//                    return Response.ok().build();
//                }
//                else{
//                    log.info("toa status {} not NEW", toa.getStatus());
//                    return Response.status(HttpStatus.SC_CONFLICT).build();
//                }
//            } else {
//                log.info("create transaction failed retrying");
//                syslog.info(CosmosUtil.cosmosCustomMetricString("create.transaction.failure", 1));
//                if(outboundMessage.getRetries() <= Constants.OUTBOUND_MESSAGE_RETRIES) {
//                    outboundMessage.setRelayed(null);
//                    outboundMessage.setRetries(outboundMessage.getRetries() + 1);
//                    outboundMessageService.updateOutboundMessage(outboundMessage);
//                }
//            }
//            return Response.ok().build();
//        }catch(Exception e) {
//            log.error("Exception {} {} {}", e, e.getMessage(), e.getStackTrace());
//            syslog.info(CosmosUtil.cosmosCustomMetricString("create.transaction.callback.failure", 1));
//            return Response.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).entity(new StatusResponse(HttpStatus.SC_INTERNAL_SERVER_ERROR, Constants.SERVER_ERROR_DESCRIPTION)).build();
//        }
//    }
//
//    @Path("/create_egv_callback")
//    @Timer("createEGVCallback")
//    @Counter("createEGVCallback")
//    @Meter("createEGVCallback")
//    @POST
//    @Consumes({MediaType.APPLICATION_JSON})
//    @Produces({MediaType.APPLICATION_JSON})
//    public Response createEGVCallback(@NotNull CreateEGVResponse createEGVResponse, @NotNull @HeaderParam("X_RESTBUS_MESSAGE_ID")
//    String messageId, @HeaderParam("X_RESTBUS_DESTINATION_RESPONSE_STATUS") String responseStatus) {
//        try{
//            log.info("messageId {} responseStatus {}", messageId, responseStatus);
//            OutboundMessage outboundMessage = outboundMessageService.getOutboundMessageByMessageId(messageId);
//
//            EGV egv = JsonUtils.getObjectMapper().readValue(outboundMessage.getMessage(), EGV.class);
//
//            log.debug("transaction Id {}", egv.getTransactionId());
//
//            Transaction transaction = new Transaction();
//            transaction.setCode(egv.getTransactionId());
//            transaction = egvService.getTransactionDetails(transaction);
//
//            if(transaction.getStatus().equals(com.flipkart.cp.transact.toa.domain.enums.TransactionStatus.FAILED)){
//                log.info("Got duplicate request for failed transaction with id {} toa Id {} code {}", transaction.getId(), transaction.getToaId(), transaction.getCode());
//                return Response.status(HttpStatus.SC_CONFLICT).entity(new StatusResponse(HttpStatus.SC_CONFLICT, Constants.TRANSACTION_STATE_CONFLICT)).build();
//            }
//
//            Toa toa = toaService.getTOAById(transaction.getToaId().toString());
//
//            if (Integer.parseInt(responseStatus) == (HttpServletResponse.SC_CREATED)) {
//                if(toa.getStatus().equals(Status.REQUESTED)) {
//
//                    TransactionDefinition def = new DefaultTransactionDefinition();
//                    TransactionStatus dbStatus = transactionManager.getTransaction(def);
//
//                    //update transaction
//                    transaction.setStatus(com.flipkart.cp.transact.toa.domain.enums.TransactionStatus.PROCESSED.getId());
//                    transaction.setCardNumber(createEGVResponse.getEgv().getCode());
//                    egvService.updateTransaction(transaction);
//
//                    if (transactionService.areAllTransactionsInStateOrAbove(transaction)){
//                        toa.setStatus(Status.INITIATED);
//                    }
//                    toaService.updateTOA(toa);
//
//                    toaHistoryService.addToToaHistory(toa, ChangedAttribute.STATUS, Event.INITIATE, Status.INITIATED, Constants.USER_LOGIN_SYSTEM,  ChangeReason.CREATED_EGV, null);
//
//                    log.info("adding egv to wallet txnId {}, toaId {}", transaction.getCode(), toa.getId());
//                    boolean walletRequestSubmitted = walletService.addEGVToWallet(toa, createEGVResponse.getEgv(), transaction);
//                    if (walletRequestSubmitted){
//                        transactionManager.commit(dbStatus);
//                        return Response.ok().build();
//                    }
//                    else {
//                        transactionManager.rollback(dbStatus);
//                        return Response.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).entity(new StatusResponse(HttpStatus.SC_INTERNAL_SERVER_ERROR, Constants.SERVER_ERROR_DESCRIPTION)).build();
//                    }
//                }
//                else{
//                    log.info("toa status {} not {}", toa.getStatus(), Status.REQUESTED);
//                    return Response.status(HttpStatus.SC_CONFLICT).build();
//                }
//            }
//            else if (Integer.parseInt(responseStatus) == (HttpServletResponse.SC_GATEWAY_TIMEOUT)){
//                //retry same transaction
//                log.info("Request timed out retrying");
//                if(egvService.retryEGVCreation(outboundMessage)) {
//                    return Response.ok().build();
//            } else {
//                    return Response.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).entity(new StatusResponse(HttpStatus.SC_INTERNAL_SERVER_ERROR, Constants.SERVER_ERROR_DESCRIPTION)).build();
//                }
//            }
//            else {
//                // update current transaction to failure state
//                log.info("createEGV failed. Making transaction {} as failed and creating new one", transaction.getCode());
//                syslog.info(CosmosUtil.cosmosCustomMetricString("create.egv.failure", 1));
//                transaction.setStatus(com.flipkart.cp.transact.toa.domain.enums.TransactionStatus.FAILED.getId());
//                egvService.updateTransaction(transaction);
//
//                toa.setStatus(Status.NEW);
//                toaService.updateTOA(toa);
//
//                //create new Transaction and egv
//                egvService.createNewTransactionForFailed(transaction);
//                return Response.ok().build();
//            }
//        }catch(Exception e) {
//            log.error("Exception {} {} {}", e, e.getMessage(), e.getStackTrace());
//            syslog.info(CosmosUtil.cosmosCustomMetricString("create.egv.callback.failure", 1));
//            return Response.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).entity(new StatusResponse(HttpStatus.SC_INTERNAL_SERVER_ERROR, Constants.SERVER_ERROR_DESCRIPTION)).build();
//        }
//    }
//
//    @Path("/add_egv_to_wallet_callback")
//    @Timer("addEGVToWalletCallback")
//    @Counter("addEGVToWalletCallback")
//    @Meter("addEGVToWalletCallback")
//    @POST
//    @Consumes({MediaType.APPLICATION_JSON})
//    @Produces({MediaType.APPLICATION_JSON})
//    public Response addEGVToWalletCallback(@NotNull BatchEgvCreditResponse batchEgvCreditResponse, @NotNull @HeaderParam("X_RESTBUS_MESSAGE_ID")
//    String messageId,@NotNull @HeaderParam("X_RESTBUS_DESTINATION_RESPONSE_STATUS") String responseStatus) {
//        try{
//            log.info("messageId {} responseStatus {}", messageId, responseStatus);
//
//            OutboundMessage outboundMessage = outboundMessageService.getOutboundMessageByMessageId(messageId);
//            BatchEgvLinkRequest batchEgvLinkRequest = JsonUtils.getObjectMapper().readValue(outboundMessage.getMessage(), BatchEgvLinkRequest.class);
//
//            if(Integer.parseInt(responseStatus) == (HttpServletResponse.SC_OK)
//                    && batchEgvCreditResponse.getCreditTransactionResponse().getCreditState().equals(TransactionState.SUCCESS)){
//                //success
//                 if(walletService.actionOnLinkSuccess(batchEgvLinkRequest)){
//                     return Response.ok().build();
//                 } else {
//                     return Response.status(HttpStatus.SC_CONFLICT).entity(new StatusResponse(HttpStatus.SC_CONFLICT, Constants.TRANSACTION_STATE_CONFLICT)).build();
//                 }
//            }
//            else {
//                //failure
//                walletService.retryLinkToWallet(batchEgvLinkRequest);
//                return Response.ok().build();
//            }
//        }catch(Exception e) {
//            log.error("Exception {} {} {}", e, e.getMessage(), e.getStackTrace());
//            syslog.info(CosmosUtil.cosmosCustomMetricString("add.to.wallet.callback.failure", 1));
//            return Response.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).entity(new StatusResponse(HttpStatus.SC_INTERNAL_SERVER_ERROR, Constants.SERVER_ERROR_DESCRIPTION)).build();
//        }
//    }
//
//    @Path("/reach_out_event_callback")
//    @Timer("reachOutEventCallback")
//    @Counter("reachOutEventCallback")
//    @Meter("reachOutEventCallback")
//    @POST
//    @Consumes({MediaType.APPLICATION_JSON})
//    @Produces({MediaType.APPLICATION_JSON})
//    public Response reachOutEventCallback(@NotNull ReachOutResponse reachOutResponse, @NotNull @HeaderParam("X_RESTBUS_MESSAGE_ID") String messageId, @NotNull @HeaderParam("X_RESTBUS_DESTINATION_RESPONSE_STATUS") String responseStatus){
//        try {
//            if(responseStatus.equals(HttpStatus.SC_OK)){
//                if(reachOutResponse.getErrorCode() == null) {
//                    log.info("ReachOutEvent success");
//                    syslog.info(CosmosUtil.cosmosCustomMetricString("reach.out.event.success", 1));
//                    eventService.reachOutEventSuccess(messageId, reachOutResponse);
//                }
//            }
//            else {
//                //retry
//                syslog.info(CosmosUtil.cosmosCustomMetricString("reach.out.event.failure", 1));
//                eventService.retryReachOutEvent(messageId, responseStatus, reachOutResponse);
//            }
//            return Response.ok().build();
//        }
//        catch (Exception e){
//            log.error("Exception {} {} {}", e, e.getMessage(), e.getStackTrace());
//            syslog.info(CosmosUtil.cosmosCustomMetricString("reach.out.event.callback.failure", 1));
//            return Response.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).entity(new StatusResponse(HttpStatus.SC_INTERNAL_SERVER_ERROR, Constants.SERVER_ERROR_DESCRIPTION)).build();
//        }
//    }
//
//}
//
