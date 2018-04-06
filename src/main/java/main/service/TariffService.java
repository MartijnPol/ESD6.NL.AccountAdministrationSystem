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
     * Create or update a tariff
     * @param tariff
     * @return the new or updated tariff
     */
    public Tariff createOrUpdate(Tariff tariff) {
        return this.tariffDao.createOrUpdate(tariff);
    }

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
