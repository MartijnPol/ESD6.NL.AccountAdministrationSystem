package main.service;

import main.dao.implementation.InvoiceDaoImpl;
import main.domain.Invoice;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Calendar;

/**
 * @author Thom van de Pas on 16-4-2018
 */
@RunWith(MockitoJUnitRunner.class)
public class InvoiceServiceTest {

    private InvoiceService invoiceService;

    private Invoice invoice;

    @Mock
    private InvoiceDaoImpl invoiceDao;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        invoiceService = new InvoiceService();
        invoiceService.setInvoiceDao(invoiceDao);

        this.invoice = new Invoice();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2018);
        calendar.set(Calendar.MONTH, Calendar.JULY);
        calendar.set(Calendar.DATE, 2);
        this.invoice.setPeriod(calendar.getTime());
    }

    @Test
    @Ignore
    public void getMonthNameTest() {
        String expectedResult = "juli";
        Assert.assertEquals(expectedResult, this.invoiceService.getMonthName(this.invoice.getPeriod()));

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2018);
        calendar.set(Calendar.MONTH, Calendar.APRIL);
        calendar.set(Calendar.DATE, 2);
        this.invoice.setPeriod(calendar.getTime());

        Assert.assertEquals("april", this.invoiceService.getMonthName(this.invoice.getPeriod()));
        Assert.assertNotEquals("december", this.invoiceService.getMonthName(this.invoice.getPeriod()));

    }
}
