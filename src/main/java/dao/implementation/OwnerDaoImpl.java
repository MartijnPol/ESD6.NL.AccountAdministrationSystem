package dao.implementation;

import dao.JPAAccountAdministration;
import dao.OwnerDao;
import domain.Owner;

import javax.ejb.Stateless;

/**
 * @author Thom van de Pas on 8-3-2018
 */
@Stateless
@JPAAccountAdministration
public class OwnerDaoImpl extends GenericDaoJPAImpl<Owner> implements OwnerDao {

    public OwnerDaoImpl() {

    }
}
