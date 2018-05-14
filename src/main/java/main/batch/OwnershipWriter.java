package main.batch;

import main.domain.Invoice;
import main.domain.Ownership;
import main.interceptor.LoggingInterceptor;
import main.service.InvoiceService;

import javax.batch.api.chunk.ItemWriter;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Thom van de Pas on 25-4-2018
 */
@Dependent
@Named("OwnershipWriter")
@Interceptors(LoggingInterceptor.class)
public class OwnershipWriter implements ItemWriter {

    @Inject
    private InvoiceService invoiceService;

    private static final Logger logger = Logger.getLogger(OwnershipWriter.class.getName());

    @Override
    public void open(Serializable serializable) throws Exception {

    }

    @Override
    public void close() throws Exception {

    }

    @Override
    public void writeItems(List<Object> items) throws Exception {

        for (Object item : items) {
            Ownership ownership = (Ownership) item;

            logger.log(Level.INFO, "Generating invoice for owner: " + ownership.getOwner().getFullName());

            BigDecimal totalInvoiceAmount = invoiceService.generateTotalInvoiceAmount(ownership);
            Invoice invoice = new Invoice();
            invoice.setOwnership(ownership);
            invoice.setTotalAmount(totalInvoiceAmount);
            invoiceService.createOrUpdate(invoice);
        }
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        return null;
    }
}
