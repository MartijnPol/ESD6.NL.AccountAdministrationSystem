package main.domain;

import main.service.CarService;
import main.service.OwnerService;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class CarTracker implements Serializable {

    private Long id;
    private Long totalRules;
    private List<CarTrackerRule> rules;
    private Owner owner;
    private Car car;

    OwnerService ownerService;
    CarService carService;

    public CarTracker() {
        rules = new ArrayList<>();
    }

    public CarTracker(Long totalRules, List<CarTrackerRule> rules) {
        this();
        this.totalRules = totalRules;
        this.rules = rules;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTotalRules() {
        return totalRules;
    }

    public void setTotalRules(Long totalRules) {
        this.totalRules = totalRules;
    }

    public List<CarTrackerRule> getRules() {
        return rules;
    }

    public void setRules(List<CarTrackerRule> rules) {
        this.rules = rules;
        calculateTotalRules();
    }

    public void addRules(List<CarTrackerRule> rules) {
        this.rules.addAll(rules);
        calculateTotalRules();
    }

    public void calculateTotalRules() {
        this.totalRules = (long) rules.size();
    }


    public JsonObject toJson() {
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

        for (CarTrackerRule carTrackerRule : rules) {
            jsonArrayBuilder.add(carTrackerRule.toJson());
        }

        return Json.createObjectBuilder()
                .add("CarTrackerId", this.id)
                .add("totalRules", this.totalRules)
                .add("CarTrackerRules", jsonArrayBuilder)
                .build();
    }

    @Override
    public String toString() {
        return "Tracker [id=" + id + ", TotalRules=" + totalRules + ", rules=" + rules + "]";
    }
}
