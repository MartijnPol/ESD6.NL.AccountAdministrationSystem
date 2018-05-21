package main.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class CarTrackerRuleResponse {

    private Long id;

    private Long kmDriven;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date date;

    private double lat;

    private double lon;

    private boolean driven;

    public CarTrackerRuleResponse() {
    }

    //<editor-fold desc="Getters/Setters">

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}