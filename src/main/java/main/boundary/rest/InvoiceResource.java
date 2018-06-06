package main.boundary.rest;

import main.domain.Invoice;
import main.domain.Owner;
import main.domain.enums.PaymentStatus;
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

    /**
     * Gets the data of an Owner by its CSN.
     *
     * @param citizenServiceNumber is the CSN of the Owner.
     * @return the found Owner with all the data.
     */
    @GET
    @Path("{citizenServiceNumber}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getInvoicesForOwner(@PathParam("citizenServiceNumber") Long citizenServiceNumber) {

        if (citizenServiceNumber == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        Owner foundOwner = this.ownerService.findByCSN(citizenServiceNumber);

        if (foundOwner == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        List<Invoice> foundInvoices = invoiceService.findByOwner(foundOwner);

        return Response.ok(invoiceService.multipleToJson(foundInvoices)).build();
    }

    // TODO-Thom: Finish this method
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response getDailyDetailsFromInvoice(Invoice invoice) {

        if (null == invoice || null == invoice.getPeriod() || null == invoice.getInvoiceNr()) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        Invoice foundInvoice = this.invoiceService.findByInvoiceNr(invoice.getInvoiceNr());
        if (null == foundInvoice) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }


        return Response.ok().build();
    }

    /**
     * Update an InvoiceStatus when the Invoice is paid or cancelled.
     *
     * @param invoice is the Invoice to be updated.
     * @return the Status.
     */
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response updateInvoicePaymentStatus(Invoice invoice) {
        if (invoice == null || invoice.getInvoiceNr() == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        if (invoice.getPaymentStatus() == PaymentStatus.OPEN) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        Invoice foundInvoice = this.invoiceService.findByInvoiceNr(invoice.getInvoiceNr());

        if (foundInvoice == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        foundInvoice.setPaymentStatus(invoice.getPaymentStatus());

        this.invoiceService.createOrUpdate(foundInvoice);

        return Response.ok().build();
    }
}
