package main.dao;

import main.domain.Car;
import main.domain.CarTracker;
import main.domain.Owner;
import main.domain.Ownership;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.AdditionalMatchers;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CarDaoTest {

    private Car car;
    private List<Car> cars;
    private Ownership ownership;
    private Owner owner;
    private CarTracker carTracker;

    @Mock
    private CarDao carDao;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        car = new Car();
        ownership = new Ownership();
        owner = new Owner();
        carTracker = new CarTracker("1" , "Sony");


        owner.setFirstName("DuckDuckGo");
        ownership.setOwner(owner);
        car.setCurrentOwnership(ownership);
        car.setLicensePlate("08-SK-PX");
        car.setCurrentCartracker(carTracker);
        cars = new ArrayList<>();
        cars.add(car);
    }

    @Test
    public void createCarTest() {
        carDao.createOrUpdate(car);

        when(carDao.findByOwner(Matchers.eq(owner))).thenReturn(cars);
        when(carDao.findByOwner(AdditionalMatchers.not(Matchers.eq(owner)))).thenReturn(null);

        Owner differentOwner = new Owner();
        differentOwner.setFirstName("Google");

        List<Car> carsFromOwner = carDao.findByOwner(owner);
        List<Car> emptypCarsFromOwner = carDao.findByOwner(differentOwner);

        assertThat(carsFromOwner.get(0).getLicensePlate(), is("08-SK-PX"));
        assertThat(emptypCarsFromOwner, is(nullValue()));
    }

    @Test
    public void findByCarTrackerId() {
        carDao.createOrUpdate(car);

        when(carDao.findByCarTrackerId(Matchers.eq("1"))).thenReturn(car);
        when(carDao.findByCarTrackerId(AdditionalMatchers.not(Matchers.eq("1")))).thenReturn(null);

        Car carByCarTrackerId = carDao.findByCarTrackerId("1");
        Car emptyCarByCarTrackerId = carDao.findByCarTrackerId("2");

        assertThat(carByCarTrackerId.getLicensePlate(), is("08-SK-PX"));
        assertThat(carByCarTrackerId.getCurrentCartracker().getId(), is("1"));
        assertThat(emptyCarByCarTrackerId, is(nullValue()));
    }

    @Test
    public void deleteCarByLicensePlateTest() {
        carDao.createOrUpdate(car);

        when(carDao.findById(Matchers.eq(1L))).thenReturn(car);
        when(carDao.findById(AdditionalMatchers.not(Matchers.eq(1L)))).thenReturn(null);

        Car carById = carDao.findById(1L);
        Car emptyCarById = carDao.findById(2L);

        assertThat(carById.getLicensePlate(), is("08-SK-PX"));
        assertThat(emptyCarById, is(nullValue()));

        carDao.deleteByLicensePlate("08-SK-PX");

        when(carDao.findById(Matchers.eq(1L))).thenReturn(null);

        Car emptyCar = carDao.findById(1L);
        assertThat(emptyCar, is(nullValue()));
    }
}
