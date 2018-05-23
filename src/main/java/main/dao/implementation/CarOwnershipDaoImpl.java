package main.dao.implementation;

import main.dao.JPA;
import main.dao.CarOwnershipDao;
import main.domain.Ownership;

import javax.ejb.Stateless;

/**
 * @author Thom van de Pas on 4-4-2018
 */
@Stateless
@JPA
public class CarOwnershipDaoImpl extends GenericDaoJPAImpl<Ownership> implements CarOwnershipDao {

    public CarOwnershipDaoImpl() {

    }
}
