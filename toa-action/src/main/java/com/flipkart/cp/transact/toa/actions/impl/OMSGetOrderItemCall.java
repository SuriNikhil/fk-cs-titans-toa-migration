package com.flipkart.cp.transact.toa.actions.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.flipkart.cp.convert.simple.proxy.commands.HttpMethod;
import com.flipkart.cp.convert.simple.proxy.request.HttpRequestWrapper;
import com.flipkart.cp.transact.toa.actions.models.request.OMSGetOrderItemRequest;
import com.flipkart.cp.transact.toa.util.common.*;
import flipkart.cp.oms.clients.checkout.wrappers.GetOrderItemResponse;
import flipkart.cp.oms.models.orderitem.OrderItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by padmanabh.kulkarni on 30/07/15.
 */
public class OMSGetOrderItemCall {
//    private static final Logger log = LoggerFactory.getLogger(OMSGetOrderItemCall.class);
//    private static final Logger syslog = LoggerFactory.getLogger("sysLogger");

    private static String POOL_NAME = "toaOMS";
    private static String COMMAND_KEY = "toaOMS";

    public static OrderItem getOrderItem(OMSGetOrderItemRequest omsOrderItemRequest, Map<String , String > headers) throws Exception {
        SimpleProxyUtil.startServiceProxy();

        GetOrderItemResponse getOrderItemResponse = null;
        HttpRequestWrapper httpRequestWrapper = new HttpRequestWrapper(Constants.OMS_BASE_URL + Constants.OMS_GET_ORDERS_URL
                + omsOrderItemRequest.getOrderId()
                + Constants.OMS_GET_ORDER_ITEM_URL + omsOrderItemRequest.getOrderItemId()).withHeaders(headers).withHttpMethod(HttpMethod.GET);

        try {
            String responseAsString = SimpleProxyHandler.getInstance().execute(httpRequestWrapper, POOL_NAME, COMMAND_KEY);
            if(responseAsString != null) {
                getOrderItemResponse = JsonUtils.getObjectMapper().readValue(responseAsString, new TypeReference<GetOrderItemResponse>(){});
            }
        } catch (Exception e) {
//            log.error("Exception {} {} {}", e, e.getMessage(), e.getStackTrace());
//            syslog.info(CosmosUtil.cosmosCustomMetricString("oms.simple.proxy.call.failure", 1));
        }

        if(getOrderItemResponse == null) {
//            log.error("No response from OMS. Throwing exception");
//            syslog.info(CosmosUtil.cosmosCustomMetricString("oms.simple.proxy.call.failure", 1));
            throw new Exception("No response");
        } else {
            return getOrderItemResponse.getOrderItem();
        }
    }
}
