package main.service;

import main.dao.implementation.InvoiceDaoImpl;
import main.dao.implementation.OwnershipDaoImpl;
import main.dao.implementation.TariffDaoImpl;
import main.domain.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Calendar;

/**
 * @author Thom van de Pas on 16-4-2018
 */
@RunWith(MockitoJUnitRunner.class)
public class InvoiceServiceTest {

    private InvoiceService invoiceService;

    private Invoice invoice;
    private Ownership ownership;
    private Car car;
    private Tariff tariff;

    @Mock
    private InvoiceDaoImpl invoiceDao;

    @Mock
    private TariffService tariffService;

    @Mock
    private RDW rdwData;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        invoiceService = new InvoiceService();
        invoiceService.setInvoiceDao(invoiceDao);
        invoiceService.setTariffService(tariffService);

        this.ownership = new Ownership();
        this.car = new Car();
        this.car.setRdwData(rdwData);
        this.car.setCurrentOwnership(ownership);
        this.ownership.setCar(car);
        this.tariff = new Tariff();
        tariff.setTariffInEuro(0.07);
        tariff.addCarLabel("E", 10.0);

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

    @Test
    @Ignore
    public void generateTotalInvoiceAmount() {
        Double expectedResult = 0.77;

        when(tariffService.findById(1L)).thenReturn(tariff);
        when(rdwData.getZuinigheidslabel()).thenReturn("E");

        double amount = invoiceService.generateTotalInvoiceAmount(ownership);
        Assert.assertEquals(expectedResult, amount, 0.0);
    }
}
