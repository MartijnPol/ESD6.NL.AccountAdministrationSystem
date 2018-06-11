package main.domain;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
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
        @NamedQuery(name = "car.findByCarTrackerId", query = "SELECT c FROM Car c WHERE c.currentCarTracker.id = :carTrackerId"),
        @NamedQuery(name = "car.deleteByLicencePlate", query = "DELETE FROM Car c WHERE c.licensePlate = :licensePlate"),
        @NamedQuery(name = "car.findByLicensePlate", query = "SELECT c FROM Car c WHERE c.licensePlate = :licensePlate")
})
public class Car extends BaseEntity {

    private String licensePlate;

    @OneToOne(cascade = {CascadeType.MERGE})
    private CarTracker currentCarTracker;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<CarTracker> pastCarTrackers;

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
        this.pastCarTrackers = new ArrayList<>();
    }

    public Car(String licensePlate) {
        this();
        this.licensePlate = licensePlate;
    }

    public Car(String licensePlate, Ownership currentOwnership) {
        this();
        this.licensePlate = licensePlate;
        this.currentOwnership = currentOwnership;
    }

    public Car(String licensePlate, Ownership currentOwnership, CarTracker currentCarTracker) {
        this();
        this.licensePlate = licensePlate;
        this.currentOwnership = currentOwnership;
        this.currentCarTracker = currentCarTracker;
    }


    public JsonObject toJson() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String date = dateFormat.format(this.getCurrentOwnership().getOwner().getBirthDay());
        String ownerSince = dateFormat.format(this.getCurrentOwnership().getStartDate());

        JsonArrayBuilder previousOwners = Json.createArrayBuilder();

        for (Ownership pastOwnership : this.pastOwnerships) {
            previousOwners.add(pastOwnership.toJsonForCarToJson());
        }

        return Json.createObjectBuilder()
                .add("carTrackerId", this.currentCarTracker.getId())
                .add("licensePlate", this.licensePlate)
                .add("owner", Json.createObjectBuilder()
                        .add("fullName", this.getCurrentOwnership().getOwner().getFullName())
                        .add("birthday", date)
                        .add("ownerSince", ownerSince).build())
                .add("previousOwners", previousOwners)
                .build();
    }

    public void addPastOwnership(Ownership ownership) {
        this.pastOwnerships.add(ownership);
    }

    public void addMultiplePastOwnerships(List<Ownership> ownerships) {
        this.pastOwnerships.addAll(ownerships);
    }

    public void addPastCarTracker(CarTracker carTracker) {
        this.pastCarTrackers.add(carTracker);
    }

    public void addMultiplePastCartrackers(List<CarTracker> carTrackers) {
        this.pastCarTrackers.addAll(carTrackers);
    }

    //<editor-fold desc="Getters/Setters">

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

    public CarTracker getCurrentCarTracker() {
        return currentCarTracker;
    }

    public void setCurrentCarTracker(CarTracker newCarTracker) {
        this.currentCarTracker = newCarTracker;
        this.currentCarTracker.setId(newCarTracker.getId());
    }

    public List<CarTracker> getPastCarTrackers() {
        return pastCarTrackers;
    }

    public void setPastCarTrackers(List<CarTracker> pastCarTrackers) {
        this.pastCarTrackers = pastCarTrackers;
    }
    //</editor-fold>
}
