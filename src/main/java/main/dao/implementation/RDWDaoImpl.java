package main.dao.implementation;

import main.dao.JPA;
import main.dao.RDWDao;
import main.domain.RDW;
import main.utils.JSONHelper;

import javax.ejb.Stateless;

/**
 * Created by Martijn van der Pol on 06-04-18
 **/
@Stateless
@JPA
public class RDWDaoImpl implements RDWDao {

    private String baseUrl = "http://opendata.rdw.nl/resource/m9d7-ebf2.json";

    /**
     * Function to get RDW data from a given license plate
     * @param licensePlate the license plate
     * @return RDW data
     */
    @Override
    public RDW findByLicensePlate(String licensePlate) {
        String url = baseUrl + "?kenteken=" + trimLicensePlate(licensePlate);
        return JSONHelper.getJSONObjectFromUrl(url);
    }

    /**
     * Function to trim the license plate to match RDW constraints
     *
     * @param licensePlate the license plate of the car
     * @return trimmed string
     */
    private String trimLicensePlate(String licensePlate) {
        return licensePlate.replace("-", "");
    }
}
