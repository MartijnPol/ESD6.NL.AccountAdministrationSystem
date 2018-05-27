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
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CarTrackerDaoTest {

    private CarTracker carTracker;
    private List<CarTracker> carTrackers;
    private Owner owner;
    private Car car;

    @Mock
    private CarTrackerDao carTrackerDao;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        carTracker = new CarTracker();
        car = new Car();
        Ownership ownership = new Ownership();
        owner = new Owner();
        carTracker = new CarTracker("1", "Sony");


        owner.setFirstName("DuckDuckGo");
        ownership.setOwner(owner);
        car.setCurrentOwnership(ownership);
        car.setLicensePlate("08-SK-PX");
        car.setCurrentCarTracker(carTracker);
    }

    @Test
    public void findByIdTest() {
        carTrackerDao.create(carTracker);

        when(carTrackerDao.findById(Matchers.eq("1"))).thenReturn(carTracker);
        when(carTrackerDao.findById(AdditionalMatchers.not(Matchers.eq("1")))).thenReturn(null);

        CarTracker carTrackerById = carTrackerDao.findById("1");
        CarTracker emptycarTrackerById = carTrackerDao.findById("2");

        assertThat(carTrackerById.getManufacturer(), is("Sony"));
        assertThat(carTrackerById.getId(), is("1"));
        assertThat(emptycarTrackerById, is(nullValue()));
    }
}