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

    public Tariff createOrUpdate(Tariff tariff) {
        return this.tariffDao.createOrUpdate(tariff);
    }

    public void delete(Tariff tariff) { this.tariffDao.delete(tariff); }

    public void deleteById(Long id) { this.tariffDao.deleteById(id); }

    public List<Tariff> findAll() { return this.tariffDao.findAll(); }
}
