package main.service;

import main.dao.CarDao;
import main.dao.JPA;
import main.domain.Car;
import main.domain.Owner;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import java.util.ArrayList;
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
    private RDWService rdwService;

    public CarService() {
    }

    public Car createOrUpdate(Car car) {
        car.setRdwData(rdwService.findByLicensePlate(car.getLicensePlate()));
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
}
