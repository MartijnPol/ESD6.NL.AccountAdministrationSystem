package main.domain;

import java.util.List;

public class CarTrackerResponse {

    private String CarTrackerId;

    private Long totalRules;

    private List<CarTrackerRuleResponse> carTrackerRuleResponses;

    public CarTrackerResponse() {
    }

    //<editor-fold desc="Getters/Setters">

    public String getCarTrackerId() {
        return CarTrackerId;
    }

    public void setCarTrackerId(String carTrackerId) {
        CarTrackerId = carTrackerId;
    }

    public Long getTotalRules() {
        return totalRules;
    }

    public void setTotalRules(Long totalRules) {
        this.totalRules = totalRules;
    }

    public List<CarTrackerRuleResponse> getCarTrackerRuleResponses() {
        return carTrackerRuleResponses;
    }

    public void setCarTrackerRuleResponses(List<CarTrackerRuleResponse> carTrackerRuleResponses) {
        this.carTrackerRuleResponses = carTrackerRuleResponses;
    }


    //</editor-fold>
}
