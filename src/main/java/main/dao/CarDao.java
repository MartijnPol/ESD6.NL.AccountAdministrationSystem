package main.dao;


import main.domain.Car;
import main.domain.Owner;

import java.util.List;

/**
 * @author Thom van de Pas on 8-3-2018
 */
public interface CarDao extends GenericDao<Car> {

    List<Car> findByOwner(Owner owner);

    Car findByCarTrackerId(String carTrackerId);

    void deleteByLicensePlate(String licensePlate);

    Car findByLicensePlate(String licensePlate);
}
