package main.batch;

import main.domain.Ownership;
import main.service.OwnershipService;

import javax.batch.api.chunk.ItemReader;
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
@Named("OwnershipReader")
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
        this.logger.log(Level.INFO, "Reader closed");
    }

    @Override
    public Object readItem() throws Exception {
        this.ownership = null;
        this.ownership = ownershipService.findById(idCount);
        if (this.ownership != null) {
            this.logger.log(Level.INFO, "Reading item");
            this.idCount = idCount + 1L;
            return this.ownership;
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
