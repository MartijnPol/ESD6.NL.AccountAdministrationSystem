package main.dao;


import main.domain.Car;
import main.domain.CarTracker;
import main.domain.Owner;

import java.util.List;


public interface CarTrackerDao extends GenericDao<CarTracker> {

    CarTracker findById(String id);

    List<Car> findByCar(Car car);

    CarTracker create(CarTracker carTracker);

    CarTracker update(CarTracker carTracker);
}
