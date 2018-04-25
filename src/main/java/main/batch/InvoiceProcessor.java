package main.batch;

import main.domain.Invoice;

import javax.batch.api.chunk.ItemProcessor;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Thom van de Pas on 25-4-2018
 */
@Dependent
@Named("InvoiceProcessor")
public class InvoiceProcessor implements ItemProcessor {

    private static final Logger logger = Logger.getLogger(InvoiceProcessor.class.getName());

    @Override
    public Object processItem(Object object) throws Exception {

        Invoice invoice = (Invoice) object;
        logger.log(Level.INFO, "Started Batch Process of Invoice with number: " + invoice.getInvoiceNr());

        return invoice;
    }
}
