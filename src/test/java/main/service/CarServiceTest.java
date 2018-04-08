package main.service;

import main.dao.CarDao;
import main.domain.Address;
import main.domain.Car;
import main.domain.Owner;
import main.domain.Ownership;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Thom van de Pas on 6-4-2018
 */
@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest {

    private CarService carService;

    private Car car;

    private Owner currentOwner;
    private Owner newOwner;
    private Ownership newOwnership;
    private Ownership currentOwnership;

    @Mock
    private CarDao carDao;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        carService = new CarService();
        carService.setCarDao(carDao);

        currentOwner = new Owner("Thom", "van de Pas", new Date(), new Address());
        currentOwnership = new Ownership();
        currentOwner.addOwnership(currentOwnership);
        currentOwnership.setOwner(currentOwner);
        car = new Car("FF-01-RK", currentOwner);


        newOwner = new Owner("Martijn", "van der Pol", new Date(), new Address());
        newOwnership = new Ownership();
        newOwner.addOwnership(newOwnership);
        newOwnership.setOwner(newOwner);
    }

    @Test
    @Ignore
    public void getAndUpdatePastOwnerships() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2012);
        calendar.set(Calendar.MONTH, Calendar.APRIL);
        calendar.set(Calendar.DATE, 3);
        Ownership test = new Ownership();
        test.setEndDate(calendar.getTime());
        Ownership test2 = new Ownership();

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.YEAR, 2016);
        calendar2.set(Calendar.MONTH, Calendar.APRIL);
        calendar2.set(Calendar.DATE, 3);
        test2.setEndDate(calendar2.getTime());
        car.addPastOwnership(test);
        car.addPastOwnership(test2);

        car.setCurrentOwnership(currentOwnership);
        car.setCarTrackerId(1L);

        List<Ownership> theOwnerships = car.getPastOwnerships();
        assertThat(theOwnerships.size(), is(2));

        when(carService.getAndUpdatePastOwnerships(car)).thenReturn(car.getPastOwnerships());
        List<Ownership> pastOwnerships = carService.getAndUpdatePastOwnerships(car);
        verify(carDao, Mockito.times(1)).createOrUpdate(car);
        assertThat(pastOwnerships.size(), is(1));
    }

}