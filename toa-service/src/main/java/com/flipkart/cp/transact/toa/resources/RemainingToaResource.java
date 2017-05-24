//package com.flipkart.cp.transact.toa.api.common.resources;
//
//import com.flipkart.cp.transact.toa.domain.entities.models.client.RemainingToaRequest;
//import com.flipkart.cp.transact.toa.domain.entities.models.client.RemainingToaResponse;
//import com.flipkart.cp.transact.toa.domain.entities.models.client.StatusResponse;
//import com.flipkart.cp.transact.toa.domain.entities.models.client.Toa;
//import com.flipkart.cp.transact.toa.domain.entities.models.oms.OrderResponse;
//import com.flipkart.cp.transact.toa.domain.enums.EntityReferenceType;
//import com.flipkart.cp.transact.toa.domain.exceptions.BadRequestException;
//import com.flipkart.cp.transact.toa.domain.exceptions.OMSCallException;
//import com.flipkart.cp.transact.toa.metrics.Counter;
//import com.flipkart.cp.transact.toa.metrics.Meter;
//import com.flipkart.cp.transact.toa.metrics.Timer;
//import com.flipkart.cp.transact.toa.service.api.OrderService;
//import com.flipkart.cp.transact.toa.service.api.ToaService;
//import com.flipkart.cp.transact.toa.util.common.Constants;
//import com.flipkart.cp.transact.toa.util.common.CosmosUtil;
//import io.swagger.annotations.*;
//import org.apache.commons.httpclient.HttpStatus;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import javax.validation.Valid;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//
///**
// * Created by padmanabh.kulkarni on 03/02/16.
// */
//@Path("/toa")
//@Api(value = "/toa", description = "Issued and Remaining TOA")
//public class RemainingToaResource {
//
//    private static final Logger log = LoggerFactory.getLogger(RemainingToaResource.class);
//    private static final Logger syslog = LoggerFactory.getLogger("sysLogger");
//
//    @Autowired
//    private ToaService toaService;
//
//    @Autowired
//    private OrderService orderService;
//
//    @Path("/remaining_toa")
//    @Timer("remainingToa")
//    @Counter("remainingToa")
//    @Meter("remainingToa")
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces({MediaType.APPLICATION_JSON})
//    @ApiOperation(value = "Get issued and remaining toa", response = RemainingToaResponse.class, notes = "notes")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "", response = RemainingToaResponse.class)
//    })
//    public Response remainingToa(@ApiParam(value = "Json object containing entity and sub entity details.", required = true) @Valid RemainingToaRequest remainingToaRequest) {
//
//
//        try {
//            log.info("reference_entity_type {} reference_entity_id {}", remainingToaRequest.getReferenceEntityType(), remainingToaRequest.getReferenceEntityId());
//
//            if(remainingToaRequest != null) {
//
//                if (remainingToaRequest.getReferenceEntityType() != null
//                        && remainingToaRequest.getSubEntityReferenceType() != null
//                        && remainingToaRequest.getSubEntityReferenceIdList() != null
//                        && remainingToaRequest.getSubEntityReferenceIdList().size() > 0){
//
//                    log.info("number of sub_entity_reference_ids {}", remainingToaRequest.getSubEntityReferenceIdList().size());
//
//                    RemainingToaResponse remainingToaResponse = null;
//                    try {
//                        Toa toa = new Toa();
//                        toa.setReferenceEntityType(EntityReferenceType.ORDER);
//                        toa.setReferenceEntityId(remainingToaRequest.getReferenceEntityId());
//                        toa.setSubEntityReferenceId(remainingToaRequest.getSubEntityReferenceIdList().get(0));
//
//                        //from oms get sellingPrice + adjustments
//                        OrderResponse orderResponse = orderService.getOrderDetails(toa);
//                        if(orderResponse == null){
//                            throw new OMSCallException(Constants.EXTERNAL_SERVICE_CALL_FAILED);
//                        }
//                        if(orderResponse.getCount() <1 || orderResponse.getCount() < 1){
//                            throw new BadRequestException("Couldn't find order for orderItem");
//                        }
//
//                        remainingToaResponse = toaService.findRemainingToa(remainingToaRequest, false, orderResponse);
//                    } catch (BadRequestException e) {
//                        log.error("Exception {} {} {}", e, e.getMessage());
//                        syslog.info(CosmosUtil.cosmosCustomMetricString("remaining.toa.failure.bad.request", 1));
//                        return Response.status(HttpStatus.SC_BAD_REQUEST).entity(new StatusResponse(HttpStatus.SC_BAD_REQUEST, e.getMessage())).build();
//                    } catch (OMSCallException e) {
//                        log.error("Exception {} {} {}", e, e.getMessage());
//                        syslog.info(CosmosUtil.cosmosCustomMetricString("remaining.toa.failure", 1));
//                        return Response.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).build();
//                    }
//
//                    return Response.ok().entity(remainingToaResponse).build();
//                } else {
//                    return Response.status(HttpStatus.SC_BAD_REQUEST).build();
//                }
//            }else {
//                return Response.status(HttpStatus.SC_BAD_REQUEST).build();
//            }
//
//        } catch (Exception e){
//            log.error("Exception {} {} {}", e, e.getMessage(), e.getStackTrace());
//            syslog.info(CosmosUtil.cosmosCustomMetricString("remaining.toa.failure", 1));
//            return Response.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).entity(new StatusResponse(HttpStatus.SC_INTERNAL_SERVER_ERROR, Constants.SERVER_ERROR_DESCRIPTION)).build();
//        }
//    }
//
//}