package main.service;

import main.dao.JPA;
import main.dao.OwnerDao;
import main.domain.Owner;
import org.jasypt.commons.CommonUtils;

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

    public Owner createOrUpdate(Owner owner) {
        return this.ownerDao.createOrUpdate(owner);
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

    /**
     * Finds an Owner based on the combination: firstName, lastName and citizenServiceNumber.
     *
     * @param firstName            is the first name of an Owner.
     * @param lastName             is the last name of an Owner.
     * @param citizenServiceNumber is the citizen service number of an Owner.
     * @return the found Owner or null
     */
    public Owner findByFullNameAndCSN(String firstName, String lastName, Long citizenServiceNumber) {
        if (CommonUtils.isNotEmpty(firstName) && CommonUtils.isNotEmpty(lastName) && citizenServiceNumber != null) {
            return this.ownerDao.findByFullNameAndCSN(firstName, lastName, citizenServiceNumber);
        }
        return null;
    }

    public List<Owner> findAll() {
        return this.ownerDao.findAll();
    }
}
