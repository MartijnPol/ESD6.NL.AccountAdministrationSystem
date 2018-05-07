package main.dao;

import main.dao.implementation.InvoiceDaoImpl;
import main.domain.Invoice;
import main.domain.enums.PaymentStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.DatabaseCleaner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class InvoiceDaoTest {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("AccountAdministrationTestPU");
    private EntityManager em;
    private EntityTransaction tx;
    private InvoiceDaoImpl invoiceDao;
    private Invoice invoice;

    @Before
    public void setUp() {
        try {
            new DatabaseCleaner(emf.createEntityManager()).clean();
        } catch (SQLException ex) {
            Logger.getLogger(CarDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        em = emf.createEntityManager();
        tx = em.getTransaction();

        invoiceDao = new InvoiceDaoImpl();
        invoiceDao.setEntityManager(em);

        invoice = new Invoice();
    }

    @Test
    public void saveInvoiceSuccessfulTest() {
        Integer expResult = 1;
        Integer unExpResult = 2;

        tx.begin();
        invoiceDao.createOrUpdate(invoice);
        tx.commit();

        List<Invoice> invoices = invoiceDao.findAll();
        int nrInvoices = invoices.size();

        assertThat(nrInvoices, is(expResult));
        assertThat(nrInvoices, not(unExpResult));
    }

    @Test
    public void updateInvoiceSuccessfulTest() {
        invoice.setInvoiceNr(1L);

        tx.begin();
        invoiceDao.createOrUpdate(invoice);
        tx.commit();

        Invoice invoiceByNr = invoiceDao.findByInvoiceNr(1L);

        assertThat(invoiceByNr.getInvoiceNr(), is(1L));

        tx.begin();
        invoiceByNr.setInvoiceNr(2L);
        invoiceByNr.setPaymentStatus(PaymentStatus.PAID);
        invoiceDao.createOrUpdate(invoiceByNr);
        tx.commit();

        Invoice invoiceEmpty = invoiceDao.findByInvoiceNr(1L);
        Invoice invoiceNotEmpty = invoiceDao.findByInvoiceNr(2L);

        assertThat(invoiceEmpty, nullValue());
        assertThat(invoiceNotEmpty.getInvoiceNr(), is(2L));
        assertThat(invoiceNotEmpty.getPaymentStatus(), is(PaymentStatus.PAID));
    }

    @Test
    public void removeInvoiceSuccessfulTest() {
        invoice.setInvoiceNr(1L);

        tx.begin();
        invoiceDao.createOrUpdate(invoice);
        tx.commit();

        Invoice invoiceByNr = invoiceDao.findByInvoiceNr(1L);

        assertThat(invoiceByNr.getInvoiceNr(), is(1L));

        tx.begin();
        invoiceDao.deleteById(invoiceByNr.getId());
        tx.commit();

        Invoice invoiceEmpty = invoiceDao.findByInvoiceNr(1L);

        assertThat(invoiceEmpty, nullValue());
    }

    @Test
    public void findFirstInvoiceSuccessfulTest() {
        invoice.setInvoiceNr(1L);

        tx.begin();
        invoiceDao.createOrUpdate(invoice);
        tx.commit();

        Invoice invoiceByNr = invoiceDao.findByInvoiceNr(1L);

        assertThat(invoiceByNr.getInvoiceNr(), is(1L));
    }
}
