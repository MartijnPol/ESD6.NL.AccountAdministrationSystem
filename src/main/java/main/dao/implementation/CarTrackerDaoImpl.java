package main.dao.implementation;

import main.dao.CarDao;
import main.dao.CarTrackerDao;
import main.dao.JPA;
import main.domain.Car;
import main.domain.CarTracker;
import main.domain.Owner;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;


@Stateless
@JPA
public class CarTrackerDaoImpl extends GenericDaoJPAImpl<CarTracker> implements CarTrackerDao {

    public CarTrackerDaoImpl() {
    }

    @Override
    public List<Car> findByCar(Car car) {
        return getEntityManager().createNamedQuery("cartracker.findByCar", Car.class)
                .setParameter("car", car)
                .getResultList();
    }
}
