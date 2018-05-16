package main.service;

import main.dao.CarTrackerDao;
import main.dao.JPA;
import main.domain.Car;
import main.domain.CarTracker;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;


@Stateless
public class CarTrackerService {

    @Inject
    @JPA
    public CarTrackerDao carTrackerDao;


    /**
     * Empty constructor
     */
    public CarTrackerService() {
    }

    /**
     * Function to save a given CarTracker object in the database
     *
     * @param carTracker is the CarTracker object that needs to be saved
     */
    private CarTracker create(CarTracker carTracker) {
        return this.carTrackerDao.create(carTracker);
    }

    /**
     * Function to update a given CarTracker object in the database
     *
     * @param carTracker is the CarTracker object that needs to be updated
     */
    private CarTracker update(CarTracker carTracker) {
        return this.carTrackerDao.update(carTracker);
    }


    public CarTracker createOrUpdate(CarTracker carTracker) {
        if (carTracker.getId() == null) {
            return this.create(carTracker);
        } else {
            return this.update(carTracker);
        }
    }
    /**
     * Function to find CarTracker data by it's Id
     *
     * @param id is the given Id that belongs to a CarTracker data object
     * @return the found object belonging to the given Id
     */
    public CarTracker findById(Long id) {
        return this.carTrackerDao.findById(id);
    }

    public CarTracker findById(String id) {
        return this.carTrackerDao.findById(id);
    }

    /**
     * Function to get all CarTrackerData stored in the database
     *
     * @return A list of all CarTrackerData stored in the database
     */
    public List<CarTracker> getCarTrackers() {
        return carTrackerDao.findAll();
    }

    public List<CarTracker> findAll() {
        return this.carTrackerDao.findAll();
    }

    public void setCarTrackerDao(CarTrackerDao carTrackerDao) {
        this.carTrackerDao = carTrackerDao;
    }
}
