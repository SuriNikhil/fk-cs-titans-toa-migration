package com.flipkart.cp.transact.toa.resources;

import com.flipkart.cp.transact.toa.domain.entities.models.client.StatusResponse;
import com.flipkart.cp.transact.toa.domain.entities.models.common.Merchant;
import com.flipkart.cp.transact.toa.domain.enums.MerchantStatus;
import com.flipkart.cp.transact.toa.metrics.Counter;
import com.flipkart.cp.transact.toa.metrics.Meter;
import com.flipkart.cp.transact.toa.metrics.Timer;
import com.flipkart.cp.transact.toa.service.api.MerchantService;
import com.flipkart.cp.transact.toa.util.common.Constants;
import com.google.inject.Inject;
import com.wordnik.swagger.annotations.*;
import io.dropwizard.hibernate.UnitOfWork;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLIntegrityConstraintViolationException;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by padmanabh.kulkarni on 01/02/16.
 */
@Path("/merchant")
@Slf4j
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/merchant", description = "Merchant operations")
public class MerchantResource {
//    private static final Logger log = LoggerFactory.getLogger(MerchantResource.class);
//    private static final Logger syslog = LoggerFactory.getLogger("sysLogger");

    private MerchantService merchantService;

    @Inject
    public MerchantResource(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @POST
    @Timer("addMerchant")
    @Counter("addMerchant")
    @Meter("addMerchant")
    @Path("/add_merchant")

    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Add Merchant", response = Response.class, notes = "notes")
    @ApiResponses({
            @ApiResponse(code = 400, message = Constants.DUPLICATE_MERCHANT_DESCRIPTION, response = StatusResponse.class),
            @ApiResponse(code = 201, message = Constants.MERCHANT_CREATED_DESCRIPTION, response = StatusResponse.class)
    })
    @UnitOfWork
    public Response addMerchant(@ApiParam(value = "Json object containing merchant parameters.", required = true) Merchant merchant) {
        try {
            log.info("add merchant function started for merchant {}", merchant.toString());

            merchant.setStatus(MerchantStatus.ACTIVE.ordinal());

            StatusResponse statusResponse = null;
            try {
               log.info("adding merchant");
                statusResponse = merchantService.addMerchant(merchant);
            }//TODO check if this works for DataIntegrityViolationException
            catch (SQLIntegrityConstraintViolationException e) {
                log.error("Exception {} {} {}"+ e, e.getMessage(), e.getStackTrace());
                return Response.status(HttpStatus.SC_BAD_REQUEST).build();
            }

            return Response.status(statusResponse.getStatusCode()).entity(statusResponse).build();
        } catch (Exception e) {
            log.error("Exception {} {} {}"+e, e.getMessage(), e.getStackTrace());
            return Response.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).entity(new StatusResponse(HttpStatus.SC_INTERNAL_SERVER_ERROR, Constants.SERVER_ERROR_DESCRIPTION)).build();
        }
    }
}
