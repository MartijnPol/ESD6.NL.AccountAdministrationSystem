package main.dao;

import main.dao.implementation.CarDaoImpl;
import main.domain.Address;
import main.domain.Car;
import main.domain.Owner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import util.DatabaseCleaner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * @author Thom van de Pas on 8-3-2018
 */
public class CarDaoTest {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("AccountAdministrationTestPU");
    private EntityManager em;
    private EntityTransaction tx;
    private CarDaoImpl carDao;
    private Car car = null;
    private Owner owner = null;

    public CarDaoTest() {
    }

    @Before
    public void setUp() {
        try {
            new DatabaseCleaner(emf.createEntityManager()).clean();
        } catch (SQLException ex) {
            Logger.getLogger(CarDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        em = emf.createEntityManager();
        tx = em.getTransaction();

        carDao = new CarDaoImpl();
        carDao.setEntityManager(em);
        owner = new Owner("Herman", "de Schermman", new Date(), new Address());
        car = new Car("HT-328-W", owner);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void saveCarSuccessfulTest() {
        Integer expResult = 1;
        tx.begin();
        carDao.create(car);
        tx.commit();
        tx.begin();
        int size = carDao.findAll().size();
        assertThat(size, is(expResult));
    }

    @Test
    public void findByOwnerSuccessfulTest() {
        Owner testOwner = new Owner("Pietje", "Bell", new Date(), new Address());
        Car test = new Car("PT-EI-82", testOwner);
        Car test2 = new Car("SR-206-P", owner);
        tx.begin();
        carDao.create(car);
        carDao.create(test);
        carDao.create(test2);
        List<Car> cars = carDao.findByOwner(owner);
        List<Car> otherCars = carDao.findByOwner(testOwner);
        tx.commit();
        assertEquals(2L, cars.size());
        assertEquals(1L, otherCars.size());
    }

//    @Test
//    public void updateCarSuccessfulTest(){
//        Integer expResult = 1;
//        Owner testOwner = new Owner("Pietje", "Bell", new Date());
//        tx.begin();
//        carDao.create(car);
//        car.setOwner(testOwner);
//        carDao.update(car);
//        List<Car> result = carDao.findByOwner(testOwner);
//        tx.commit();
//        assertThat(result.size(), is(expResult));
//    }

    @Test
    public void removeCarSuccessfulTest() {
        Car test = new Car("PT-EI-82", owner);
        tx.begin();
        carDao.create(car);
        carDao.create(test);
        List<Car> cars = carDao.findByOwner(owner);
        tx.commit();

        assertThat(cars.size(), is(2));

        tx.begin();
        carDao.delete(car);
        carDao.deleteById(test.getId());
        cars = carDao.findByOwner(owner);
        tx.commit();

        assertThat(cars.size(), is(0));
    }
}
