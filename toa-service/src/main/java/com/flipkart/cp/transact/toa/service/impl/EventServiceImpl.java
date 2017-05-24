//package com.flipkart.cp.transact.toa.service.impl;
//
//import com.fasterxml.jackson.databind.ObjectWriter;
//import com.flipkart.cp.transact.toa.domain.entities.models.client.Toa;
//import com.flipkart.cp.transact.toa.domain.entities.models.common.OutboundMessage;
//import com.flipkart.cp.transact.toa.domain.entities.models.reachout.ReachOutEventData;
//import com.flipkart.cp.transact.toa.domain.entities.models.reachout.ReachOutRequest;
//import com.flipkart.cp.transact.toa.domain.entities.models.reachout.ReachOutResponse;
//import com.flipkart.cp.transact.toa.domain.enums.HttpMethod;
//import com.flipkart.cp.transact.toa.service.api.EventService;
//import com.flipkart.cp.transact.toa.service.api.OutboundMessageService;
//import com.flipkart.cp.transact.toa.util.common.Constants;
//import com.flipkart.cp.transact.toa.util.common.CosmosUtil;
//import com.flipkart.cp.transact.toa.util.common.JsonUtils;
//import com.flipkart.cp.transact.toa.util.common.StringUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.inject.Inject;
//import javax.inject.Named;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Properties;
//import java.util.UUID;
//
///**
// * Created by padmanabh.kulkarni on 17/12/15.
// */
//@Named
//public class EventServiceImpl implements EventService {
////    private static final Logger log = LoggerFactory.getLogger(EventServiceImpl.class);
////    private static final Logger syslog = LoggerFactory.getLogger("sysLogger");
//
//    private static String REACH_OUT_EVENT_URL;
//    private static String REACH_OUT_EVENT_CALLBACK_URI;
//
//
//    @Inject
//    private OutboundMessageService outboundMessageService;
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
//                REACH_OUT_EVENT_URL = toaProperties.getProperty("REACH_OUT_EVENT_URL");
//                REACH_OUT_EVENT_CALLBACK_URI = toaProperties.getProperty("REACH_OUT_EVENT_CALLBACK_URI");
//
////                log.info("Static block - setTOAProperties -  REACH_OUT_EVENT_URL {}, REACH_OUT_EVENT_CALLBACK_URI {}",
////                        REACH_OUT_EVENT_URL, REACH_OUT_EVENT_CALLBACK_URI);
//            } else {
//               // log.error("Static block - setTOAProperties - error in setting TOA properties");
//               // syslog.info(CosmosUtil.cosmosCustomMetricString("property.loading.failure", 1));
//            }
//
//        } catch (Exception e) {
//            //log.error("Static block - Error in setting TOA properties {} {} {}", e, e.getMessage(), e.getStackTrace());
//          //  syslog.info(CosmosUtil.cosmosCustomMetricString("property.loading.failure", 1));
//        }
//    }
//
//
//    @Override
//    public void pushEventToReachOut(Toa toa) {
//
//        ReachOutRequest reachOutRequest = new ReachOutRequest();
//
//        reachOutRequest.setRequestId(StringUtil.randomAlphanumeric(32));
//        reachOutRequest.setEventName(Constants.TOA_REACH_OUT_EVENT_NAME);
//        reachOutRequest.setEventTime(toa.getUpdatedAt().getTime());
//        reachOutRequest.setEventCount(1);
//
//        if(toa != null){
//            ObjectWriter ow = JsonUtils.getObjectMapper().writer();
//            try {
//                ReachOutEventData reachOutEventData = new ReachOutEventData(toa);
//                String eventData = ow.writeValueAsString(reachOutEventData);
//                List<String> eventDataList = new ArrayList<>();
//                eventDataList.add(eventData);
//                reachOutRequest.setEventData(eventDataList);
//
//            } catch (IOException e) {
//              //  log.error("Exception {} {}", e, e.getMessage());
//              //  syslog.info(CosmosUtil.cosmosCustomMetricString("event.reach.out.failure", 1));
//                return;
//            }
//        }
//        else {
//            //log.error("Toa object is null. Can not trigger event to reach-out returning.");
//           // syslog.info(CosmosUtil.cosmosCustomMetricString("event.reach.out.failure", 1));
//        }
//
//        String messageId = UUID.randomUUID().toString();
//        String transactionId = UUID.randomUUID().toString();
//        String correlationId = UUID.randomUUID().toString();
//
//        String customHeaders = "{\"X_CLIENT_ID\":\"TOA_SERVICE\"}";
//
//        String groupId = UUID.randomUUID().toString(); //Constants.REACH_OUT_EVENT_GROUP_ID
//
//        outboundMessageService.createAndInsertInOutboundMessage(reachOutRequest, REACH_OUT_EVENT_URL, HttpMethod.POST.toString(),
//                HttpMethod.POST.toString(), REACH_OUT_EVENT_CALLBACK_URI, customHeaders, groupId, messageId, transactionId, correlationId);
//    }
//
//    @Override
//    public Integer retryReachOutEvent(String messageId, String responseStatus, ReachOutResponse reachOutResponse) {
//        OutboundMessage outboundMessage = outboundMessageService.getOutboundMessageByMessageId(messageId);
//        outboundMessage.setRelayed(null);
//        outboundMessage.setDestinationResponseStatus(Integer.parseInt(responseStatus));
//        outboundMessage.setRelayError(reachOutResponse.getErrorCode().toString());
//
//        return outboundMessageService.updateOutboundMessage(outboundMessage);
//    }
//
//    @Override
//    public Integer reachOutEventSuccess(String messageId, ReachOutResponse reachOutResponse) {
//        return 1;
//    }
//}
