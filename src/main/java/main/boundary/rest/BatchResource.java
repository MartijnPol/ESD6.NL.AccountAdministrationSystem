package main.boundary.rest;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("batch")
@Stateless
public class BatchResource {

    private final JobOperator jobOperator = BatchRuntime.getJobOperator();

    @GET
    public Response startJob() {
        Long executeId = jobOperator.start("invoicegeneration", null);

        return Response.ok("Batch process invoice generation started: " + executeId).build();
    }

    @GET
    @Path("{executeId}")
    public Response stopJob(@PathParam("executeId") Long executeId) {
        jobOperator.stop(executeId);

        return Response.ok("Batch process invoice generation stopped: " + executeId).build();
    }
}
