package main.dao;


import main.domain.Car;
import main.domain.CarTracker;

import java.util.List;


public interface CarTrackerDao extends GenericDao<CarTracker> {

    CarTracker findById(String id);

    List<CarTracker> findUnusedTrackers ();

    CarTracker create(CarTracker carTracker);

    CarTracker update(CarTracker carTracker);
}
