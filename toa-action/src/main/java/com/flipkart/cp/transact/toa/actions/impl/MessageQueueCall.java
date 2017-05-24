package com.flipkart.cp.transact.toa.actions.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.flipkart.cp.convert.simple.proxy.commands.HttpMethod;
import com.flipkart.cp.convert.simple.proxy.request.HttpRequestWrapper;
import com.flipkart.cp.transact.toa.actions.models.request.MessageQueueRequest;
import com.flipkart.cp.transact.toa.actions.models.response.MessageQueueResponse;
import com.flipkart.cp.transact.toa.util.common.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * Created by padmanabh.kulkarni on 28/06/15.
 */
public class MessageQueueCall {
//    private static final Logger log = LoggerFactory.getLogger(MessageQueueCall.class);
//    private static final Logger syslog = LoggerFactory.getLogger("sysLogger");

    private static String QUEUE_URL;
    private static String POOL_NAME = "toaMessageService";
    private static String COMMAND_KEY = "toaMessageService";

    static {
        // Reads the properties file based on environment and sets the TOA properties
        try {
            File file = new File(Constants.TOA_CONFIG_FILE);
            FileInputStream fileInputStream = new FileInputStream(file);

            Properties toaProperties = new Properties();
            toaProperties.load(fileInputStream);

            if (toaProperties != null) {
                QUEUE_URL = toaProperties.getProperty("QUEUE_URL");

       //         log.info("Static block - setTOAProperties - QUEUE_URL ", QUEUE_URL);
            } else {
         //       log.error("Static block - setTOAProperties - error in setting TOA properties");
           //     syslog.info(CosmosUtil.cosmosCustomMetricString("property.loading.failure", 1));
            }

        } catch (Exception e) {
            //log.error("Static block - Error in setting TOA properties {} {} {}", e, e.getMessage(), e.getStackTrace());
            //syslog.info(CosmosUtil.cosmosCustomMetricString("property.loading.failure", 1));
        }
    }

    public static MessageQueueResponse getMessageQueueResponse(MessageQueueRequest messageQueueRequest) throws Exception {
        SimpleProxyUtil.startServiceProxy();

        MessageQueueResponse messageQueueResponse = new MessageQueueResponse();
        HttpRequestWrapper httpRequestWrapper = new HttpRequestWrapper(QUEUE_URL).withHttpMethod(HttpMethod.POST).withPayload(messageQueueRequest.getOutboundMessage().getMessage().getBytes()).withHeaders(messageQueueRequest.getHeaders());

        try {
            String responseAsString = SimpleProxyHandler.getInstance().execute(httpRequestWrapper, POOL_NAME, COMMAND_KEY);
            if(responseAsString != null) {
         //       log.debug("response {}", responseAsString);
                messageQueueResponse = JsonUtils.getObjectMapper().readValue(responseAsString, new TypeReference<MessageQueueResponse>(){});
            }
        } catch (Exception e) {
           // log.error("Exception {} {}", e, e.getMessage());
            //syslog.info(CosmosUtil.cosmosCustomMetricString("varadhi.simple.proxy.call.failure", 1));
        }
        if(messageQueueResponse == null || messageQueueResponse.getMessageId() == null) {
        //    log.error("Relaying failed. Throwing exception");
         //   syslog.info(CosmosUtil.cosmosCustomMetricString("varadhi.simple.proxy.call.failure", 1));
            throw new Exception("Relay failed");
        } else {
            return messageQueueResponse;
        }

    }
}
