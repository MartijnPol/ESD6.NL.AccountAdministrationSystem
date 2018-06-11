package main.service;

import com.mashape.unirest.http.exceptions.UnirestException;
import main.dao.RDWFuelDao;
import main.dao.implementation.CarDaoImpl;
import main.dao.implementation.RDWDaoImpl;
import main.domain.*;
import org.hamcrest.core.IsNull;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.isNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Thom van de Pas on 6-4-2018
 */
@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest {

    private CarService carService;
    private Car car;

    private Ownership newOwnership;
    private Ownership currentOwnership;
    private CarTracker carTracker;

    @Mock
    private CarDaoImpl carDao;
    @Mock
    private RDWDaoImpl rdwDao;
    @Mock
    private RDWFuelDao rdwFuelDao;
    @Mock
    private CarService carServiceMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        carService = new CarService();
        carService.setCarDao(carDao);
        carService.setRdwDao(rdwDao);
        carService.setRdwFuelDao(rdwFuelDao);

        Owner currentOwner = new Owner("Thom", "van de Pas", new Date(), new Address());
        currentOwnership = new Ownership();
        currentOwner.addOwnership(currentOwnership);
        currentOwnership.setOwner(currentOwner);
        currentOwnership.setId(1L);
        car = new Car("FF-01-RK", currentOwnership);
        car.setCurrentOwnership(currentOwnership);


        Owner newOwner = new Owner("Martijn", "van der Pol", new Date(), new Address());
        newOwnership = new Ownership();
        newOwner.addOwnership(newOwnership);
        newOwnership.setOwner(newOwner);
        newOwnership.setId(2L);

        carTracker = new CarTracker("1" , "Sony");
    }

    @Test
    public void getAndUpdatePastOwnershipsTest() {
        List<Ownership> ownerships = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2012);
        calendar.set(Calendar.MONTH, Calendar.APRIL);
        calendar.set(Calendar.DATE, 3);
        Ownership test = new Ownership();
        test.setEndDate(calendar.getTime());

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.YEAR, 2016);
        calendar2.set(Calendar.MONTH, Calendar.APRIL);
        calendar2.set(Calendar.DATE, 3);
        Ownership test2 = new Ownership();
        test2.setEndDate(calendar2.getTime());

        ownerships.add(test);
        ownerships.add(test2);
        car.addMultiplePastOwnerships(ownerships);

        car.setCurrentOwnership(currentOwnership);
        car.setCurrentCarTracker(carTracker);

        List<Ownership> theOwnerships = car.getPastOwnerships();
        assertThat(theOwnerships.size(), is(2));

        when(carServiceMock.getAndUpdatePastOwnerships(car)).thenReturn(car.getPastOwnerships());
        List<Ownership> pastOwnerships = carService.getAndUpdatePastOwnerships(car);
        verify(carDao, Mockito.times(1)).createOrUpdate(car);
        assertThat(pastOwnerships.size(), is(1));
    }

    @Test
    public void assignCarToNewOwnerTest() {
        assertThat(car.getCurrentOwnership(), is(this.currentOwnership));

        this.car = carService.assignToNewOwner(car, this.newOwnership);
        Ownership newOwnership = this.car.getCurrentOwnership();
        Assert.assertEquals(this.newOwnership, newOwnership);
    }

    @Test
    public void findByLicensePlate() {
        when(carServiceMock.findByLicensePlate(car.getLicensePlate())).thenReturn(car);
        Car foundCar = carServiceMock.findByLicensePlate("FF-01-RK");
        assertThat(foundCar, is(car));
    }

    @Test
    public void findCarMovementsTest() throws UnirestException {
        CarTrackerResponse carTrackerResponseEmpty = carService.findCarMovements("");
        CarTrackerResponse carTrackerResponse = carService.findCarMovements("NLD1");

        assertThat(carTrackerResponseEmpty, is(IsNull.nullValue()));
        assertThat(carTrackerResponse.getId(), is("NLD1"));
        assertThat(carTrackerResponse.getManufacturer(), is("ASUS"));
    }
}
