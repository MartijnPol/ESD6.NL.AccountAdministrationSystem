package main.service;

import main.dao.JPA;
import main.dao.OwnershipDao;
import main.domain.Owner;
import main.domain.Ownership;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * @author Thom van de Pas on 4-4-2018
 */
@Stateless
public class OwnershipService {

    @Inject
    @JPA
    private OwnershipDao ownershipDao;


    public Ownership createOrUpdate(Ownership ownership) {
        return this.ownershipDao.createOrUpdate(ownership);
    }

    public List<Ownership> findAll() {
        return this.ownershipDao.findAll();
    }
}
