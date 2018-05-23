package main.dao;

import main.domain.Invoice;
import main.domain.Owner;

import java.util.List;

/**
 * @author Thom van de Pas on 4-4-2018
 */
public interface InvoiceDao extends GenericDao<Invoice> {

    Invoice findByInvoiceNr(Long invoiceNr);

    Invoice findFirstInvoice();

    Long findLastInvoiceNr();

    List<Invoice> findByOwner(Owner foundOwner);
}
