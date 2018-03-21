package dao;

import domain.Car;
import domain.Owner;

import java.util.List;

/**
 * @author Thom van de Pas on 8-3-2018
 */
public interface CarDao extends GenericDao<Car> {

    List<Car> findByOwner(Owner owner);

    Car findByCarTrackerId(Long carTrackerId);

    void deleteByLicensePlate(String licensePlate);
}
