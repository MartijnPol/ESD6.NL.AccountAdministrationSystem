package main.dao.implementation;

import main.dao.JPA;
import main.dao.LogDao;
import main.domain.Log;

import javax.ejb.Stateless;

/**
 * @author Thom van de Pas on 14-5-2018
 */
@Stateless
@JPA
public class LogDaoImpl extends GenericDaoJPAImpl<Log> implements LogDao {

    public LogDaoImpl() {
    }
}
