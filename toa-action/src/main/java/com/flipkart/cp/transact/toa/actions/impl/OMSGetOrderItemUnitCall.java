package com.flipkart.cp.transact.toa.actions.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.flipkart.cp.convert.simple.proxy.commands.HttpMethod;
import com.flipkart.cp.convert.simple.proxy.request.HttpRequestWrapper;
import com.flipkart.cp.transact.toa.actions.models.request.OMSGetOrderItemUnitRequest;
import com.flipkart.cp.transact.toa.util.common.*;
import flipkart.cp.oms.clients.checkout.wrappers.GetOrderItemUnitResponse;
import flipkart.cp.oms.models.orderitemunit.OrderItemUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by padmanabh.kulkarni on 30/07/15.
 */
public class OMSGetOrderItemUnitCall {
//    private static final Logger log = LoggerFactory.getLogger(OMSGetOrderItemUnitCall.class);
//    private static final Logger syslog = LoggerFactory.getLogger("sysLogger");

    private static String POOL_NAME = "toaOMS";
    private static String COMMAND_KEY = "toaOMS";

    public static OrderItemUnit getOrderItemUnit(OMSGetOrderItemUnitRequest omsGetOrderItemUnitRequest, Map<String , String > headers) throws Exception {
        SimpleProxyUtil.startServiceProxy();

        GetOrderItemUnitResponse getOrderItemUnitResponse = null;

        HttpRequestWrapper httpRequestWrapper = new HttpRequestWrapper(Constants.OMS_BASE_URL + Constants.OMS_GET_ORDERS_URL
                + omsGetOrderItemUnitRequest.getOrderId()
                + Constants.OMS_GET_ORDER_ITEM_UNIT_URL + omsGetOrderItemUnitRequest.getOrderItemUnitId()).withHeaders(headers).withHttpMethod(HttpMethod.GET);
        try {
            String responseAsString = SimpleProxyHandler.getInstance().execute(httpRequestWrapper, POOL_NAME, COMMAND_KEY);
            if(responseAsString != null) {
                getOrderItemUnitResponse = JsonUtils.getObjectMapper().readValue(responseAsString, new TypeReference<GetOrderItemUnitResponse>(){});
            }
        } catch (Exception e) {
//            log.error("Exception {} {} {}", e, e.getMessage(), e.getStackTrace());
//            syslog.info(CosmosUtil.cosmosCustomMetricString("oms.simple.proxy.call.failure", 1));
        }

        if(getOrderItemUnitResponse == null) {
//            log.error("No response from OMS. Throwing exception");
//            syslog.info(CosmosUtil.cosmosCustomMetricString("oms.simple.proxy.call.failure", 1));
            throw new Exception("No response");
        } else {
            return getOrderItemUnitResponse.getOrderItemUnit();
        }
    }

}
