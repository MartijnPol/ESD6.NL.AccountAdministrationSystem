package main.dao.implementation;

import main.dao.JPA;
import main.dao.RDWDao;
import main.domain.RDW;
import main.utils.JSONHelper;
import main.utils.StringHelper;

import javax.ejb.Stateless;

/**
 * Created by Martijn van der Pol on 06-04-18
 **/
@Stateless
@JPA
public class RDWDaoImpl implements RDWDao {

    /**
     * Function to get RDW data from a given license plate
     * @param licensePlate the license plate
     * @return RDW data
     */
    @Override
    public RDW findByLicensePlate(String licensePlate) {
        String baseUrl = "http://opendata.rdw.nl/resource/m9d7-ebf2.json";
        String url = baseUrl + "?kenteken=" + StringHelper.replace(licensePlate, "-", "");
        return JSONHelper.getRDWJSONObjectFromUrl(url);
    }
}
