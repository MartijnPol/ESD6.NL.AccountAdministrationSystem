package main.service;

import main.dao.JPA;
import main.dao.OwnerDao;
import main.domain.Owner;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * @author Thom van de Pas on 8-3-2018
 */
@Stateless
public class OwnerService {

    @Inject
    @JPA
    private OwnerDao ownerDao;

    public OwnerService() {
    }

    public Owner create(Owner owner) {
        return this.ownerDao.create(owner);
    }

    public Owner update(Owner owner) {
        return this.ownerDao.update(owner);
    }

    public void delete(Owner owner) {
        this.ownerDao.delete(owner);
    }

    public void deleteById(Long id) {
        this.ownerDao.deleteById(id);
    }

    public Owner findById(Long id) {
        return this.ownerDao.findById(id);
    }

    public List<Owner> findAll() {
        return this.ownerDao.findAll();
    }
}
