package main.domain;

import javax.persistence.Entity;
import java.util.Date;

/**
 * @author Thom van de Pas on 14-5-2018
 */
@Entity
public class BatchLog extends BaseEntity {

    private Long executionId;
    private String batchJobName;
    private String detailedMessage;
    private Date time;

    public BatchLog() {
    }

    public BatchLog(String batchJobName, String detailedMessage, Long executionId) {
        this.time = new Date();
        this.batchJobName = batchJobName;
        this.detailedMessage = detailedMessage;
        this.executionId = executionId;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getDetailedMessage() {
        return detailedMessage;
    }

    public void setDetailedMessage(String detailedMessage) {
        this.detailedMessage = detailedMessage;
    }

    public Long getExecutionId() {
        return executionId;
    }

    public void setExecutionId(Long executionId) {
        this.executionId = executionId;
    }

    public String getBatchJobName() {
        return batchJobName;
    }

    public void setBatchJobName(String batchJobName) {
        this.batchJobName = batchJobName;
    }

    //</editor-fold>
}
