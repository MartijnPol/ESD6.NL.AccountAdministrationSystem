package main.service;

import com.mashape.unirest.http.exceptions.UnirestException;
import main.dao.InvoiceDao;
import main.domain.*;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.AdditionalMatchers;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

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
        RDW rdwData = new RDW();
        RDWFuel rdwFuelData = new RDWFuel();
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.YEAR, 2018);
        calendar.set(Calendar.MONTH, Calendar.JULY);
        calendar.set(Calendar.DATE, 2);

        rdwData.setZuinigheidslabel("E");
        rdwFuelData.setBrandstof_omschrijving("Benzine");
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

        Assert.assertNull(invoiceFoundByIdNull);
        Assert.assertEquals(invoice, invoiceFoundById);
        Assert.assertNull(invoiceFoundByIdEmpty);
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
        Assert.assertNull(invoiceFoundByInvoiceNrEmpty);
        Assert.assertNull(invoiceFoundByInvoiceNrNull);
    }

    @Test
    public void findFirstInvoiceTest() {
        when(this.invoiceService.createOrUpdate(invoice)).thenReturn(invoice);
        this.invoiceService.createOrUpdate(invoice);

        Invoice invoiceSecond = new Invoice();
        invoiceSecond.setInvoiceNr(2L);

        when(this.invoiceService.createOrUpdate(invoiceSecond)).thenReturn(invoiceSecond);
        invoiceDao.createOrUpdate(invoiceSecond);

        when(this.invoiceService.findFirstInvoice()).thenReturn(invoice);

        Invoice firstInvoiceFound = this.invoiceService.findFirstInvoice();

        Assert.assertEquals(invoice, firstInvoiceFound);
    }

    @Test
    public void getNextInvoiceNr() {
        Long expectedResult = 20184L;
        Long expectedResultNew = 20181L;
        Long unexpectedResult = 20185L;
        Long expectedResultNull = null;

        Long nextInvoiceNr = this.invoiceService.getNextInvoiceNr(2018003L);
        Long nextInvoiceNrNew = this.invoiceService.getNextInvoiceNr(2018L);
        Long nextInvoiceNrNull = this.invoiceService.getNextInvoiceNr(null);

        Assert.assertEquals(expectedResult, nextInvoiceNr);
        Assert.assertEquals(expectedResultNew, nextInvoiceNrNew);
        Assert.assertNotEquals(unexpectedResult, nextInvoiceNr);
        Assert.assertEquals(expectedResultNull, nextInvoiceNrNull);
    }

    @Test
    public void findLastInvoiceNr() {
        Long expectedResult = 20184L;
        Long unexpectedResult = 20185L;

        invoice.setInvoiceNr(20184L);
        Invoice invoiceSecond = new Invoice();
        invoiceSecond.setInvoiceNr(20185L);

        this.invoiceService.createOrUpdate(invoice);
        this.invoiceService.createOrUpdate(invoiceSecond);

        when(this.invoiceService.findLastInvoiceNr()).thenReturn(20184L);

        Long lastInvoiceNr = this.invoiceService.findLastInvoiceNr();

        Assert.assertEquals(expectedResult, lastInvoiceNr);
        Assert.assertNotEquals(unexpectedResult, lastInvoiceNr);
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

    @Test
    public void sortMovementsByDayTest() {
        CarTrackerResponse carTrackerResponse = new CarTrackerResponse();
        carTrackerResponse.setId("NLD1");
        carTrackerResponse.setTotalRules(3L);

        Calendar calendarMay21 = Calendar.getInstance();
        calendarMay21.set(Calendar.YEAR, 2018);
        calendarMay21.set(Calendar.MONTH, Calendar.MAY);
        calendarMay21.set(Calendar.DATE, 21);

        Calendar calendarMay22 = Calendar.getInstance();
        calendarMay22.set(Calendar.YEAR, 2018);
        calendarMay22.set(Calendar.MONTH, Calendar.MAY);
        calendarMay22.set(Calendar.DATE, 22);

        CarTrackerRuleResponse carTrackerRuleResponseFirst = new CarTrackerRuleResponse();
        CarTrackerRuleResponse carTrackerRuleResponseSecond = new CarTrackerRuleResponse();
        CarTrackerRuleResponse carTrackerRuleResponseThird = new CarTrackerRuleResponse();

        carTrackerRuleResponseFirst.setDate(calendarMay22.getTime());
        carTrackerRuleResponseSecond.setDate(calendarMay21.getTime());
        carTrackerRuleResponseThird.setDate(calendarMay22.getTime());

        carTrackerRuleResponseFirst.setId(1L);
        carTrackerRuleResponseSecond.setId(2L);
        carTrackerRuleResponseThird.setId(3L);

        carTrackerResponse.setCarTrackerRules(Arrays.asList(carTrackerRuleResponseFirst, carTrackerRuleResponseSecond, carTrackerRuleResponseThird));

        HashMap<Date, List<CarTrackerRuleResponse>> sortedMovementsByDay = this.invoiceService.sortMovementsByDay(carTrackerResponse);

        assertThat(sortedMovementsByDay.get(calendarMay22.getTime()).size(), is(2));
        assertThat(sortedMovementsByDay.get(calendarMay21.getTime()).size(), is(1));
        assertThat(sortedMovementsByDay.get(calendarMay22.getTime()).get(0).getId(), is(1L));
        assertThat(sortedMovementsByDay.get(calendarMay21.getTime()).get(0).getId(), is(2L));
        assertThat(sortedMovementsByDay.get(calendarMay22.getTime()).get(1).getId(), is(3L));
    }

    @Test
    public void getLatLonPathTest() {
        String expectedResult = "-35.27801,149.12958|-35.28032,149.12907";
        String expectedResultEmpty = "";

        List<CarTrackerRuleResponse> rulesFilled = new ArrayList<>();
        List<CarTrackerRuleResponse> rulesEmpty = new ArrayList<>();
        CarTrackerRuleResponse carTrackerRuleResponseFirst = new CarTrackerRuleResponse();
        CarTrackerRuleResponse carTrackerRuleResponseSecond = new CarTrackerRuleResponse();

        carTrackerRuleResponseFirst.setLat(-35.27801);
        carTrackerRuleResponseFirst.setLon(149.12958);

        carTrackerRuleResponseSecond.setLat(-35.28032);
        carTrackerRuleResponseSecond.setLon(149.12907);

        rulesFilled.add(carTrackerRuleResponseFirst);
        rulesFilled.add(carTrackerRuleResponseSecond);

        String latLonPath = this.invoiceService.getLatLonPath(rulesFilled);
        String latLonPathEmpty = this.invoiceService.getLatLonPath(rulesEmpty);

        assertThat(latLonPath, is(expectedResult));
        assertThat(latLonPathEmpty, is(expectedResultEmpty));
    }

    @Test
    public void extractRoadTypeTest() {
        String expectedResult = "A";
        String expectedResultEmpty = "";

        String roadType = this.invoiceService.extractRoadType("A2");
        String roadTypeEmpty = this.invoiceService.extractRoadType("");
        String roadTypeStrangeInput = this.invoiceService.extractRoadType("A22A");

        assertThat(roadType, is(expectedResult));
        assertThat(roadTypeEmpty, is(expectedResultEmpty));
        assertThat(roadTypeStrangeInput, is(expectedResult));
    }
}
