package main.dao.implementation;

import main.dao.JPA;
import main.dao.LogDao;
import main.domain.BatchLog;

import javax.ejb.Stateless;

/**
 * @author Thom van de Pas on 14-5-2018
 */
@Stateless
@JPA
public class LogDaoImpl extends GenericDaoJPAImpl<BatchLog> implements LogDao {

    public LogDaoImpl() {
    }
}
