package main.service;

import main.dao.JPA;
import main.dao.TariffDao;
import main.domain.Tariff;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class TariffService {

    @Inject
    @JPA
    private TariffDao tariffDao;

    /**
     * Create a new tariff in the database
     * @param tariff
     * @return the newly created tariff
     */
    public Tariff create(Tariff tariff) { return this.tariffDao.create(tariff); }

    /**
     * Update an excisting tariff in the database
     * @param tariff
     * @return the updated tariff
     */
    public Tariff update(Tariff tariff) { return this.tariffDao.update(tariff); }

    /**
     * Delete a tariff from the database
     * @param tariff
     */
    public void delete(Tariff tariff) { this.tariffDao.delete(tariff); }

    /**
     * Delete a tariff from the database using an id
     * @param id
     */
    public void deleteById(Long id) { this.tariffDao.deleteById(id); }

    /**
     * Get a list of all the tariffs that exist in the database.
     * @return the list of all the tariffs found
     */
    public List<Tariff> findAll() { return this.tariffDao.findAll(); }
}
