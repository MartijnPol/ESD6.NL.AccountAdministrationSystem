package main.service;

import main.dao.JPA;
import main.dao.CarOwnershipDao;
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
    private CarOwnershipDao carOwnershipDao;


    public Ownership createOrUpdate(Ownership ownership) {
        return this.carOwnershipDao.createOrUpdate(ownership);
    }

    public Ownership findById(Long id) {
        return this.carOwnershipDao.findById(id);
    }

    public List<Ownership> findAll() {
        return this.carOwnershipDao.findAll();
    }
}
