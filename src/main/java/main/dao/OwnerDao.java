package main.dao;

import main.domain.Owner;

/**
 * @author Thom van de Pas on 8-3-2018
 */
public interface OwnerDao extends GenericDao<Owner> {

    Owner findByFullNameAndCSN(String firstName, String lastName, Long citizenServiceNumber);
}
