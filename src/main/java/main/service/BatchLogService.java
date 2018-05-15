package main.service;

import main.dao.JPA;
import main.dao.LogDao;
import main.domain.BatchLog;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * @author Thom van de Pas on 14-5-2018
 */
@Stateless
public class BatchLogService {

    @Inject
    @JPA
    private LogDao logDao;

    public BatchLog createOrUpdate(BatchLog batchLog) {
        if (null != batchLog) {
            return this.logDao.createOrUpdate(batchLog);
        }
        return null;
    }

    public List<BatchLog> findAll() {
        return this.logDao.findAll();
    }
}
