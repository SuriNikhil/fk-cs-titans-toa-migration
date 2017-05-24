//package com.flipkart.cp.transact.toa.api.common.resources;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.flipkart.cp.transact.toa.domain.entities.models.client.*;
//import com.flipkart.cp.transact.toa.domain.exceptions.BadRequestException;
//import com.flipkart.cp.transact.toa.domain.exceptions.InvalidMerchantException;
//import com.flipkart.cp.transact.toa.domain.exceptions.OMSCallException;
//import com.flipkart.cp.transact.toa.metrics.Counter;
//import com.flipkart.cp.transact.toa.metrics.Meter;
//import com.flipkart.cp.transact.toa.metrics.Timer;
//import com.flipkart.cp.transact.toa.service.api.ToaService;
//import com.flipkart.cp.transact.toa.util.common.Constants;
//import com.flipkart.cp.transact.toa.util.common.CosmosUtil;
//import com.flipkart.cp.transact.toa.util.common.JsonUtils;
//import io.swagger.annotations.*;
//import org.apache.commons.httpclient.HttpStatus;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DuplicateKeyException;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.TransactionDefinition;
//import org.springframework.transaction.TransactionStatus;
//import org.springframework.transaction.support.DefaultTransactionDefinition;
//
//import javax.servlet.http.HttpServletResponse;
//import javax.validation.Valid;
//import javax.ws.rs.*;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//
//
///**
// * Created by padmanabh.kulkarni on 29/05/15.
// */
//@Path("/toa")
//@Component
//@Api(value = "/toa", description = "Token Of Apology")
//public class RaiseTOAResource {
//    private static final Logger log = LoggerFactory.getLogger(RaiseTOAResource.class);
//    private static final Logger syslog = LoggerFactory.getLogger("sysLogger");
//
//    @Autowired
//    private PlatformTransactionManager transactionManager;
//
//    @Autowired
//    private ToaService toaService;
//
//    @POST
//    @Timer("raiseToa")
//    @Counter("raiseToa")
//    @Meter("raiseToa")
//    @Path("/raise_token_of_apology")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    @ApiOperation(value = "Raise TOA", response = StatusResponse.class, notes = "toa_reference_id: Unique id generated at client's end for each request. Can be used as common reference between two systems." +
//            "reference_entity_type can be 'ORDER' and sub_entity_reference_type can be 'ORDER_ITEM' OR 'ORDER_ITEM_UNIT'.")
//    @ApiResponses({
//            @ApiResponse(code = 400, message = Constants.DUPLICATE_TOA_REFERENCE_ID_DESCRIPTION, response = StatusResponse.class),
//            @ApiResponse(code = 400, message = Constants.MISSING_HEADERS_DESCRIPTION, response = StatusResponse.class),
//            @ApiResponse(code = 201, message = Constants.TOA_CREATED_DESCRIPTION, response = StatusResponse.class)
//    })
//    public Response raiseTokenOfApology(@ApiParam(value = "Json object containing TOA parameters.", required = true) @Valid ToaRequest toaRequest,
//                                        @HeaderParam(value = "X_TENANT_ID") String tenantIdHeader, @HeaderParam("Authorization") String authHeader) {
//        try {
//
//            try {
//                log.info("toa payload {}", JsonUtils.getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(toaRequest));
//            } catch (JsonProcessingException e) {
//                log.error("Exception {} {} {}", e, e.getMessage(), e.getStackTrace());
//            }
//
//            if (tenantIdHeader == null) {
//                log.info("Either or both of the header are empty.");
//                StatusResponse statusResponse = new StatusResponse(HttpStatus.SC_BAD_REQUEST, Constants.MISSING_HEADERS_DESCRIPTION);
//                return Response.status(HttpServletResponse.SC_BAD_REQUEST).entity(statusResponse).build();
//            }
//
//            Toa toa = new Toa(toaRequest);
//            toa.setTenantId(tenantIdHeader);
//
//            if(toaService.isUniqueCustomerIdAndCustomerRequestId(toa)) {
//                TransactionDefinition def = new DefaultTransactionDefinition();
//                TransactionStatus dbStatus = transactionManager.getTransaction(def);
//
//                try {
//                    if (toaService.processTOA(toa)) {
//                        transactionManager.commit(dbStatus);
//                        StatusResponse statusResponse = new StatusResponse(HttpStatus.SC_CREATED, Constants.TOA_CREATED_DESCRIPTION);
//                        syslog.info(CosmosUtil.cosmosCustomMetricString("RaiseToa.success", 1));
//                        return Response.status(HttpServletResponse.SC_CREATED).entity(statusResponse).build();
//                    } else {
//                        //To catch unique constraint violation on toa_reference_id
//                        transactionManager.rollback(dbStatus);
//                        syslog.info(CosmosUtil.cosmosCustomMetricString("RaiseToa.failure", 1));
//                        return Response.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).build();
//                    }
//                } catch (DuplicateKeyException e) {
//                    //To catch unique constraint violation on toa_reference_id
//                    syslog.info(CosmosUtil.cosmosCustomMetricString("RaiseToa.failure.bad.request", 1));
//                    log.error("Exception {} {} {}", e, e.getMessage(), e.getStackTrace());
//                    transactionManager.rollback(dbStatus);
//                    StatusResponse statusResponse = new StatusResponse(HttpStatus.SC_BAD_REQUEST, Constants.DUPLICATE_TOA_REFERENCE_ID_DESCRIPTION);
//                    return Response.status(HttpServletResponse.SC_BAD_REQUEST).entity(statusResponse).build();
//                } catch (BadRequestException e) {
//                    transactionManager.rollback(dbStatus);
//                    syslog.info(CosmosUtil.cosmosCustomMetricString("RaiseToa.failure.bad.request", 1));
//                    log.error("Exception {} {} {}", e, e.getMessage(), e.getStackTrace());
//                    StatusResponse statusResponse = new StatusResponse(HttpStatus.SC_BAD_REQUEST, e.getMessage());
//                    return Response.status(HttpServletResponse.SC_BAD_REQUEST).entity(statusResponse).build();
//                } catch (OMSCallException e) {
//                    transactionManager.rollback(dbStatus);
//                    syslog.info(CosmosUtil.cosmosCustomMetricString("RaiseToa.failure", 1));
//                    log.info("Exception occurred in OMS call returning with 500");
//                    return Response.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).build();
//                } catch (InvalidMerchantException e) {
//                    transactionManager.rollback(dbStatus);
//                    syslog.info(CosmosUtil.cosmosCustomMetricString("RaiseToa.failure.bad.request", 1));
//                    log.info("Invalid/Inactive merchant");
//                    return Response.status(HttpServletResponse.SC_BAD_REQUEST).entity(new StatusResponse(HttpStatus.SC_BAD_REQUEST, e.getMessage())).build();
//                } catch (Exception e) {
//                    transactionManager.rollback(dbStatus);
//                    log.error("Exception {} {} {}", e, e.getMessage(), e.getStackTrace());
//                    return Response.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).build();
//                }
//            }else{
//                log.error("Not unique customerId {} and customerRequestId {}", toa.getCustomerAccountId(), toa.getCustomerRequestId());
//                return Response.status(HttpStatus.SC_BAD_REQUEST).entity(new StatusResponse(HttpStatus.SC_BAD_REQUEST, Constants.DUPLICATE_CUSTOMER_ID_AND_REQUEST_ID_COMBINATION)).build();
//            }
//        }catch (Exception e){
//            log.error("Exception {} {} {}", e, e.getMessage(), e.getStackTrace());
//            return Response.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).entity(new StatusResponse(HttpStatus.SC_INTERNAL_SERVER_ERROR, Constants.SERVER_ERROR_DESCRIPTION)).build();
//        }
//    }
//
//    @POST
//    @Timer("raiseBulkToa")
//    @Counter("raiseBulkToa")
//    @Meter("raiseBulkToa")
//    @Path("/raise_bulk_token_of_apology")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    @ApiOperation(value = "Raise Bulk TOA", response = StatusResponse.class, notes = "toa_reference_id: Unique id generated at client's end for each request. Can be used as common reference between two systems." +
//            "reference_entity_type can be 'ORDER' and sub_entity_reference_type can be 'ORDER_ITEM' OR 'ORDER_ITEM_UNIT'.")
//    @ApiResponses({
//            @ApiResponse(code = 400, message = Constants.DUPLICATE_TOA_REFERENCE_ID_DESCRIPTION, response = StatusResponse.class),
//            @ApiResponse(code = 400, message = Constants.MISSING_HEADERS_DESCRIPTION, response = StatusResponse.class),
//            @ApiResponse(code = 201, message = Constants.TOA_CREATED_DESCRIPTION, response = StatusResponse.class)
//    })
//
//    public Response raiseBulkTokenOfApology(@ApiParam(value = "Json object containing TOA creation details.", required = true) @Valid BulkToaRequest bulkToaRequest, @HeaderParam(value = "X_TENANT_ID") String tenantIdHeader, @HeaderParam("Authorization") String authHeader) {
//        try {
//            try {
//                log.info("toa payload {}", JsonUtils.getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(bulkToaRequest));
//            } catch (JsonProcessingException e) {
//                log.error("Exception {} {} {}", e, e.getMessage(), e.getStackTrace());
//            }
//
//            if (tenantIdHeader == null) {
//                log.info("Missing one of the required headers.");
//                StatusResponse statusResponse = new StatusResponse(HttpStatus.SC_BAD_REQUEST, Constants.MISSING_HEADERS_DESCRIPTION);
//                return Response.status(HttpServletResponse.SC_BAD_REQUEST).entity(statusResponse).build();
//            }
//
//            Toa toa = new Toa(bulkToaRequest);
//
//            if(toaService.isUniqueCustomerIdAndCustomerRequestId(toa)){
//                TransactionDefinition def = new DefaultTransactionDefinition();
//                TransactionStatus dbStatus = transactionManager.getTransaction(def);
//
//                for (ToaItem toaItem : bulkToaRequest.getToaItems()) {
//                    toa.setToaReferenceId(toaItem.getToaReferenceId());
//                    toa.setQuantity(toaItem.getQuantity());
//                    toa.setSubEntityReferenceId(toaItem.getSubEntityReferenceId());
//                    toa.setToaAmount(toaItem.getToaAmount());
//
//                    toa.setSellerId(toaItem.getSellerId());
//                    toa.setTenantId(tenantIdHeader);
//
//                    try {
//                        if(toaService.processTOA(toa)){
//                            continue;
//                        }
//                        else{
//                            transactionManager.rollback(dbStatus);
//                            syslog.info(CosmosUtil.cosmosCustomMetricString("RaiseToa.failure", 1));
//                            return Response.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
//                                    .entity(new StatusResponse(HttpStatus.SC_INTERNAL_SERVER_ERROR, Constants.SERVER_ERROR_DESCRIPTION)).build();
//                        }
//                    } catch (DuplicateKeyException e) {
//                        //To catch unique constraint violation on toa_reference_id
//                        log.error("Exception {} {} {}", e, e.getMessage(), e.getStackTrace());
//                        transactionManager.rollback(dbStatus);
//                        syslog.info(CosmosUtil.cosmosCustomMetricString("RaiseToa.failure.bad.request", 1));
//                        StatusResponse statusResponse = new StatusResponse(HttpStatus.SC_BAD_REQUEST, Constants.DUPLICATE_TOA_REFERENCE_ID_DESCRIPTION);
//                        return Response.status(HttpServletResponse.SC_BAD_REQUEST).entity(statusResponse).build();
//                    } catch (BadRequestException e) {
//                        log.error("Exception {} {} {}", e, e.getMessage(), e.getStackTrace());
//                        transactionManager.rollback(dbStatus);
//                        syslog.info(CosmosUtil.cosmosCustomMetricString("RaiseToa.failure.bad.request", 1));
//                        StatusResponse statusResponse = new StatusResponse(HttpStatus.SC_BAD_REQUEST, e.getMessage());
//                        return Response.status(HttpServletResponse.SC_BAD_REQUEST).entity(statusResponse).build();
//                    } catch (OMSCallException e) {
//                        log.info("Exception occurred in OMS call returning with 500");
//                        transactionManager.rollback(dbStatus);
//                        syslog.info(CosmosUtil.cosmosCustomMetricString("RaiseToa.failure", 1));
//                        return Response.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).build();
//                    } catch (InvalidMerchantException e) {
//                        syslog.info(CosmosUtil.cosmosCustomMetricString("RaiseToa.failure.bad.request", 1));
//                        transactionManager.rollback(dbStatus);
//                        log.info("Invalid/Inactive merchant");
//                        return Response.status(HttpServletResponse.SC_BAD_REQUEST).entity(new StatusResponse(HttpStatus.SC_BAD_REQUEST, e.getMessage())).build();
//                    } catch(Exception e) {
//                        transactionManager.rollback(dbStatus);
//                        log.error("Exception {} {} {}", e, e.getMessage(), e.getStackTrace());
//                        syslog.info(CosmosUtil.cosmosCustomMetricString("RaiseToa.failure", 1));
//                        return Response.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).build();
//                    }
//                }
//
//                transactionManager.commit(dbStatus);
//
//                syslog.info(CosmosUtil.cosmosCustomMetricString("RaiseToa.success", 1));
//                StatusResponse statusResponse = new StatusResponse(HttpStatus.SC_CREATED, Constants.TOA_CREATED_DESCRIPTION);
//                return Response.status(HttpServletResponse.SC_CREATED).entity(statusResponse).build();
//
//            }
//            else {
//                log.error("Not unique customerId {} and customerRequestId {}", toa.getCustomerAccountId(), toa.getCustomerRequestId());
//                return Response.status(HttpStatus.SC_BAD_REQUEST).entity(new StatusResponse(HttpStatus.SC_BAD_REQUEST, Constants.DUPLICATE_CUSTOMER_ID_AND_REQUEST_ID_COMBINATION)).build();
//            }
//        }catch(Exception e) {
//            log.error("Exception {} {} {}", e, e.getMessage(), e.getStackTrace());
//            syslog.info(CosmosUtil.cosmosCustomMetricString("RaiseToa.failure", 1));
//            return Response.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).entity(new StatusResponse(HttpStatus.SC_INTERNAL_SERVER_ERROR, Constants.SERVER_ERROR_DESCRIPTION)).build();
//        }
//    }
//}