//package com.flipkart.cp.transact.toa.service.api;
//
//import com.flipkart.cp.transact.toa.domain.entities.models.client.Toa;
//import com.flipkart.cp.transact.toa.domain.entities.models.oms.OrderResponse;
//import com.flipkart.cp.transact.toa.domain.exceptions.BadRequestException;
//import com.flipkart.cp.transact.toa.domain.exceptions.OMSCallException;
//import flipkart.cp.oms.models.orderitem.OrderItem;
//
///**
// * Created by padmanabh.kulkarni on 29/07/15.
// */
//public interface OrderService {
//    //for OMS 3.0
//    Double findMaximumAllowedToa(Double maxPercentage, Toa toa) throws OMSCallException, BadRequestException;
//
//    OrderItem getOrderItem(Toa toa);
//
//    OrderResponse getOrderDetails(Toa toa);
//}
