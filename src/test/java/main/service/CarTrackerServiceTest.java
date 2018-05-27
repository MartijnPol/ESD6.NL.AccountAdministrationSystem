package main.service;

import main.dao.RDWFuelDao;
import main.dao.implementation.CarDaoImpl;
import main.dao.implementation.RDWDaoImpl;
import main.domain.*;
import org.junit.Assert;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CarTrackerServiceTest {

    private CarService carService;
    private Car car;
    private Ownership newOwnership;
    private Ownership currentOwnership;
    private Owner owner;
    private CarTracker carTracker;

    @Mock
    private CarDaoImpl carDao;
    @Mock
    private RDWDaoImpl rdwDao;
    @Mock
    private RDWFuelDao rdwFuelDao;
    @Mock
    private CarService carServiceMock;
    @Mock
    private CarTrackerService carTrackerService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        carTracker = new CarTracker();
        car = new Car();
        Ownership ownership = new Ownership();
        owner = new Owner();
        carTracker = new CarTracker("1", "Apple");

        owner.setFirstName("Jens");
        ownership.setOwner(owner);
        car.setCurrentOwnership(ownership);
        car.setLicensePlate("08-SK-PX");
        car.setCurrentCarTracker(carTracker);
    }

    @Test
    public void createOrUpdateTest() {

    }

    @Test
    public void findByIdTest() {
        when(this.carTrackerService.findById(Matchers.eq("1"))).thenReturn(carTracker);
        when(this.carTrackerService.findById(AdditionalMatchers.not(Matchers.eq("1")))).thenReturn(null);

        CarTracker carTrackerFoundByIdNull = this.carTrackerService.findById(null);
        CarTracker carTrackerFoundById = this.carTrackerService.findById("1");
        CarTracker carTrackerFoundByIdEmpty = this.carTrackerService.findById("2");

        Assert.assertNull(carTrackerFoundByIdNull);
        Assert.assertEquals(carTracker, carTrackerFoundById);
        Assert.assertNull(carTrackerFoundByIdEmpty);
    }

    @Test
    public void findAllTest() {
        List<CarTracker> expectedResult = new ArrayList<>();
        expectedResult.add(carTracker);

        when(this.carTrackerService.findAll()).thenReturn(expectedResult);

        assertEquals(1, expectedResult.size());
    }

}