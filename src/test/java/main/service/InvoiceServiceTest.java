package main.service;

import main.dao.InvoiceDao;
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

import java.math.BigDecimal;
import java.math.RoundingMode;
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
    private RDW rdwData;
    private RDWFuel rdwFuelData;

    @Mock
    private InvoiceDao invoiceDao;

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
        rdwData.setZuinigheidslabel("A");
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
        rdwFuelData.setBrandstof_omschrijving("Waterstof");
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
}
