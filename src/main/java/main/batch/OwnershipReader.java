package main.batch;

import main.domain.Ownership;
import main.interceptor.LoggingInterceptor;
import main.service.OwnershipService;

import javax.batch.api.chunk.ItemReader;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptor;
import javax.interceptor.Interceptors;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Thom van de Pas on 25-4-2018
 */
@Dependent
@Named("OwnershipReader")
@Interceptors(LoggingInterceptor.class)
public class OwnershipReader implements ItemReader {

    @Inject
    private OwnershipService ownershipService;

    private static final Logger logger = Logger.getLogger(OwnershipReader.class.getName());

    private ItemNumberCheckpoint checkpoint;
    private Ownership ownership;
    private Long idCount = 1L;

    public OwnershipReader() {
    }

    @Override
    public void open(Serializable serializable) {
        if (checkpoint == null) {
            this.checkpoint = new ItemNumberCheckpoint();
        } else {
            this.checkpoint = checkpoint;
        }
        logger.log(Level.INFO, "Reader open");
    }

    @Override
    public void close() {
        logger.log(Level.INFO, "Reader closed");
    }

    @Override
    public Object readItem() {
        this.ownership = null;
        this.ownership = ownershipService.findById(idCount);
        if (this.ownership != null) {
            logger.log(Level.INFO, "Reading item");
            this.idCount = idCount + 1L;
            return this.ownership;
        } else {
            logger.log(Level.INFO, "No items found");
            return null;
        }
    }

    @Override
    public Serializable checkpointInfo() {
        logger.log(Level.INFO, "Checkpoint Info");
        return checkpoint.getItemNumber();
    }
}
