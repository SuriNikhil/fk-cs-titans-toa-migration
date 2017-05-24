//package com.flipkart.cp.transact.toa.service.impl;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.node.ObjectNode;
//import com.flipkart.casclient.entity.Request;
//import com.flipkart.cp.transact.toa.domain.exceptions.BadRequestException;
//import com.ning.http.client.Response;
//import flipkart.cp.oms.models.orderitem.OrderItem;
//import flipkart.cp.oms.models.orderitemunit.OrderItemUnit;
//import lombok.extern.slf4j.Slf4j;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.inject.Named;
//import java.io.File;
//import java.util.List;
//import java.util.Map;
//import java.util.Properties;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.node.ObjectNode;
//import com.flipkart.casclient.client.HttpAuthClient;
//import com.flipkart.casclient.entity.Request;
//import com.flipkart.cp.transact.toa.actions.impl.OMSGetOrderItemCall;
//import com.flipkart.cp.transact.toa.actions.impl.OMSGetOrderItemUnitCall;
//import com.flipkart.cp.transact.toa.actions.models.request.OMSGetOrderItemRequest;
//import com.flipkart.cp.transact.toa.actions.models.request.OMSGetOrderItemUnitRequest;
//import com.flipkart.cp.transact.toa.domain.entities.models.client.Toa;
//import com.flipkart.cp.transact.toa.domain.entities.models.oms.OrderResponse;
//import com.flipkart.cp.transact.toa.domain.enums.SubEntityReferenceType;
//import com.flipkart.cp.transact.toa.domain.exceptions.BadRequestException;
//import com.flipkart.cp.transact.toa.domain.exceptions.OMSCallException;
//import com.flipkart.cp.transact.toa.service.api.OrderService;
//import com.flipkart.cp.transact.toa.service.api.ToaService;
//import com.flipkart.cp.transact.toa.util.common.*;
//import com.ning.http.client.Response;
//import flipkart.cp.oms.models.orderitem.OrderItem;
//import flipkart.cp.oms.models.orderitemunit.OrderItemUnit;
//import flipkart.cp.oms.models.orderitemunit.OrderItemUnitAdjustment;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.inject.Named;
//import java.io.File;
//import java.io.FileInputStream;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Properties;
//
///**
// * Created by padmanabh.kulkarni on 29/07/15.
// */
//@Named
//public class OrderServiceImpl implements OrderService{
//    //private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
//   // private static final Logger syslog = LoggerFactory.getLogger("sysLogger");
//
//    private static String OMS_AUTH_CLIENT_ID;
//    private static String OMS2_GET_ORDER_DETAILS_URI;
//    private static String OMS2_HOST;
//    private static String OMS2_PORT;
//
//    private static String CAS_URI;
//    private static String CAS_USERNAME;
//    private static String CAS_PASSWORD;
//
//
////    @Inject
////    private RequestExecutor requestExecutor;
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
//                OMS_AUTH_CLIENT_ID = toaProperties.getProperty("OMS_AUTH_CLIENT_ID");
//                OMS2_GET_ORDER_DETAILS_URI = toaProperties.getProperty("OMS2_GET_ORDER_DETAILS_URI");
//                OMS2_HOST = toaProperties.getProperty("OMS2_HOST");
//                OMS2_PORT = toaProperties.getProperty("OMS2_PORT");
//
//                //TODO
//      //          log.info("Static block - setTOAProperties -  OMS_AUTH_CLIENT_ID {}, OMS2_PORT {},  OMS2_GET_ORDER_DETAILS_URI {}"+
////                        OMS_AUTH_CLIENT_ID, OMS2_PORT, OMS2_GET_ORDER_DETAILS_URI);
//            } else {
////                log.error("Static block - setTOAProperties - error in setting TOA properties");
//               //TODO
//                // syslog.info(CosmosUtil.cosmosCustomMetricString("property.loading.failure", 1));
//            }
//
//        } catch (Exception e) {
////            log.error("Static block - Error in setting TOA properties {} {} {}"+ e, e.getMessage(), e.getStackTrace());
//          //  syslog.info(CosmosUtil.cosmosCustomMetricString("property.loading.failure", 1));
//        }
//    }
//
//
//   // @Autowired
//    //TODO not required as guice bundle declared
//    private ToaService toaService;
//
//    //For OMS 3.0
//    @Override
//    public Double findMaximumAllowedToa(Double maxPercentage, Toa toa) throws OMSCallException, BadRequestException {
//        Double sellingPrice = 0.0;
//        Double totalAdjustments = 0.0;
//        String subEntityReferenceIds = "";
//
//        Map<String , String > headers = new HashMap<>();
//        headers.put("Content-Type", "application-json");
////        headers.put("Authorization", AuthUtil.getAuthorizationHeader(OMS_AUTH_CLIENT_ID));
//
//        //decide to hit orderItem or orderItemUnit
//        if (toa.getSubEntityReferenceType().equals(SubEntityReferenceType.ORDER_ITEM)){
////            log.info("order item query");
////          adjustments  {orderItem{orderItemUnits{actualorderItemUnitId{adjustments[]}}}}
////          listPrice    {orderItem{productData{listPrice{value, currency}}}}
//
//            try {
//                OrderItem orderItem = OMSGetOrderItemCall.getOrderItem(new OMSGetOrderItemRequest(toa.getExternalReferenceId(),toa.getSubEntityReferenceId()), headers);
//
//                subEntityReferenceIds += "'" + toa.getSubEntityReferenceId() + "'";
//
//                if(toa.getQuantity() > orderItem.getQuantity() || toa.getQuantity() < 1){
////                    log.error("Invalid quantity {}  max quantity {}", toa.getQuantity(), orderItem.getQuantity());
//                    throw new BadRequestException(Constants.INVALID_QUANTITY);
//                }
//                sellingPrice = orderItem.getProductData().getListPrice().getValue() * orderItem.getQuantity();
//
//                List<OrderItemUnitAdjustment> adjustments;
//                for(Map.Entry<String , OrderItemUnit> orderItemUnitEntry : orderItem.getOrderItemUnits().entrySet()) {
//                    OrderItemUnit orderItemUnit = orderItemUnitEntry.getValue();
//                    adjustments = orderItemUnit.getAdjustments();
//
//                    subEntityReferenceIds += "," + "'" + orderItemUnit.getId() + "'";
//
//                    if(adjustments != null) {
//                        for (OrderItemUnitAdjustment orderItemUnitAdjustment : adjustments) {
//                            totalAdjustments += orderItemUnitAdjustment.getAmount().getValue();
//                        }
//                    }
//                }
//            }
//            catch (Exception e) {
//                if(e.getMessage()!= null && e.getMessage().equals(Constants.INVALID_QUANTITY)){
//                    throw new BadRequestException(e.getMessage());
//                }
////                log.error("Exception {} {} {}", e.getMessage(), e.getStackTrace());
//                throw new OMSCallException(e.getMessage());
//            }
//        }
//
//        else if (toa.getSubEntityReferenceType().equals(SubEntityReferenceType.ORDER_ITEM_UNIT)){
////          listPrice   {orderItem{productData{listPrice{value, currency}}}
////          adjustments {orderItemUnit{adjustments[]}}
////            log.info("order item unit query");
//
//            try {
//                OrderItemUnit orderItemUnit = OMSGetOrderItemUnitCall.getOrderItemUnit(new OMSGetOrderItemUnitRequest(toa.getExternalReferenceId(), toa.getSubEntityReferenceId()), headers);
//
//                subEntityReferenceIds +=   "'" + toa.getSubEntityReferenceId() + "'";
//
//                String orderItemId = orderItemUnit.getItemExternalId();
//
//                OrderItem orderItem = OMSGetOrderItemCall.getOrderItem(new OMSGetOrderItemRequest(toa.getExternalReferenceId(), orderItemId), headers);
//                sellingPrice = orderItem.getProductData().getListPrice().getValue();
//
//                subEntityReferenceIds += "," + "'" + orderItem.getExternalSystemId() + "'";
//
//                List<OrderItemUnitAdjustment> adjustments = orderItemUnit.getAdjustments();
//
//                if(adjustments != null) {
//                    for (OrderItemUnitAdjustment orderItemUnitAdjustment : adjustments) {
//                        totalAdjustments += orderItemUnitAdjustment.getAmount().getValue();
//                    }
//                }
//
//            } catch (Exception e) {
////                log.error("Exception {} {} {}" + e, e.getMessage(), e.getStackTrace());
//                throw new OMSCallException(e.getMessage());
//            }
//        }
//
//        //find already given toa from db maxToaAllowed = maxToa - toaAlready given
//        Toa refToa = new Toa();
//        refToa.setExternalReferenceId(toa.getExternalReferenceId());
//        refToa.setReferenceEntityType(toa.getReferenceEntityType());
//        refToa.setSubEntityReferenceType(toa.getSubEntityReferenceType());
//        refToa.setSubEntityReferenceId(subEntityReferenceIds);
//
//        Double previousToaSum = toaService.findPreviousToaSum(refToa);
////        log.info("previousToaSum {}", previousToaSum);
//
//        if(previousToaSum == null){
//            previousToaSum = 0.0;
//        }
//        //find maxToa = ((selling_price + totalAdjustments) * percentage/100) - previousToaSum
//        Double maximumAllowedToa = ((sellingPrice + totalAdjustments) * maxPercentage/100) - previousToaSum;
////        log.info("maximumAllowedToa {} for refId {}", maximumAllowedToa, toa.getToaReferenceId());
//        return maximumAllowedToa;
//    }
//
//    @Override
//    public OrderItem getOrderItem(Toa toa) {
//        String orderItemId = toa.getSubEntityReferenceId();
//
//        Map<String , String > headers = new HashMap<>();
//        headers.put("Content-Type", "application-json");
////        headers.put("Authorization", AuthUtil.getAuthorizationHeader(OMS_AUTH_CLIENT_ID));
//
//        if (toa.getSubEntityReferenceType().equals(SubEntityReferenceType.ORDER_ITEM_UNIT)){
//            try {
//                OrderItemUnit orderItemUnit = OMSGetOrderItemUnitCall.getOrderItemUnit(new OMSGetOrderItemUnitRequest(toa.getExternalReferenceId(), toa.getSubEntityReferenceId()), headers);
//                orderItemId = orderItemUnit.getItemExternalId();
//
//            } catch (Exception e) {
////                log.error("Exception {} {} {}"+ e, e.getMessage(), e.getStackTrace());
//            }
//        }
//
//        try {
//            return OMSGetOrderItemCall.getOrderItem(new OMSGetOrderItemRequest(toa.getExternalReferenceId(), orderItemId), headers);
//        } catch (Exception e) {
////            log.error("Exception {} {} {}"+ e, e.getMessage(), e.getStackTrace());
//        }
//        return null;
//    }
//
//    @Override
//    public OrderResponse getOrderDetails(Toa toa) {
//        Map<String , String > headers = new HashMap<>();
//        headers.put("X_OMS_CLIENT_ID", "jarvis");
////        headers.put("Authorization", AuthUtil.getAuthorizationHeader(OMS_AUTH_CLIENT_ID));
//
//        try {
////            OMS2GetOrderCall.getOrderDetails(toa.getSubEntityReferenceId(), headers);
//            return getOrder(toa);
//        } catch (Exception e) {
////            log.error("Exception {} {} {}"+ e, e.getMessage(), e.getStackTrace());
//        }
//        return null;
//    }
//
//    private OrderResponse getOrder(Toa toa) throws Exception {
//        Request omsRequest = new Request();
//        omsRequest.setHeader("X_OMS_CLIENT_ID", "jarvis");
//        omsRequest.setUrl("http://" + OMS2_HOST+ ":" + OMS2_PORT + OMS2_GET_ORDER_DETAILS_URI + toa.getSubEntityReferenceId());
//
////        log.info(" requestUrl {}", omsRequest.getUrl());
//
//        Response response;
//        HttpAuthClient httpAuthClient = AuthClientUtil.getHttpAuthClient();
//        try {
//            response = httpAuthClient.executeGet(omsRequest);
//        } catch (Exception e) {
////            log.error("Exception {} {} {}"+ e, e.getMessage(), e.getStackTrace());
//            return null;
//        }
//        if ( response != null && !(response.getStatusCode() >= 200 && response.getStatusCode() < 300) ) {
//            String responseBody = "";
//            try {
//                responseBody = response.getResponseBody();
//            } catch (Exception e) {
////                log.error("Exception while fetching response body " + e.getMessage());
//            }
////            log.warn(omsRequest.getUrl() + " failed with response code "+ response.getStatusCode() + " response body " + responseBody);
//            throw  new Exception(omsRequest.getUrl() +" failed. " + responseBody);
//        }
//
//        return getOrderResponse(response, omsRequest);
//    }
//
//    private OrderResponse getOrderResponse(Response response, Request omsRequest) throws Exception {
//        if ( response != null && !(response.getStatusCode() >= 200 && response.getStatusCode() < 300) ) {
////            log.info("response code is in 200s");
//            String responseBody = "";
//            try {
//                responseBody = response.getResponseBody();
//            } catch (Exception e) {
////                log.error("Exception while fetching response body " + e.getMessage());
//            }
////            log.warn(omsRequest.getUrl() + " failed with response code "+ response.getStatusCode() + " response body " + responseBody);
//            throw  new OMSCallException( omsRequest.getUrl()+" failed with response code " + response.getStatusCode() + " response body" + responseBody);
//        }
//
//        try {
//
//            if (response.getResponseBody()==null || response.getResponseBody().isEmpty()) {
////                log.info("No response received");
//                return null;
//            }
//
////            log.info("Response from " + omsRequest.getUrl() + " : " + response.getResponseBody());
//            ObjectMapper mapper = JsonUtils.getObjectMapper();
//
//            if (OrderResponse.class.equals(ObjectNode.class.getName())) {
////                log.info("response is of type OrderResponse");
//                return OrderResponse.class.cast(mapper.readTree(response.getResponseBody()));
//            } else {
////                log.info("mapping response to OrderResponse");
//                return mapper.readValue(response.getResponseBody(), OrderResponse.class);
//            }
//
//        } catch (Exception e) {
////            log.error("Exception ", e);
//            throw new OMSCallException("Failed to read the response from " + omsRequest.getUrl() +". "+ e.getMessage());
//        }
//    }
//
//}
//
