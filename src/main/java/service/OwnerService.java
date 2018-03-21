package service;

import dao.JPAAccountAdministration;
import dao.OwnerDao;
import domain.Car;
import domain.Owner;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * @author Thom van de Pas on 8-3-2018
 */
@Stateless
public class OwnerService {

    @Inject
    @JPAAccountAdministration
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
