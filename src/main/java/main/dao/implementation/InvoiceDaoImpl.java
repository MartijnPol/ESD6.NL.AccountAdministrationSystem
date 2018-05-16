package main.dao.implementation;

import main.dao.InvoiceDao;
import main.dao.JPA;
import main.domain.Car;
import main.domain.Invoice;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Thom van de Pas on 4-4-2018
 */
@Stateless
@JPA
public class InvoiceDaoImpl extends GenericDaoJPAImpl<Invoice> implements InvoiceDao {

    public InvoiceDaoImpl() {

    }

    @Override
    public Invoice findByInvoiceNr(Long invoiceNr) {
        TypedQuery<Invoice> query = getEntityManager().createNamedQuery("invoice.findByInvoiceNr", Invoice.class)
                .setParameter("invoiceNr", invoiceNr);

        return oneResult(query);
    }

    @Override
    public Invoice findFirstInvoice() {
        TypedQuery<Invoice> query = getEntityManager().createNamedQuery("invoice.findFirstInvoice", Invoice.class);

        return oneResult(query);
    }

    @Override
    public Invoice create(Invoice invoice) {
        this.entityManager.persist(invoice);

        return invoice;
    }

    @Override
    public Invoice update(Invoice invoice) {
        return getEntityManager().merge(invoice);
    }
}
