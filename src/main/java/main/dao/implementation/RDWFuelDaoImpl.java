package main.dao.implementation;

import main.dao.JPA;
import main.dao.RDWFuelDao;
import main.domain.RDWFuel;
import main.utils.JSONHelper;
import main.utils.StringHelper;

import javax.ejb.Stateless;

@Stateless
@JPA
public class RDWFuelDaoImpl implements RDWFuelDao {

    private final String baseUrl = "http://opendata.rdw.nl/resource/8ys7-d773.json";

    @Override
    public RDWFuel findByLicensePlate(String licensePlate) {
        String url = baseUrl + "?kenteken=" + StringHelper.replace(licensePlate, "-", "");
        return JSONHelper.getRDWFuelJSONObjectFromUrl(url);
    }
}
