package main.domain;


import javax.json.Json;
import javax.json.JsonObject;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class CarTrackerRule implements Serializable {

    private Long id;
    private CarTracker carTracker;
    private Long kmDriven;
    private Date date;
    private double lat;
    private double lon;
    private boolean driven;


    public CarTrackerRule() {
    }

    public CarTrackerRule(CarTracker carTracker, Long kmDriven, Date date, double lat, double lon, boolean driven) {
        this.carTracker = carTracker;
        this.kmDriven = kmDriven;
        this.date = date;
        this.lat = lat;
        this.lon = lon;
        this.driven = driven;
    }

    public JsonObject toJson() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        String date = dateFormat.format(this.date);
        return Json.createObjectBuilder()
                .add("id", this.id)
                .add("kmDriven" , this.kmDriven)
                .add("date", date)
                .add("lat", this.lat)
                .add("lon", this.lon)
                .add("driven", driven)
                .build();
    }

    //<editor-fold desc="Getters/Setters">
    public Long getId() {
        return id;
    }

    public void setId(Long ruleId) {
        this.id = ruleId;
    }

    public CarTracker getCarTracker() {
        return carTracker;
    }

    public void setCarTracker(CarTracker carTracker) {
        this.carTracker = carTracker;
    }

    public Long getKmDriven() {
        return kmDriven;
    }

    public void setKmDriven(Long kmDriven) {
        this.kmDriven = kmDriven;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public boolean isDriven() {
        return driven;
    }

    public void setDriven(boolean driven) {
        this.driven = driven;
    }
    //</editor-fold>

    //<editor-fold desc="equals/hashCode">
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarTrackerRule that = (CarTrackerRule) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
    //</editor-fold>
}
