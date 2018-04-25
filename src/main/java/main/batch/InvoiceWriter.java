package main.batch;

import javax.batch.api.chunk.ItemWriter;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * @author Thom van de Pas on 25-4-2018
 */
@Dependent
@Named("InvoiceWriter")
public class InvoiceWriter implements ItemWriter {

    @Override
    public void open(Serializable serializable) throws Exception {

    }

    @Override
    public void close() throws Exception {

    }

    @Override
    public void writeItems(List<Object> list) throws Exception {

    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        return null;
    }
}
