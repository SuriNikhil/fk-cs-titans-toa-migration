package com.flipkart.cp.transact.toa.resources;

import com.flipkart.cp.transact.toa.domain.entities.models.client.HealthCheckResponse;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by padmanabh.kulkarni on 04/09/15.
 */

@Path("/elb-healthcheck")
@Produces(MediaType.APPLICATION_JSON)
public class HealthCheckResource {
    @GET
    public Response ping(){
        HealthCheckResponse healthCheckResponse = new HealthCheckResponse();
        healthCheckResponse.setCapacity(90);
        healthCheckResponse.setUptime(1442831687);
        healthCheckResponse.setRequests(10001);
        return Response.ok().entity(healthCheckResponse).build();
    }
}
