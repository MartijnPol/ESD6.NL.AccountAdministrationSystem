package main.dao;

import main.domain.RDW;

/**
 * Created by Martijn van der Pol on 06-04-18
 **/
public interface RDWDao {

    RDW findByLicensePlate(String licensePlate);

}
