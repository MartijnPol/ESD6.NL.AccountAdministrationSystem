package main.batch;

import javax.batch.runtime.BatchRuntime;
import javax.ejb.Schedule;
import javax.ejb.Singleton;

/**
 * @author Thom van de Pas on 25-4-2018
 */
@Singleton
public class BatchProcessingScheduler {

    @Schedule(dayOfMonth = "21-Last")
    public void generateInvoices() {
        BatchRuntime.getJobOperator().start("invoicegeneration", null);
    }

}
