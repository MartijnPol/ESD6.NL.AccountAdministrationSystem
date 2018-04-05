package main.dao.implementation;

import main.dao.JPA;
import main.dao.TariffDao;
import main.domain.Tariff;

import javax.ejb.Stateless;

@Stateless
@JPA
public class TariffDaoImpl extends GenericDaoJPAImpl<Tariff> implements TariffDao {

    public TariffDaoImpl(){

    }
}
