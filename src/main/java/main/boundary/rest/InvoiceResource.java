package main.boundary.rest;

import main.domain.Invoice;
import main.domain.Owner;
import main.service.InvoiceService;
import main.service.OwnerService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author Thom van de Pas on 16-5-2018
 */
@Path("invoices")
@Stateless
public class InvoiceResource {

    @Inject
    private InvoiceService invoiceService;
    @Inject
    private OwnerService ownerService;

    @GET
    @Path("{citizenServiceNumber}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getInvoicesForOwner(@PathParam("citizenServiceNumber")Long citizenServiceNumber) {

        if (citizenServiceNumber == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        Owner foundOwner = this.ownerService.findByCSN(citizenServiceNumber);

        if (foundOwner == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        List<Invoice> foundInvoices = invoiceService.findByOwner(foundOwner);

        return Response.ok(foundInvoices).build();
    }
}
