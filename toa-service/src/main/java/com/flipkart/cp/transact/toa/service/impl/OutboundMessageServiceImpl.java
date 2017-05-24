//package com.flipkart.cp.transact.toa.service.impl;
//
//import com.fasterxml.jackson.databind.ObjectWriter;
//import com.flipkart.cp.transact.toa.dao.OutboundMessageDAO;
//import com.flipkart.cp.transact.toa.domain.entities.models.common.OutboundMessage;
//import com.flipkart.cp.transact.toa.domain.enums.HttpMethod;
//import com.flipkart.cp.transact.toa.service.api.OutboundMessageService;
//import com.flipkart.cp.transact.toa.util.common.Constants;
//import com.flipkart.cp.transact.toa.util.common.CosmosUtil;
//import com.flipkart.cp.transact.toa.util.common.JsonUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.inject.Inject;
//import javax.inject.Named;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.Date;
//import java.util.List;
//import java.util.Properties;
//
///**
// * Created by padmanabh.kulkarni on 11/06/15.
// */
//@Named
//public class OutboundMessageServiceImpl implements OutboundMessageService {
//
////    private static final Logger log = LoggerFactory.getLogger(OutboundMessageServiceImpl.class);
////    private static final Logger syslog = LoggerFactory.getLogger("sysLogger");
//
//    private static String APP_ID;
//    private static String EXCHANGE_NAME;
//    private static String CALLBACK_QUEUE_NAME;
//
//    @Inject
//    private OutboundMessageDAO outboundMessageDAO;
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
//
//                APP_ID = toaProperties.getProperty("APP_ID");
//                EXCHANGE_NAME = toaProperties.getProperty("EXCHANGE_NAME");
//                CALLBACK_QUEUE_NAME = toaProperties.getProperty("CALLBACK_QUEUE_NAME");
//
//              //  log.info("Static block - setTOAProperties -  APP_ID {}, EXCHANGE_NAME {}", APP_ID, EXCHANGE_NAME, CALLBACK_QUEUE_NAME);
//
//
//            } else {
//                //log.error("Static block - setTOAProperties - error in setting TOA properties");
//               // syslog.info(CosmosUtil.cosmosCustomMetricString("property.loading.failure", 1));
//            }
//
//        } catch (Exception e) {
//           // log.error("Static block - Error in setting TOA properties {} {} {}", e, e.getMessage(), e.getStackTrace());
//           // syslog.info(CosmosUtil.cosmosCustomMetricString("property.loading.failure", 1));
//        }
//    }
//
//
//    @Override
//    public Integer insertInOutboundMessage(OutboundMessage outboundMessage) {
//        return outboundMessageDAO.insertInOutboundMessage(outboundMessage);
//    }
//
//    @Override
//    public Integer createAndInsertInOutboundMessage(Object entity, String httpUri, String httpMethod, String replyToHttpMethod, String callbackURI, String customHeaders, String groupId, String messageId, String transactionId, String correlationId) {
//        OutboundMessage outboundMessage = new OutboundMessage();
//
//        outboundMessage.setMessageId(messageId);
//
////        outboundMessage.setRelayed(Constants.NOT_RELAYED);
//        outboundMessage.setExchangeName(EXCHANGE_NAME);
//        outboundMessage.setExchangeType(Constants.OUTBOUND_MESSAGE_EXCHANGE_TYPE_QUEUE);
//        outboundMessage.setAppId(APP_ID);
//
//        if(entity != null){
//            ObjectWriter ow = JsonUtils.getObjectMapper().writer();
//            try {
//                String entityJSON = ow.writeValueAsString(entity);
//                outboundMessage.setMessage(entityJSON);
//
//            } catch (IOException e) {
//             //   log.error("Exception {} {} {}", e, e.getMessage(), e.getStackTrace());
//            }
//        }
//
//        outboundMessage.setHttpMethod(httpMethod);
//        outboundMessage.setHttpUri(httpUri);
//
//        outboundMessage.setReplyTo(CALLBACK_QUEUE_NAME);
//        outboundMessage.setReplyToHttpMethod(HttpMethod.POST.toString());
//        outboundMessage.setReplyToHttpUri(callbackURI);
//
//        outboundMessage.setTxnId(transactionId);
//        outboundMessage.setCorrelationId(correlationId);
//
//        outboundMessage.setRetries(Constants.OUTBOUND_MESSAGE_RETRIES);
//
//        outboundMessage.setCustomHeaders(customHeaders);
//
////        outboundMessage.setRoutingKey(EXCHANGE_NAME);
//
//        outboundMessage.setCreatedAt(new Date());
//        outboundMessage.setGroupId(groupId);
//
//        return insertInOutboundMessage(outboundMessage);
//
//    }
//
//    @Override
//    public Integer updateOutboundMessage(OutboundMessage outboundMessage) {
//        return outboundMessageDAO.updateOutboundMessage(outboundMessage);
//    }
//
//    @Override
//    public List<OutboundMessage> getOutboundMessages(OutboundMessage outboundMessage) {
//        return outboundMessageDAO.getOutboundMessages(outboundMessage);
//    }
//
//    @Override
//    public OutboundMessage getOutboundMessageByMessageId(String messageId) {
//        return outboundMessageDAO.getOutboundMessageByMessageId(messageId);
//    }
//}
