package main.service;

import main.dao.InvoiceDao;
import main.dao.JPA;
import main.dao.implementation.InvoiceDaoImpl;
import main.domain.Invoice;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Thom van de Pas on 4-4-2018
 */
@Stateless
public class InvoiceService {

    @Inject
    @JPA
    private InvoiceDao invoiceDao;

    public Invoice createOrUpdate(Invoice invoice) {
        return this.invoiceDao.createOrUpdate(invoice);
    }

    public void delete(Invoice invoice) {
        this.invoiceDao.delete(invoice);
    }

    public List<Invoice> findAll() {
        return this.invoiceDao.findAll();
    }

    public Invoice findById(Long id) {
        if (id != null) {
            return this.invoiceDao.findById(id);
        }
        return null;
    }

    /**
     * Gets the name of the month from a Date.
     *
     * @param date is the whole date from which the month name has to be shown.
     * @return a String of the month name.
     */
    public String getMonthName(Date date) {
        String monthName;

        Format formatter = new SimpleDateFormat("MMMM");
        monthName = formatter.format(date);

        return monthName;
    }

    /**
     * Finds an Invoice by its number.
     *
     * @param invoiceNr is the number of the Invoice to be found.
     * @return the found Invoice or null if the number is unknown.
     */
    public Invoice findByInvoiceNr(Long invoiceNr) {
        if (invoiceNr != null) {
            return this.invoiceDao.findByInvoiceNr(invoiceNr);
        }
        return null;
    }

    public void setInvoiceDao(InvoiceDao invoiceDao) {
        this.invoiceDao = invoiceDao;
    }
}
