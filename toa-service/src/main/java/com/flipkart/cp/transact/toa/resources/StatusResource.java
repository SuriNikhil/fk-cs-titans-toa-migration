//package com.flipkart.cp.transact.toa.api.common.resources;
//
//import com.flipkart.cp.transact.toa.domain.entities.models.client.StatusResponse;
//import com.flipkart.cp.transact.toa.domain.entities.models.client.Toa;
//import com.flipkart.cp.transact.toa.domain.enums.EntityReferenceType;
//import com.flipkart.cp.transact.toa.metrics.Counter;
//import com.flipkart.cp.transact.toa.metrics.Meter;
//import com.flipkart.cp.transact.toa.metrics.Timer;
//import com.flipkart.cp.transact.toa.service.api.ToaService;
//import com.flipkart.cp.transact.toa.util.common.Constants;
//import com.flipkart.cp.transact.toa.util.common.CosmosUtil;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
//import org.apache.commons.httpclient.HttpStatus;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.ws.rs.*;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//import java.util.List;
//
///**
// * Created by padmanabh.kulkarni on 01/07/15.
// */
//@Component
//@Api(value = "/status", description = "Status of TOA")
//@Path("/status")
//public class StatusResource {
//    private static final Logger log = LoggerFactory.getLogger(StatusResource.class);
//    private static final Logger syslog = LoggerFactory.getLogger("sysLogger");
//
//    @Autowired
//    private ToaService toaService;
//
//    @Timer("statusByReference")
//    @Counter("statusByReference")
//    @Meter("statusByReference")
//    @GET
//    @Path("/by_reference")
//    @Produces({MediaType.APPLICATION_JSON})
//    @ApiOperation(value = "Get status by reference", notes = "Returns status of TOAs for given reference_id. Reference id can be any of toa_reference_id, external_reference_id, entity_reference_id")
//    @ApiResponses({
//            @ApiResponse(code = 400, message = Constants.NO_DATA_FOUND_DESCRIPTION, response = StatusResponse.class),
//            @ApiResponse(code = 400, message = Constants.MISSING_PARAMS_DESCRIPTION, response = StatusResponse.class),
//            @ApiResponse(code = 200, message = "", response = Toa.class)
//    })
//    public Response getToaByReference(@QueryParam("reference_id") String referenceId, @HeaderParam("Authorization") String authHeader){
//        try{
//            if(referenceId == null || referenceId.isEmpty()){
//                log.info("Null/empty referenceId");
//                StatusResponse statusResponse = new StatusResponse(HttpStatus.SC_BAD_REQUEST, Constants.MISSING_PARAMS_DESCRIPTION);
//                return Response.status(HttpStatus.SC_BAD_REQUEST).entity(statusResponse).build();
//            }
//
//            log.info("ReferenceId {}", referenceId);
//
//            List<Toa> toaList = toaService.getToaByReference(referenceId);
//
//            if(toaList == null || toaList.isEmpty()){
//                log.info("No TOA found for referenceId {}", referenceId);
//                syslog.info(CosmosUtil.cosmosCustomMetricString("status.call.toa.not.found", 1));
//                StatusResponse statusResponse = new StatusResponse(HttpStatus.SC_NO_CONTENT, Constants.NO_DATA_FOUND_DESCRIPTION);
//                return Response.status(HttpStatus.SC_NO_CONTENT).entity(statusResponse).build();
//            }
//
//            return Response.ok().entity(toaList).build();
//        }catch(Exception e) {
//            log.error("Exception {} {} {}", e, e.getMessage(), e.getStackTrace());
//            return Response.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).entity(new StatusResponse(HttpStatus.SC_INTERNAL_SERVER_ERROR, Constants.SERVER_ERROR_DESCRIPTION)).build();
//        }
//    }
//
//    @Timer("statusByEntity")
//    @Counter("statusByEntity")
//    @Meter("statusByEntity")
//    @GET
//    @Path("/by_entity/{referenceEntityType}/{referenceEntityId}")
//    @Produces({MediaType.APPLICATION_JSON})
//    @ApiOperation(value = "Get status by entity", notes = "Returns status of TOAs for given reference_entity_id & sub_entity_reference_id")
//    @ApiResponses({
//            @ApiResponse(code = 400, message = Constants.NO_DATA_FOUND_DESCRIPTION, response = StatusResponse.class),
//            @ApiResponse(code = 400, message = Constants.MISSING_PARAMS_DESCRIPTION, response = StatusResponse.class),
//            @ApiResponse(code = 200, message = "", response = Toa.class)
//    })
//    public Response getToaByEntity(@PathParam("referenceEntityId") String referenceEntityId,
//                                   @PathParam("referenceEntityType") EntityReferenceType referenceEntityType,
//                                   @HeaderParam("Authorization") String authHeader){
//        try{
//            log.info("referenceEntityType {} referenceEntityId {}", referenceEntityType, referenceEntityId);
//
//            if(referenceEntityId.isEmpty()){
//                log.info("Null/empty referenceId");
//                StatusResponse statusResponse = new StatusResponse(HttpStatus.SC_BAD_REQUEST, Constants.MISSING_PARAMS_DESCRIPTION);
//                return Response.status(HttpStatus.SC_BAD_REQUEST).entity(statusResponse).build();
//            }
//
//            Toa toa = new Toa();
//            toa.setReferenceEntityType(referenceEntityType);
//            toa.setReferenceEntityId(referenceEntityId);
//
//            List<Toa> toaList = toaService.getToaByEntity(toa);
//
//            if(toaList == null || toaList.isEmpty()){
//                log.info("No TOA found for referenceEntityId {} and referenceEntityType {}", referenceEntityId, referenceEntityType);
//                StatusResponse statusResponse = new StatusResponse(HttpStatus.SC_NO_CONTENT, Constants.NO_DATA_FOUND_DESCRIPTION);
//                syslog.info(CosmosUtil.cosmosCustomMetricString("status.call.toa.not.found", 1));
//                return Response.status(HttpStatus.SC_NO_CONTENT).entity(statusResponse).build();
//            }
//
//            return Response.ok().entity(toaList).build();
//        }catch(Exception e) {
//            log.error("Exception {} {} {}", e, e.getMessage(), e.getStackTrace());
//            syslog.info(CosmosUtil.cosmosCustomMetricString("get.toa.status.failure", 1));
//            return Response.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).entity(new StatusResponse(HttpStatus.SC_INTERNAL_SERVER_ERROR, Constants.SERVER_ERROR_DESCRIPTION)).build();
//        }
//    }
//
//    @Timer("statusByClientReference")
//    @Counter("statusByClientReference")
//    @Meter("statusByClientReference")
//    @GET
//    @Path("/by_toa_reference_id/{clientReferenceId}")
//    @Produces({MediaType.APPLICATION_JSON})
//    @ApiOperation(value = "Get status by client reference id", notes = "Returns status of TOAs for given toa_reference_id.")
//    @ApiResponses({
//            @ApiResponse(code = 400, message = Constants.NO_DATA_FOUND_DESCRIPTION, response = StatusResponse.class),
//            @ApiResponse(code = 400, message = Constants.MISSING_PARAMS_DESCRIPTION, response = StatusResponse.class),
//            @ApiResponse(code = 200, message = "", response = Toa.class)
//    })
//    public Response getToaByToaReferenceId(@PathParam("clientReferenceId") String clientReferenceId, @HeaderParam("Authorization") String authHeader){
//        try{
//            if(clientReferenceId == null || clientReferenceId.isEmpty()){
//                log.info("Null/empty referenceId");
//                StatusResponse statusResponse = new StatusResponse(HttpStatus.SC_BAD_REQUEST, Constants.MISSING_PARAMS_DESCRIPTION);
//                return Response.status(HttpStatus.SC_BAD_REQUEST).entity(statusResponse).build();
//            }
//
//            log.info("ReferenceId {}", clientReferenceId);
//
//            Toa toa = toaService.getTOAByToaReferenceId(clientReferenceId);
//
//            if(toa == null ){
//                log.info("No TOA found for toaReferenceId {}", clientReferenceId);
//                StatusResponse statusResponse = new StatusResponse(HttpStatus.SC_NO_CONTENT, Constants.NO_DATA_FOUND_DESCRIPTION);
//                syslog.info(CosmosUtil.cosmosCustomMetricString("toa.not.found", 1));
//                return Response.status(HttpStatus.SC_NO_CONTENT).entity(statusResponse).build();
//            }
//
//            return Response.ok().entity(toa).build();
//        }catch(Exception e) {
//            log.error("Exception {} {} {}", e, e.getMessage(), e.getStackTrace());
//            syslog.info(CosmosUtil.cosmosCustomMetricString("get.toa.status.failure", 1));
//            return Response.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).entity(new StatusResponse(HttpStatus.SC_INTERNAL_SERVER_ERROR, Constants.SERVER_ERROR_DESCRIPTION)).build();
//        }
//    }
//}
