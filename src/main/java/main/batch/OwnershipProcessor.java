package main.batch;

import main.domain.Ownership;
import main.service.BatchLogService;

import javax.batch.api.chunk.ItemProcessor;
import javax.batch.runtime.context.JobContext;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Thom van de Pas on 25-4-2018
 */
@Dependent
@Named("OwnershipProcessor")
public class OwnershipProcessor implements ItemProcessor {


    private static final Logger logger = Logger.getLogger(OwnershipProcessor.class.getName());

    @Override
    public Object processItem(Object object) throws Exception {

        Ownership ownership = (Ownership) object;
        logger.log(Level.INFO, "Batch process started for " + ownership.getOwner().getFullName() + " invoice is being generated");

        return ownership;
    }
}
