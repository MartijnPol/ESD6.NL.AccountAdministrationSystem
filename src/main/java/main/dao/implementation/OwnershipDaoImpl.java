package main.dao.implementation;

import main.dao.JPA;
import main.dao.OwnershipDao;
import main.domain.Ownership;

import javax.ejb.Stateless;

/**
 * @author Thom van de Pas on 4-4-2018
 */
@Stateless
@JPA
public class OwnershipDaoImpl extends GenericDaoJPAImpl<Ownership> implements OwnershipDao {

    public OwnershipDaoImpl() {

    }
}
