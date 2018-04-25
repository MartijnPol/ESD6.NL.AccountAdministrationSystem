package main.batch;

import javax.batch.api.chunk.ItemProcessor;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

/**
 * @author Thom van de Pas on 25-4-2018
 */
@Dependent
@Named("InvoiceProcessor")
public class InvoiceProcessor implements ItemProcessor {

    @Override
    public Object processItem(Object o) throws Exception {
        return null;
    }
}
