package main.utils;

import main.service.InvoiceService;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import javax.inject.Inject;
import java.io.Serializable;

public class InvoiceNrGenerator implements IdentifierGenerator {

    @Inject
    InvoiceService invoiceService;

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        Long lastInvoiceNr = invoiceService.findLastInvoiceNr();
        Long nextInvoiceNr = invoiceService.getNextInvoiceNr(lastInvoiceNr);

        return nextInvoiceNr;
    }
}
