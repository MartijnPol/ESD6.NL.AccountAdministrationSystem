package main.service;

import main.dao.JPA;
import main.dao.OwnershipDao;
import main.domain.Owner;
import main.domain.Ownership;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author Thom van de Pas on 4-4-2018
 */
@Stateless
public class OwnershipService {

    @Inject
    @JPA
    private OwnershipDao ownershipDao;


    public Ownership create(Ownership ownership) {
        return this.ownershipDao.create(ownership);
    }

    public Ownership update(Ownership ownership) {
        return this.ownershipDao.update(ownership);
    }
}
