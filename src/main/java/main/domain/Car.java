package main.domain;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Thom van de Pas on 8-3-2018
 */
@XmlRootElement
@Entity
@NamedQueries({
        @NamedQuery(name = "car.findByOwner", query = "SELECT c FROM Car c WHERE c.currentOwnership.owner = :owner"),
        @NamedQuery(name = "car.findByCarTrackerId", query = "SELECT c FROM Car c WHERE c.carTrackerId = :carTrackerId"),
        @NamedQuery(name = "car.deleteByLicencePlate", query = "DELETE FROM Car c WHERE c.licensePlate = :licensePlate")
})

public class Car extends BaseEntity {

    private Long carTrackerId;
    private String licensePlate;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private CarTracker currentCartracker;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<CarTracker> pastCartrackers;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<Ownership> pastOwnerships;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Ownership currentOwnership;

    @OneToOne(cascade = CascadeType.ALL)
    private RDW rdwData;

    @OneToOne(cascade = CascadeType.ALL)
    private RDWFuel rdwFuelData;


    public Car() {
        this.pastOwnerships = new ArrayList<>();
        this.pastCartrackers = new ArrayList<>();
    }

    public Car(String licensePlate, Ownership currentOwnership) {
        this();
        this.licensePlate = licensePlate;
        this.currentOwnership = currentOwnership;
    }

    public Car(String licensePlate, Ownership currentOwnership, CarTracker currentCartracker) {
        this();
        this.licensePlate = licensePlate;
        this.currentOwnership = currentOwnership;
        this.currentCartracker = currentCartracker;
        this.carTrackerId = currentCartracker.getId();
    }


    public JsonObject toJson() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String date = dateFormat.format(this.getCurrentOwnership().getOwner().getBirthDay());
        return Json.createObjectBuilder()
                .add("carTrackerId", this.carTrackerId)
                .add("licensePlate", this.licensePlate)
                .add("owner", Json.createObjectBuilder()
                        .add("fullname", this.getCurrentOwnership().getOwner().getFullName())
                        .add("birthday", date).build())
                .build();
    }

    public void addPastOwnership(Ownership ownership) {
        this.pastOwnerships.add(ownership);
    }

    public void addMultiplePastOwnerships(List<Ownership> ownerships) {
        this.pastOwnerships.addAll(ownerships);
    }

    public void addPastCartracker(CarTracker carTracker) {
        this.pastCartrackers.add(carTracker);
    }

    public void addMultiplePastCartrackers(List<CarTracker> carTrackers) {
        this.pastCartrackers.addAll(carTrackers);
    }

    //<editor-fold desc="Getters/Setters">
    public Long getCarTrackerId() { return carTrackerId; }

    public void setCarTrackerId(Long carTrackerId) {
        this.carTrackerId = carTrackerId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public List<Ownership> getPastOwnerships() {
        return pastOwnerships;
    }

    public void setPastOwnerships(List<Ownership> pastOwnerships) {
        this.pastOwnerships = pastOwnerships;
    }

    public Ownership getCurrentOwnership() {
        return currentOwnership;
    }

    public void setCurrentOwnership(Ownership currentOwnership) {
        this.currentOwnership = currentOwnership;
    }

    public RDW getRdwData() {
        return rdwData;
    }

    public void setRdwData(RDW rdwData) {
        this.rdwData = rdwData;
    }

    public RDWFuel getRdwFuelData() {
        return rdwFuelData;
    }

    public void setRdwFuelData(RDWFuel rdwFuelData) {
        this.rdwFuelData = rdwFuelData;
    }

    public CarTracker getCurrentCartracker() {
        return currentCartracker;
    }

    public void setCurrentCartracker(CarTracker newCartracker) {
        this.currentCartracker = newCartracker;
        this.currentCartracker.setId(newCartracker.getId());
    }

    public List<CarTracker> getPastCartrackers() {
        return pastCartrackers;
    }

    public void setPastCartrackers(List<CarTracker> pastCartrackers) {
        this.pastCartrackers = pastCartrackers;
    }
    //</editor-fold>
}
