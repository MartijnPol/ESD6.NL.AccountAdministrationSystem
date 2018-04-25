package main.batch;

import main.domain.Invoice;
import main.service.InvoiceService;

import javax.batch.api.chunk.ItemWriter;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Thom van de Pas on 25-4-2018
 */
@Dependent
@Named("InvoiceWriter")
public class InvoiceWriter implements ItemWriter {

    @Inject
    private InvoiceService invoiceService;
    @Inject
    private Logger logger;

    @Override
    public void open(Serializable serializable) throws Exception {

    }

    @Override
    public void close() throws Exception {

    }

    @Override
    public void writeItems(List<Object> items) throws Exception {

        for (Object item : items) {
            Invoice invoice = (Invoice) item;
            logger.log(Level.INFO, "Generating PDF for: " + invoice.getInvoiceNr());
            //TODO: Generate PDF here.
            //Here the pdf gets generated.
//            this.invoiceService.generateInvoicePdf(invoice);
        }

    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        return null;
    }
}
