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
    private Ownership ownership;
    private List<Car> cars;

    @Before
    public void setUp() {
        owner = new Owner();
        owner.setFirstName("DuckDuckGo");
        ownership = new Ownership();
        ownership.setOwner(owner);
        cars = new ArrayList<Car>();
    }

    @Test
    public void setCarsToOwnerTest() {
        int expResult = 2;
        String expName = "DuckDuckGo";
        cars.add(new Car("32-ABC-1", ownership));
        cars.add(new Car("12-CBA-9", ownership));


        assertEquals(expResult, cars.size());
        assertEquals(expName, cars.get(0).getCurrentOwnership().getOwner().getFirstName());
        assertEquals(expName, cars.get(1).getCurrentOwnership().getOwner().getFirstName());
    }
}
