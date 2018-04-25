package main.batch;

import main.domain.Invoice;
import main.service.InvoiceService;

import javax.batch.api.chunk.ItemReader;
import javax.batch.runtime.context.JobContext;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Thom van de Pas on 25-4-2018
 */
@Dependent
@Named("InvoiceReader")
public class InvoiceReader implements ItemReader {

    @Inject
    private InvoiceService invoiceService;
    @Inject
    private JobContext jobContext;
    @Inject
    private Logger logger;

    private ItemNumberCheckpoint checkpoint;
    private Invoice invoice;

    public InvoiceReader() {
    }

    @Override
    public void open(Serializable serializable) throws Exception {
        if (checkpoint == null) {
            this.checkpoint = new ItemNumberCheckpoint();
        } else {
            this.checkpoint = (ItemNumberCheckpoint) checkpoint;
        }
        this.logger.log(Level.INFO, "Reader open");
    }

    @Override
    public void close() throws Exception {
    }

    @Override
    public Object readItem() throws Exception {
        this.invoice = null;
        this.invoice = invoiceService.findFirstInvoice();
        if (this.invoice != null) {
            this.logger.log(Level.INFO, "Reading item");
            return this.invoice;
        } else {
            this.logger.log(Level.INFO, "No items found");
            return null;
        }
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        this.logger.log(Level.INFO, "Checkpoint Info");
        return checkpoint.getItemNumber();
    }
}
