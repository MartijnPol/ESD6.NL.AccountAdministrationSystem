package main.dao;

import main.domain.RDWFuel;

public interface RDWFuelDao {
    RDWFuel findByLicensePlate(String licensePlate);
}
