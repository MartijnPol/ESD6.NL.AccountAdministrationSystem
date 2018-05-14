package main.dao;


import main.domain.Car;
import main.domain.CarTracker;
import main.domain.Owner;

import java.util.List;

/**
 * @author Thom van de Pas on 8-3-2018
 */
public interface CarTrackerDao extends GenericDao<CarTracker> {

    List<Car> findByCar(Car car);

}
