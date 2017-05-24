package com.flipkart.cp.transact.toa.actions.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.flipkart.cp.convert.simple.proxy.commands.HttpMethod;
import com.flipkart.cp.convert.simple.proxy.request.HttpRequestWrapper;
import com.flipkart.cp.transact.toa.domain.entities.models.oms.OrderResponse;
import com.flipkart.cp.transact.toa.util.common.*;
import flipkart.cp.oms.clients.checkout.wrappers.GetOrderItemResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;
import java.util.Properties;

/**
 * Created by padmanabh.kulkarni on 04/02/16.
 */
public class OMS2GetOrderCall {
//    private static final Logger log = LoggerFactory.getLogger(OMS2GetOrderCall.class);
//    private static final Logger syslog = LoggerFactory.getLogger("sysLogger");

    private static String OMS2_GET_ORDER_DETAILS_URI;

    static {
        // Reads the properties file based on environment and sets the TOA properties
        try {
            File file = new File(Constants.TOA_CONFIG_FILE);
            FileInputStream fileInputStream = new FileInputStream(file);

            Properties toaProperties = new Properties();
            toaProperties.load(fileInputStream);

            if (toaProperties != null) {
                OMS2_GET_ORDER_DETAILS_URI = toaProperties.getProperty("OMS2_GET_ORDER_DETAILS_URI");

//                log.info("Static block - setTOAProperties -    OMS2_GET_ORDER_DETAILS_URI {}  ",
//                        OMS2_GET_ORDER_DETAILS_URI);
            } else {
//                log.error("Static block - setTOAProperties - error in setting TOA properties");
//                syslog.info(CosmosUtil.cosmosCustomMetricString("property.loading.failure", 1));
            }

        } catch (Exception e) {
//            log.error("Static block - Error in setting TOA properties {} {} {}", e, e.getMessage(), e.getStackTrace());
//            syslog.info(CosmosUtil.cosmosCustomMetricString("property.loading.failure", 1));
        }
    }


    private static String POOL_NAME = "toaOMS2";
    private static String COMMAND_KEY = "toaOMS2";

    public static OrderResponse getOrderDetails(String orderItemId, Map<String , String > headers) throws Exception {
        SimpleProxyUtil.startServiceProxy();

        OrderResponse orderResponse = null;
        HttpRequestWrapper httpRequestWrapper = new HttpRequestWrapper(OMS2_GET_ORDER_DETAILS_URI + orderItemId).withHeaders(headers).withHttpMethod(HttpMethod.GET);

        try {
            String responseAsString = SimpleProxyHandler.getInstance().execute(httpRequestWrapper, POOL_NAME, COMMAND_KEY);
            if(responseAsString != null) {
//                log.debug("responaseString {}", responseAsString);
                orderResponse = JsonUtils.getObjectMapper().readValue(responseAsString, new TypeReference<GetOrderItemResponse>(){});
            }
        } catch (Exception e) {
//            log.error("Exception {} {} {}", e, e.getMessage(), e.getStackTrace());
//            syslog.info(CosmosUtil.cosmosCustomMetricString("oms.simple.proxy.call.failure", 1));
        }

        if(orderResponse == null) {
//            log.error("No response from OMS. Throwing exception");
//            syslog.info(CosmosUtil.cosmosCustomMetricString("oms.simple.proxy.call.failure", 1));
            throw new Exception("No response");
        } else {
            return orderResponse;
        }
    }
}
