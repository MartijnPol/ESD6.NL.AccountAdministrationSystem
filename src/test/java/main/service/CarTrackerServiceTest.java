package main.service;

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

    private Car car;
    private Owner owner;
    private CarTracker carTracker;
    private CarTracker carTrackerUnused;

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
        carTrackerUnused = new CarTracker("2", "Test");

        owner.setFirstName("Jens");
        ownership.setOwner(owner);
        car.setCurrentOwnership(ownership);
        car.setLicensePlate("08-SK-PX");
        car.setCurrentCarTracker(carTracker);



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

    @Test
    public void findUnusedTrackerTest()
    {
        List<CarTracker> expectedResult = new ArrayList<>();
        expectedResult.add(carTrackerUnused);

        when(this.carTrackerService.findUnusedTrackers()).thenReturn(expectedResult);

        assertEquals(1, expectedResult.size());

    }

}