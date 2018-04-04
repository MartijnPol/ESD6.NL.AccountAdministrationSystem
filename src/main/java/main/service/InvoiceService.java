package main.service;

import main.dao.InvoiceDao;
import main.dao.JPA;
import main.domain.Invoice;
import main.domain.Owner;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * @author Thom van de Pas on 4-4-2018
 */
@Stateless
public class InvoiceService {

    @Inject
    @JPA
    private InvoiceDao invoiceDao;

    public Invoice create(Invoice invoice) {
        return this.invoiceDao.create(invoice);
    }

    public List<Invoice> getAll() {
        return this.invoiceDao.findAll();
    }
}
