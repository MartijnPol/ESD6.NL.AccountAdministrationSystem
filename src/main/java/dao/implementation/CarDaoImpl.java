package dao.implementation;

import dao.CarDao;
import dao.JPAAccountAdministration;
import domain.Car;
import domain.Owner;

import javax.ejb.Stateless;
import java.util.List;

/**
 * @author Thom van de Pas on 8-3-2018
 */
@Stateless
@JPAAccountAdministration
public class CarDaoImpl extends GenericDaoJPAImpl<Car> implements CarDao {

    public CarDaoImpl() {

    }

    public List<Car> findByOwner(Owner owner) {
        return getEntityManager().createNamedQuery("car.findByOwner", Car.class)
                .setParameter("owner", owner)
                .getResultList();
    }

    public Car findByCarTrackerId(Long carTrackerId) {
        return getEntityManager().createNamedQuery("car.findByCarTrackerId", Car.class)
                .setParameter("carTrackerId", carTrackerId)
                .getSingleResult();
    }

    public void deleteByLicensePlate(String licensePlate) {
        getEntityManager().createNamedQuery("car.deleteByLicencePlate", Car.class)
                .setParameter("licensePlate", licensePlate);
    }
}
