package main.service;

import main.dao.CarDao;
import main.dao.JPA;
import main.dao.RDWDao;
import main.dao.RDWFuelDao;
import main.domain.*;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Thom van de Pas on 8-3-2018
 */
@Stateless
public class CarService {

    @Inject
    @JPA
    private CarDao carDao;

    @Inject
    @JPA
    private RDWDao rdwDao;

    @Inject
    @JPA
    private RDWFuelDao rdwFuelDao;

    public CarService() {
    }

    public Car createOrUpdate(Car car) {
        RDW rdwData = this.rdwDao.findByLicensePlate(car.getLicensePlate());
        if (rdwData != null && car.getRdwData() == null) {
            car.setRdwData(rdwData);
        }

        RDWFuel rdwFuelData = this.rdwFuelDao.findByLicensePlate(car.getLicensePlate());
        if (rdwFuelData != null && car.getRdwFuelData() == null) {
            car.setRdwFuelData(rdwFuelData);
        }

        return this.carDao.createOrUpdate(car);
    }

    public void delete(Car car) {
        this.carDao.delete(car);
    }

    public void deleteById(Long id) {
        this.carDao.deleteById(id);
    }

    public Car findById(Long id) {
        return this.carDao.findById(id);
    }

    public List<Car> findByOwner(Owner owner) {
        return this.carDao.findByOwner(owner);
    }

    public List<Car> findAll() {
        return this.carDao.findAll();
    }

    public Car findByCarTrackerId(Long carTrackerId) {
        return this.carDao.findByCarTrackerId(carTrackerId);
    }

    /**
     * Deletes a car based on its licensePlate.
     *
     * @param licensePlate
     */
    public void deleteByLicensePlate(String licensePlate) {
        this.carDao.deleteByLicensePlate(licensePlate);
    }

    /**
     * Replaces all the cars with JsonObjects.
     *
     * @param cars the cars to be replaced with JsonObject.
     * @returns a List of JsonObjects.
     */
    public List<JsonObject> replaceObjects(List<Car> cars) {
        List<JsonObject> carsToJson = new ArrayList<JsonObject>();
        for (Car car : cars) {
            carsToJson.add(car.toJson());
        }
        return carsToJson;
    }

    /**
     * Removes Ownerships older than 5 years and returns a List of old Owners (till 5 years ago)
     *
     * @returns a List of past owners or an empty list when there are no past owners.
     */
    public List<Ownership> getAndUpdatePastOwnerships(Car car) {
        for (Ownership ownership : car.getPastOwnerships()) {
            if (isLongerThanFiveYearsAgo(ownership.getEndDate())) {
                car.getPastOwnerships().remove(ownership);
            }
        }
        this.createOrUpdate(car);

        return car.getPastOwnerships();
    }

    /**
     * Checks if a date is longer than five years ago.
     *
     * @param date the date to be checked.
     * @returns true if the date is longer ago, false if not.
     */
    private boolean isLongerThanFiveYearsAgo(Date date) {
        LocalDate inputDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fiveYearsAgo = LocalDate.now().minusYears(5);

        return inputDate.isBefore(fiveYearsAgo);
    }

    /**
     * Assign a Car to a new Ownership.
     * Previous cartracker is added to past ownerships and the new cartracker will be assigned.
     *
     * @param car          is the Car that gets a new Ownership.
     * @param newOwnership is the new Ownership
     * @returns the updated Car, if something went wrong null will be returned.
     */
    public Car assignToNewOwner(Car car, Ownership newOwnership) {
        if (car != null && newOwnership != null) {
            if (!car.getCurrentOwnership().equals(newOwnership)) {
                Ownership pastOwnership = car.getCurrentOwnership();
                if (pastOwnership != null) {
                    pastOwnership.setEndDate(new Date());
                    car.addPastOwnership(pastOwnership);
                }
                newOwnership.setStartDate(new Date());
                newOwnership.setOwner(newOwnership.getOwner());
                newOwnership.setCar(car);
                car.setCurrentOwnership(newOwnership);
            }
            this.createOrUpdate(car);
            return car;
        }
        return null;
    }


    public void setCarDao(CarDao carDao) {
        this.carDao = carDao;
    }

    public void setRdwDao(RDWDao rdwDao) {
        this.rdwDao = rdwDao;
    }
}
