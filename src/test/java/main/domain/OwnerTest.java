package main.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Thom van de Pas on 8-3-2018
 */
public class OwnerTest {

    private Owner owner;
    private List<Car> cars;

    @Before
    public void setUp() {
        owner = new Owner();
        cars = new ArrayList<Car>();
    }

//    @Test
//    public void setCarsToOwnerTest() {
//        int expResult = 2;
//        cars.add(new Car("32-ABC-1", owner));
//        cars.add(new Car("12-CBA-9", owner));
//
//        owner.setCars(cars);
//
//        assertEquals(expResult, cars.size());
//        assertEquals(expResult, owner.getCars().size());
//    }
}
