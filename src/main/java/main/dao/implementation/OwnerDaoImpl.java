package main.dao.implementation;

import main.dao.JPA;
import main.dao.OwnerDao;
import main.domain.Owner;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 * @author Thom van de Pas on 8-3-2018
 */
@Stateless
@JPA
public class OwnerDaoImpl extends GenericDaoJPAImpl<Owner> implements OwnerDao {

    public OwnerDaoImpl() {
    }

    @Override
    public Owner findByFullNameAndCSN(String firstName, String lastName, Long citizenServiceNumber) {
        TypedQuery<Owner> query = getEntityManager().createNamedQuery("owner.findByFullNameAndCSN", Owner.class)
                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName)
                .setParameter("citizenServiceNumber", citizenServiceNumber);

        return oneResult(query);
    }
}
