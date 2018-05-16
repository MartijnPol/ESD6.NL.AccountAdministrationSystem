package main.batch;

import main.domain.BatchLog;
import main.domain.Ownership;
import main.service.BatchLogService;
import main.service.OwnershipService;

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
@Named("OwnershipReader")
public class OwnershipReader implements ItemReader {

    @Inject
    private JobContext jobContext;
    @Inject
    private OwnershipService ownershipService;
    @Inject
    private BatchLogService batchLogService;

    private static final Logger logger = Logger.getLogger(OwnershipReader.class.getName());

    private ItemNumberCheckpoint checkpoint;
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
        this.batchLogService.createOrUpdate(new BatchLog(jobContext.getJobName(), "De ownership reader is open.", jobContext.getExecutionId()));
    }

    @Override
    public void close() {
        logger.log(Level.INFO, "Reader closed");
        this.batchLogService.createOrUpdate(new BatchLog(jobContext.getJobName(), "De ownership reader is gesloten.", jobContext.getExecutionId()));
    }

    @Override
    public Object readItem() {
        Ownership ownership = null;
        ownership = ownershipService.findById(idCount);
        if (ownership != null) {
            logger.log(Level.INFO, "Reading item");
            this.batchLogService.createOrUpdate(new BatchLog(jobContext.getJobName(), "Bezig met het lezen van ownership met id: " + ownership.getId().toString() , jobContext.getExecutionId()));
            this.idCount = idCount + 1L;
            return ownership;
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
