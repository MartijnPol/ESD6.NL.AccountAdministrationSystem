package main.dao;

import main.domain.Invoice;
import main.domain.enums.PaymentStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.AdditionalMatchers;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.is;

@RunWith(MockitoJUnitRunner.class)
public class InvoiceDaoTest {

    private Invoice invoice;

    @Mock
    private InvoiceDao invoiceDao;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        invoice = new Invoice();
    }

    @Test
    public void createInvoiceTest() {
        invoice.setInvoiceNr(1L);
        invoiceDao.createOrUpdate(invoice);

        when(invoiceDao.findByInvoiceNr(Matchers.eq(1L))).thenReturn(invoice);
        when(invoiceDao.findByInvoiceNr(AdditionalMatchers.not(Matchers.eq(1L)))).thenReturn(null);

        Invoice invoiceByNrFirst = invoiceDao.findByInvoiceNr(1L);
        Invoice invoiceByNrSecond = invoiceDao.findByInvoiceNr(2L);

        assertThat(invoiceByNrFirst.getInvoiceNr(), is(1L));
        assertThat(invoiceByNrSecond, is(nullValue()));
    }

    @Test
    public void updateInvoiceTest() {
        invoice.setInvoiceNr(1L);
        invoiceDao.createOrUpdate(invoice);

        when(invoiceDao.findByInvoiceNr(Matchers.eq(1L))).thenReturn(invoice);

        Invoice invoiceByNrFirst = invoiceDao.findByInvoiceNr(1L);
        assertThat(invoiceByNrFirst.getInvoiceNr(), is(1L));

        invoiceByNrFirst.setInvoiceNr(2L);
        invoiceByNrFirst.setPaymentStatus(PaymentStatus.PAID);

        when(invoiceDao.findByInvoiceNr(Matchers.eq(2L))).thenReturn(invoiceByNrFirst);
        when(invoiceDao.findByInvoiceNr(AdditionalMatchers.not(Matchers.eq(2L)))).thenReturn(null);

        Invoice invoiceNotEmpty = invoiceDao.findByInvoiceNr(2L);
        Invoice invoiceEmpty = invoiceDao.findByInvoiceNr(1L);

        assertThat(invoiceNotEmpty.getInvoiceNr(), is(2L));
        assertThat(invoiceEmpty, is(nullValue()));
    }

    @Test
    public void deleteInvoiceTest() {
        invoice.setInvoiceNr(1L);
        invoiceDao.createOrUpdate(invoice);

        when(invoiceDao.findByInvoiceNr(Matchers.eq(1L))).thenReturn(invoice);

        Invoice invoiceByNr = invoiceDao.findByInvoiceNr(1L);
        assertThat(invoiceByNr.getInvoiceNr(), is(1L));

        invoiceDao.delete(invoiceByNr);

        when(invoiceDao.findByInvoiceNr(Matchers.eq(1L))).thenReturn(null);

        Invoice invoiceEmpty = invoiceDao.findByInvoiceNr(1L);
        
        assertThat(invoiceEmpty, is(nullValue()));
    }
}
