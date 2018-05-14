package web.bean.invoice;

import main.domain.BatchLog;
import main.service.BatchLogService;
import web.bean.BaseBean;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * @author Thom van de Pas on 14-5-2018
 */
@Named
@ViewScoped
public class BatchLogOverviewBean extends BaseBean {

    @Inject
    private BatchLogService batchLogService;

    private List<BatchLog> batchLogs;

    @Override
    @PostConstruct
    public void init() {
        this.batchLogs = this.batchLogService.findAll();
    }

    public List<BatchLog> getBatchLogs() {
        return batchLogs;
    }

    public void setBatchLogs(List<BatchLog> batchLogs) {
        this.batchLogs = batchLogs;
    }
}
