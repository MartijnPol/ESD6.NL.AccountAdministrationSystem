package main.service;

import main.dao.InvoiceDao;
import main.dao.implementation.InvoiceDaoImpl;
import main.dao.implementation.OwnershipDaoImpl;
import main.dao.implementation.TariffDaoImpl;
import main.domain.*;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.AdditionalMatchers;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
    private RDW rdwData;
    private RDWFuel rdwFuelData;

    @Mock
    private InvoiceDao invoiceDao;

    @Mock
    private TariffService tariffService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        invoiceService = new InvoiceService();
        this.ownership = new Ownership();
        this.car = new Car();
        this.tariff = new Tariff();
        this.invoice = new Invoice();
        this.rdwData = new RDW();
        this.rdwFuelData = new RDWFuel();
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.YEAR, 2018);
        calendar.set(Calendar.MONTH, Calendar.JULY);
        calendar.set(Calendar.DATE, 2);

        this.rdwData.setZuinigheidslabel("E");
        this.rdwFuelData.setBrandstof_omschrijving("Benzine");
        this.car.setCurrentOwnership(ownership);
        this.car.setRdwData(rdwData);
        this.car.setRdwFuelData(rdwFuelData);
        this.ownership.setCar(car);
        tariff.setTariffInEuro(0.07);
        tariff.addCarLabel("E", 10.0);
        tariff.addCarLabel("F", 20.0);
        tariff.addCarFuel("Benzine", 10.0);
        tariff.addCarFuel("Diesel", 20.0);
        this.invoice.setPeriod(calendar.getTime());
        this.invoice.setInvoiceNr(1L);
        this.invoiceService.setTariffService(tariffService);
        this.invoiceService.setInvoiceDao(invoiceDao);
    }

    @Test
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
    public void getEconomicalAdditionTest() {
        Double expectedResult = 10.0;
        Double unexpectedResult = 20.0;
        Double expectedResultNothingFound = 0.0;

        double economicalAddition = this.invoiceService.getEconomicalAddition(this.car, this.tariff);

        Assert.assertEquals(expectedResult, economicalAddition, 0);
        Assert.assertNotEquals(unexpectedResult, economicalAddition, 0);

        RDW rdwData = this.car.getRdwData();
        rdwData.setZuinigheidslabel("");
        this.car.setRdwData(rdwData);

        double economicalAdditionEmpty = this.invoiceService.getEconomicalAddition(this.car, this.tariff);

        Assert.assertEquals(expectedResultNothingFound, economicalAdditionEmpty, 0);
    }

    @Test
    public void getCarFuelAdditionTest() {
        Double expectedResult = 10.0;
        Double unexpectedResult = 20.0;
        Double expectedResultNothingFound = 0.0;

        double carFuelAddition = this.invoiceService.getCarFuelAddition(this.car, this.tariff);

        Assert.assertEquals(expectedResult, carFuelAddition, 0);
        Assert.assertNotEquals(unexpectedResult, carFuelAddition, 0);

        RDWFuel rdwFuelData = this.car.getRdwFuelData();
        rdwFuelData.setBrandstof_omschrijving("");
        this.car.setRdwFuelData(rdwFuelData);

        double carFuelAdditionEmpty = this.invoiceService.getCarFuelAddition(this.car, this.tariff);

        Assert.assertEquals(expectedResultNothingFound, carFuelAdditionEmpty, 0);
    }

    @Test
    public void preventNegativeAmountTest() {
        BigDecimal expectedResult = new BigDecimal(0.77).setScale(2, RoundingMode.HALF_UP);
        BigDecimal unexpectedResult = new BigDecimal(-0.77).setScale(2, RoundingMode.HALF_UP);
        BigDecimal expectedResultZero = BigDecimal.ZERO;
        BigDecimal inputPositive = new BigDecimal(0.77);
        BigDecimal inputNegative = new BigDecimal(-0.77);

        BigDecimal preventedNegativeAmount = this.invoiceService.preventNegativeAmount(inputPositive);

        Assert.assertEquals(expectedResult, preventedNegativeAmount);
        Assert.assertNotEquals(unexpectedResult, preventedNegativeAmount);

        BigDecimal preventedNegativeAmountZero = this.invoiceService.preventNegativeAmount(inputNegative);

        Assert.assertEquals(expectedResultZero, preventedNegativeAmountZero);
    }

    @Test
    public void generateTotalInvoiceAmountTest() {
        BigDecimal expectedResult = new BigDecimal(1.47).setScale(2, RoundingMode.HALF_UP);
        BigDecimal unexpectedResult = new BigDecimal(-1.47).setScale(2, RoundingMode.HALF_UP);
        BigDecimal expectedResultZero = BigDecimal.ZERO;

        when(this.tariffService.findById(1L)).thenReturn(tariff);

        BigDecimal generatedTotalInvoiceAmount = this.invoiceService.generateTotalInvoiceAmount(this.ownership);

        Assert.assertEquals(expectedResult, generatedTotalInvoiceAmount);
        Assert.assertNotEquals(unexpectedResult, generatedTotalInvoiceAmount);

        when(this.tariffService.findById(1L)).thenReturn(null);

        BigDecimal generatedTotalInvoiceAmountZero = this.invoiceService.generateTotalInvoiceAmount(this.ownership);

        Assert.assertEquals(expectedResultZero, generatedTotalInvoiceAmountZero);
    }

    @Test
    public void findByIdTest() {
        when(this.invoiceService.findById(Matchers.eq(1L))).thenReturn(invoice);
        when(this.invoiceService.findById(AdditionalMatchers.not(Matchers.eq(1L)))).thenReturn(null);

        Invoice invoiceFoundByIdNull = this.invoiceService.findById(null);
        Invoice invoiceFoundById = this.invoiceService.findById(1L);
        Invoice invoiceFoundByIdEmpty = this.invoiceService.findById(2L);

        Assert.assertEquals(null, invoiceFoundByIdNull);
        Assert.assertEquals(invoice, invoiceFoundById);
        Assert.assertEquals(null, invoiceFoundByIdEmpty);
    }

    @Test
    public void findAllTest() {
        List<Invoice> expectedResult = new ArrayList<>();
        expectedResult.add(invoice);

        when(this.invoiceService.findAll()).thenReturn(expectedResult);

        assertEquals(1, expectedResult.size());
    }

    @Test
    public void findByInvoiceNrTest() {
        when(this.invoiceService.findByInvoiceNr(Matchers.eq(1L))).thenReturn(invoice);
        when(this.invoiceService.findByInvoiceNr(AdditionalMatchers.not(Matchers.eq(1L)))).thenReturn(null);

        Invoice invoiceFoundByInvoiceNr = this.invoiceService.findByInvoiceNr(1L);
        Invoice invoiceFoundByInvoiceNrEmpty = this.invoiceService.findByInvoiceNr(2L);
        Invoice invoiceFoundByInvoiceNrNull = this.invoiceService.findByInvoiceNr(null);

        Assert.assertEquals(invoice, invoiceFoundByInvoiceNr);
        Assert.assertEquals(null, invoiceFoundByInvoiceNrEmpty);
        Assert.assertEquals(null, invoiceFoundByInvoiceNrNull);
    }

    @Test
    public void findFirstInvoiceTest() {
        when(this.invoiceService.createOrUpdate(invoice)).thenReturn(invoice);
        this.invoiceService.createOrUpdate(invoice);

        Invoice invoiceSecond = new Invoice();
        invoiceSecond.setInvoiceNr(2L);

        when(this.invoiceService.createOrUpdate(invoiceSecond)).thenReturn(invoiceSecond);
        invoiceDao.create(invoiceSecond);

        when(this.invoiceService.findFirstInvoice()).thenReturn(invoice);

        Invoice firstInvoiceFound = this.invoiceService.findFirstInvoice();

        Assert.assertEquals(invoice, firstInvoiceFound);
    }

    @Test
    public void deleteTest() {
        when(this.invoiceService.createOrUpdate(invoice)).thenReturn(invoice);
        this.invoiceService.createOrUpdate(invoice);

        when(this.invoiceService.findByInvoiceNr(Matchers.eq(1L))).thenReturn(invoice);

        Invoice invoiceByNr = this.invoiceService.findByInvoiceNr(1L);
        assertThat(invoiceByNr.getInvoiceNr(), CoreMatchers.is(1L));

        this.invoiceService.delete(invoiceByNr);

        when(this.invoiceService.findByInvoiceNr(Matchers.eq(1L))).thenReturn(null);

        Invoice invoiceEmpty = this.invoiceService.findByInvoiceNr(1L);

        assertThat(invoiceEmpty, CoreMatchers.is(nullValue()));
    }
}
