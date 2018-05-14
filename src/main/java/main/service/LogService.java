package main.service;

import main.dao.JPA;
import main.dao.LogDao;
import main.domain.Log;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author Thom van de Pas on 14-5-2018
 */
@Stateless
public class LogService {

    @Inject
    @JPA
    private LogDao logDao;

    public Log createOrUpdate(Log log) {
        if (null != log) {
            return this.logDao.createOrUpdate(log);
        }
        return null;
    }
}
