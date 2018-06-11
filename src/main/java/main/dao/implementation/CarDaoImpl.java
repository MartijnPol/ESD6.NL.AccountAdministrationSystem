package main.dao.implementation;

import main.dao.CarDao;
import main.dao.JPA;
import main.domain.Car;
import main.domain.Owner;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Thom van de Pas on 8-3-2018
 */
@Stateless
@JPA
public class CarDaoImpl extends GenericDaoJPAImpl<Car> implements CarDao {

    public CarDaoImpl() {
    }

    public List<Car> findByOwner(Owner owner) {
        return getEntityManager().createNamedQuery("car.findByOwner", Car.class)
                .setParameter("owner", owner)
                .getResultList();
    }

    @Override
    public Car findByCarTrackerId(String carTrackerId) {
        return null;
    }

    public Car findByCarTrackerId(Long carTrackerId) {
        TypedQuery<Car> query = getEntityManager().createNamedQuery("car.findByCarTrackerId", Car.class)
                .setParameter("carTrackerId", carTrackerId);

        return oneResult(query);
    }

    public void deleteByLicensePlate(String licensePlate) {
        getEntityManager().createNamedQuery("car.deleteByLicencePlate", Car.class)
                .setParameter("licensePlate", licensePlate);
    }

    public Car findByLicensePlate(String licensePlate) {
        TypedQuery<Car> query = getEntityManager().createNamedQuery("car.findByLicensePlate", Car.class)
                .setParameter("licensePlate", licensePlate);

        return oneResult(query);
    }
}
