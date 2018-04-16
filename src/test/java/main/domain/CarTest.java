package main.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


/**
 * @author Thom van de Pas on 8-3-2018
 */
public class CarTest {

    private Car car;
    private Owner owner;

    @Before
    public void setUp() {
        car = new Car();
        owner = new Owner();
    }

    @Test
    public void createCarTest() {
        Owner expOwner = owner;
        car.setOwner(owner);
        assertEquals(expOwner, car.getOwner());
    }
}
