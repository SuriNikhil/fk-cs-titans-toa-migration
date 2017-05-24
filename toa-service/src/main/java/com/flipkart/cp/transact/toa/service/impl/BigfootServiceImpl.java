//package com.flipkart.cp.transact.toa.service.impl;
//
//import com.flipkart.cp.transact.toa.domain.entities.models.bigfoot.*;
//import com.flipkart.cp.transact.toa.domain.entities.models.client.Toa;
//import com.flipkart.cp.transact.toa.domain.entities.models.common.Merchant;
//import com.flipkart.cp.transact.toa.domain.entities.models.common.OutboundMessage;
//import com.flipkart.cp.transact.toa.domain.entities.models.toa.TOAHistory;
//import com.flipkart.cp.transact.toa.domain.enums.HttpMethod;
//import com.flipkart.cp.transact.toa.service.api.BigfootService;
//import com.flipkart.cp.transact.toa.service.api.MerchantService;
//import com.flipkart.cp.transact.toa.service.api.OutboundMessageService;
//import com.flipkart.cp.transact.toa.util.common.Constants;
//import com.flipkart.cp.transact.toa.util.common.CosmosUtil;
//import com.flipkart.cp.transact.toa.util.common.DateUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.inject.Inject;
//import javax.inject.Named;
//import java.io.File;
//import java.io.FileInputStream;
//import java.util.Date;
//import java.util.Properties;
//import java.util.TimeZone;
//import java.util.UUID;
//
///**
// * Created by padmanabh.kulkarni on 21/06/15.
// */
//@Named
//public class BigfootServiceImpl implements BigfootService {
//   // private static final Logger log = LoggerFactory.getLogger(BigfootServiceImpl.class);
//  //  private static final Logger syslog = LoggerFactory.getLogger("sysLogger");
//
//    private static String BIGFOOT_CALLBACK_URL;
//    private static String BIGFOOT_ENTITY_URL;
//    private static String BIGFOOT_EVENT_URL;
//    private static String BIGFOOT_COMPANY;
//    private static String BIGFOOT_ORG;
//    private static String BIGFOOT_NAMESPACE;
//    private static String BIGFOOT_TOA_ENTITY;
//    private static String BIGFOOT_TOA_EVENT;
//    private static String BIGFOOT_TOA_ITEM_ENTITY;
//
//    @Inject
//    private OutboundMessageService outboundMessageService;
//
//    @Inject
//    private MerchantService merchantService;
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
//                BIGFOOT_CALLBACK_URL = toaProperties.getProperty("BIGFOOT_CALLBACK_URL");
//                BIGFOOT_ENTITY_URL = toaProperties.getProperty("BIGFOOT_ENTITY_URL");
//                BIGFOOT_EVENT_URL = toaProperties.getProperty("BIGFOOT_EVENT_URL");
//                BIGFOOT_COMPANY = toaProperties.getProperty("BIGFOOT_COMPANY");
//                BIGFOOT_ORG = toaProperties.getProperty("BIGFOOT_ORG");
//                BIGFOOT_NAMESPACE = toaProperties.getProperty("BIGFOOT_NAMESPACE");
//                BIGFOOT_TOA_ENTITY = toaProperties.getProperty("BIGFOOT_TOA_ENTITY");
//                BIGFOOT_TOA_ITEM_ENTITY = toaProperties.getProperty("BIGFOOT_TOA_ITEM_ENTITY");
//                BIGFOOT_TOA_EVENT = toaProperties.getProperty("BIGFOOT_TOA_EVENT");
//
//
////                log.info("Static block - setTOAProperties -   BIGFOOT_CALLBACK_URL {}",   BIGFOOT_CALLBACK_URL);
////                log.info("BIGFOOT_ENTITY_URL {}, BIGFOOT_EVENT_URL {}, COMPANY {}, ORG {}, NAMESPACE {}," +
////                                " BIGFOOT_TOA_ENTITY {}, BIGFOOT_TOA_ITEM_ENTITY {}, BIGFOOT_TOA_EVENT {}", BIGFOOT_ENTITY_URL,
////                        BIGFOOT_EVENT_URL, BIGFOOT_COMPANY, BIGFOOT_ORG, BIGFOOT_NAMESPACE, BIGFOOT_TOA_ENTITY,
////                        BIGFOOT_TOA_ITEM_ENTITY, BIGFOOT_TOA_EVENT);
//
//            } else {
////                log.error("Static block - setTOAProperties - error in setting TOA properties");
////                syslog.info(CosmosUtil.cosmosCustomMetricString("property.loading.failure", 1));
//            }
//
//        } catch (Exception e) {
////            log.error("Static block - Error in setting TOA properties {} {}", e, e.getMessage());
////            syslog.info(CosmosUtil.cosmosCustomMetricString("property.loading.failure", 1));
//        }
//    }
//
//
//    @Override
//    public Integer addToOutboundMessage(Toa toa, TOAHistory toaHistory) {
//        ///log.info("adding bigfoot entities to outbound message");
//
//        addBigfootToaEntity(toa, toaHistory);
//
//        return addBigfootToaEvent(toaHistory);
//    }
//
//    @Override
//    public void bigfootActionOnSuccess(String messageId) {
//       // log.info("Message with id {} got successfully processed by Bigfoot", messageId);
//        return;
//
//    }
//
//    @Override
//    public void bigfootActionOnFailure(BigfootResponse bigfootResponse, String messageId, Integer destinationResponseStatus) {
//        //mark as relayed=0 and retry again
//       //// log.info("Message with id {} failed to process by Bigfoot. Status {} Reason {} DetailedReason {}. Marking it for relay again", messageId, bigfootResponse.getStatus(), bigfootResponse.getReason(), bigfootResponse.getDetailedReason());
//        OutboundMessage outboundMessage = new OutboundMessage();
//        outboundMessage.setMessageId(messageId);
////        outboundMessage.setRelayed(Constants.NOT_RELAYED);
//        outboundMessage.setDestinationResponseStatus(destinationResponseStatus);
//        outboundMessage.setRelayError("Reason: " + bigfootResponse.getReason() + " Detailed Reason: " + bigfootResponse.getDetailedReason());
//        outboundMessage.setUpdatedAt(new Date());
//        outboundMessageService.updateOutboundMessage(outboundMessage);
//
//    }
//
//    private void addBigfootToaEntity(Toa toa, TOAHistory toaHistory) {
//        BigfootEntityRequest bigfootEntityRequest = null;
//
//        //TOA entity
//       // log.info("adding refund entity");
//        bigfootEntityRequest = createBigfootToaEntity(toa, toaHistory);
//       // log.debug("adding refund entity in outbound_messages");
//        addBigfootEntityToOutboundMessage(bigfootEntityRequest, BIGFOOT_TOA_ENTITY);
//    }
//
//    private Integer addBigfootEntityToOutboundMessage(BigfootEntityRequest bigfootEntityRequest, String bigfootEntity) {
//        if(bigfootEntityRequest != null) {
//            String httpUrl = BIGFOOT_ENTITY_URL + "/" + BIGFOOT_COMPANY + "/" + BIGFOOT_ORG + "/" + BIGFOOT_NAMESPACE + "/" + bigfootEntity;
//
//            String messageId = UUID.randomUUID().toString();
//         //   log.info("message id {}, length {}", messageId, messageId.length());
//
//            String transactionId = UUID.randomUUID().toString();
//          //  log.info("transaction id {}, length {}", transactionId, transactionId.length());
//
//            String correlationId = UUID.randomUUID().toString();
//          ///  log.info("correlationId {} length {}", correlationId, correlationId.length());
//
//            String groupId = UUID.randomUUID().toString();     //Constants.BIGFOOT_MESSAGE_GROUP_ID
//
//            return outboundMessageService.createAndInsertInOutboundMessage(bigfootEntityRequest, httpUrl, HttpMethod.POST.toString(),
//                    HttpMethod.POST.toString(), BIGFOOT_CALLBACK_URL, null, groupId, messageId, transactionId, correlationId);
//        }
//        return null;
//    }
//
//    private BigfootEntityRequest createBigfootToaEntity(Toa toa, TOAHistory toaHistory) {
//        BigfootToaEntity bigfootToaEntity = new BigfootToaEntity();
//
//        bigfootToaEntity.setId((long) toa.getId());
//        bigfootToaEntity.setToaReferenceId(toa.getToaReferenceId());
//        bigfootToaEntity.setReferenceEntityType(toa.getReferenceEntityType().name());
//        bigfootToaEntity.setReferenceEntityId(toa.getReferenceEntityId());
//        bigfootToaEntity.setSubEntityReferenceType(toa.getSubEntityReferenceType().name());
//        bigfootToaEntity.setSubEntityReferenceId(toa.getSubEntityReferenceId());
//        bigfootToaEntity.setExternalReferenceId(toa.getExternalReferenceId());
//        bigfootToaEntity.setAmount(toa.getToaAmount());
//        bigfootToaEntity.setCustomerAccountId(toa.getCustomerAccountId());
//        bigfootToaEntity.setCustomerRequestId(toa.getCustomerRequestId());
//        bigfootToaEntity.setChannel(toa.getChannel());
//        bigfootToaEntity.setComments(toa.getComment());
//        bigfootToaEntity.setStatus(toa.getStatus().toString());
//        bigfootToaEntity.setToaReason(toa.getToaReason());
//        bigfootToaEntity.setCreatedAt(DateUtil.formatDate(toa.getCreatedAt(), Constants.BIGFOOT_DATE_FORMAT, TimeZone.getTimeZone("UTC")));
//
//        if(toa.getUpdatedAt() == null){
//            toa.setUpdatedAt(new Date());
//        }
//        bigfootToaEntity.setUpdatedAt(DateUtil.formatDate(toa.getUpdatedAt(), Constants.BIGFOOT_DATE_FORMAT, TimeZone.getTimeZone("UTC")));
//
//        if(toa.getPromiseDate() != null) {
//            bigfootToaEntity.setPromiseDate(DateUtil.formatDate(toa.getPromiseDate(), Constants.BIGFOOT_DATE_FORMAT, TimeZone.getTimeZone("UTC")));
//        }
//        bigfootToaEntity.setSellerId(toa.getSellerId());
//        bigfootToaEntity.setTenantId(toa.getTenantId());
//
//        if(toa.getInitDate() != null) {
//            bigfootToaEntity.setInitDate(DateUtil.formatDate(toa.getInitDate(), Constants.BIGFOOT_DATE_FORMAT, TimeZone.getTimeZone("UTC")));
//        }
//
//        bigfootToaEntity.setExternalPaymentId(toa.getExternalPaymentId());
//        bigfootToaEntity.setPaymentRefNum(toa.getPaymentReferenceNumber());
//        bigfootToaEntity.setQuantity(toa.getQuantity());
//        bigfootToaEntity.setUserLogin(toa.getUserLogin());
//        bigfootToaEntity.setToaType(toa.getToaType());
//        bigfootToaEntity.setToaMode(toa.getToaMode().toString());
//
//        Merchant merchant = new Merchant();
//        merchant.setName(toa.getMerchantName());
//
//        if(merchant.getName() == null){
//            merchant.setId(toa.getMerchantId());
//            merchant = merchantService.getMerchantDetails(merchant);
//        }
//
//        bigfootToaEntity.setMerchantName(merchant.getName());
//
//        try {
//            bigfootToaEntity.validate();
//        } catch (Exception e) {
//       //     log.error("Exception {} {} {}", e, e.getMessage(), e.getStackTrace());
//        //    syslog.info(CosmosUtil.cosmosCustomMetricString("bigfoot.refund.entity.validation.failure", 1));
//        }
//
//        BigfootEntityRequest bigfootEntityRequest = new BigfootEntityRequest();
//        bigfootEntityRequest.setEntityId(bigfootToaEntity.getId().toString());
//        bigfootEntityRequest.setEntityVersion(toa.getId().toString());
//        bigfootEntityRequest.setUpdatedAt(DateUtil.formatDate(toa.getUpdatedAt(), Constants.BIGFOOT_DATE_FORMAT, TimeZone.getTimeZone("UTC")));
//        bigfootEntityRequest.setSchemaVersion(bigfootToaEntity.getSchemaVersion());
//        bigfootEntityRequest.setData(bigfootToaEntity);
//
//        return bigfootEntityRequest;
//    }
//
//    private Integer addBigfootToaEvent(TOAHistory toaHistory) {
//
//        BigfootEventRequest bigfootEventRequest = createBigfootToaEvent(toaHistory);
//
//        String httpUrl = BIGFOOT_EVENT_URL +  "/" + BIGFOOT_COMPANY + "/" + BIGFOOT_ORG + "/" + BIGFOOT_NAMESPACE + "/" + BIGFOOT_TOA_EVENT;
//
//        String messageId = UUID.randomUUID().toString();
//      //  log.info("random Code {}, length {}", messageId, messageId.length());
//
//        String transactionId = UUID.randomUUID().toString();
//      //  log.info("random Code {}, length {}", transactionId, transactionId.length());
//
//        String correlationId = UUID.randomUUID().toString();
//      //  log.info("correlationId {} length {}", correlationId, correlationId.length());
//
//        String groupId = UUID.randomUUID().toString(); //Constants.BIGFOOT_MESSAGE_GROUP_ID
//
//        return  outboundMessageService.createAndInsertInOutboundMessage(bigfootEventRequest, httpUrl, HttpMethod.POST.toString(), HttpMethod.POST.toString(), BIGFOOT_CALLBACK_URL, null, groupId, messageId, transactionId, correlationId);
//    }
//
//    private BigfootEventRequest createBigfootToaEvent(TOAHistory toaHistory) {
//        BigfootToaHistoryEvent bigfootToaHistoryEvent = new BigfootToaHistoryEvent();
//        bigfootToaHistoryEvent.setToaHistoryId((long) toaHistory.getId());
//        bigfootToaHistoryEvent.setToaId((long) toaHistory.getToaId());
//        bigfootToaHistoryEvent.setChangeData(toaHistory.getChangeData());
//        bigfootToaHistoryEvent.setChangeReason(toaHistory.getChangeReason().toString());
//        bigfootToaHistoryEvent.setChangeSubReason(toaHistory.getChangeSubReason());
//        bigfootToaHistoryEvent.setUserLogin(toaHistory.getUserLogin());
//        bigfootToaHistoryEvent.setNewStatus(toaHistory.getNewStatus().toString());
//        bigfootToaHistoryEvent.setEvent(toaHistory.getEvent().toString());
//
//        if(toaHistory.getChangeTime() == null){
//            toaHistory.setChangeTime(new Date());
//        }
//        bigfootToaHistoryEvent.setChangeTime(DateUtil.formatDate(toaHistory.getChangeTime(), Constants.BIGFOOT_DATE_FORMAT, TimeZone.getTimeZone("UTC")));
//
//        bigfootToaHistoryEvent.setChangedAttribute(toaHistory.getChangedAttribute().toString());
//
//        try {
//            bigfootToaHistoryEvent.validate();
//        } catch (Exception e) {
//            ////log.error("Exception {} {} {}", e, e.getMessage(), e.getStackTrace());
//           // syslog.info(CosmosUtil.cosmosCustomMetricString("bigfoot.refund.history.event.validation.failure", 1));
//        }
//
//        BigfootEventRequest bigfootEventRequest = new BigfootEventRequest();
//        bigfootEventRequest.setEventId(bigfootToaHistoryEvent.getToaHistoryId().toString());
//        bigfootEventRequest.setEventTime(bigfootToaHistoryEvent.getChangeTime());
//        bigfootEventRequest.setSchemaVersion(bigfootToaHistoryEvent.getSchemaVersion());
//        bigfootEventRequest.setData(bigfootToaHistoryEvent);
//        bigfootEventRequest.setSchemaVersion(bigfootToaHistoryEvent.getSchemaVersion());
//
//        return bigfootEventRequest;
//    }
//}
