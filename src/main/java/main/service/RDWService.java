package main.service;

import main.dao.JPA;
import main.dao.RDWDao;
import main.domain.RDW;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by Martijn van der Pol on 06-04-18
 **/
@Stateless
public class RDWService {

    @Inject
    @JPA
    private RDWDao rdwDao;

    public RDW findByLicensePlate(String licensePlate) {
        return rdwDao.findByLicensePlate(licensePlate);
    }

}
