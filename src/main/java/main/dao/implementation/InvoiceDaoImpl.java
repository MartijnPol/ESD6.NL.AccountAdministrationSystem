package main.dao.implementation;

import main.dao.InvoiceDao;
import main.dao.JPA;
import main.domain.Invoice;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

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
    public Long findLastInvoiceNr() {
        TypedQuery<Long> query = getEntityManager().createNamedQuery("invoice.findLastInvoiceNr", Long.class);

        return query.getSingleResult();
    }
}
