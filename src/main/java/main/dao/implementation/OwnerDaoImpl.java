package main.dao.implementation;

import main.dao.JPA;
import main.dao.OwnerDao;
import main.domain.Owner;

import javax.ejb.Stateless;

/**
 * @author Thom van de Pas on 8-3-2018
 */
@Stateless
@JPA
public class OwnerDaoImpl extends GenericDaoJPAImpl<Owner> implements OwnerDao {

    public OwnerDaoImpl() {
    }
}
