package main.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

public class CarTrackerResponse {

    private String id;

    private Long totalRules;

    private List<CarTrackerRuleResponse> CarTrackerRules;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date startDate;

    private String manufacturer;

    public CarTrackerResponse() {
    }

    //<editor-fold desc="Getters/Setters">

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getTotalRules() {
        return totalRules;
    }

    public void setTotalRules(Long totalRules) {
        this.totalRules = totalRules;
    }

    public List<CarTrackerRuleResponse> getCarTrackerRules() {
        return CarTrackerRules;
    }

    public void setCarTrackerRules(List<CarTrackerRuleResponse> carTrackerRules) {
        this.CarTrackerRules = carTrackerRules;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    //</editor-fold>
}
