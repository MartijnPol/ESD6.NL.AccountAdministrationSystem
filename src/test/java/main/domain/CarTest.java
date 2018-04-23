package main.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


/**
 * @author Thom van de Pas on 8-3-2018
 */
public class CarTest {

    private Car car;
    private Ownership ownership;

    @Before
    public void setUp() {
        car = new Car();
        ownership = new Ownership();
    }

    @Test
    public void createCarTest() {
        Ownership expOwner = ownership;
        car.setCurrentOwnership(ownership);
        assertEquals(expOwner, car.getCurrentOwnership().getOwner());
    }
}
