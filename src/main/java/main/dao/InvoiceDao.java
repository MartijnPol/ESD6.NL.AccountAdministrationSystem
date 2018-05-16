package main.dao;

import main.domain.Invoice;

/**
 * @author Thom van de Pas on 4-4-2018
 */
public interface InvoiceDao extends GenericDao<Invoice> {

    Invoice findByInvoiceNr(Long invoiceNr);

    Invoice findFirstInvoice();

    Invoice create(Invoice invoice);

    Invoice update(Invoice invoice);
}
