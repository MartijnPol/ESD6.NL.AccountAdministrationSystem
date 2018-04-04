package main.domain;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Thom van de Pas on 8-3-2018
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "car.findByOwner", query = "SELECT c FROM Car c WHERE c.owner = :owner"),
        @NamedQuery(name = "car.findByCarTrackerId", query = "SELECT c FROM Car c WHERE c.carTrackerId = :carTrackerId"),
        @NamedQuery(name = "car.deleteByLicencePlate", query = "DELETE FROM Car c WHERE c.licensePlate = :licensePlate")
})
public class Car implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long carTrackerId;
    private String licensePlate;

    @ManyToOne(cascade = CascadeType.ALL)
    private Owner owner;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<Ownership> pastOwnerships;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Ownership currentOwnership;

    public Car() {
        this.pastOwnerships = new ArrayList<>();
    }

    public Car(String licensePlate, Owner owner) {
        this();
        this.licensePlate = licensePlate;
        this.owner = owner;
    }

    public JsonObject toJson() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String date = dateFormat.format(this.owner.getBirthDay());
        return Json.createObjectBuilder()
                .add("carTrackerId", this.carTrackerId)
                .add("licensePlate", this.licensePlate)
                .add("owner", Json.createObjectBuilder()
                        .add("fullname", this.owner.getFullName())
                        .add("birthday", date).build())
                .build();
    }

    //<editor-fold desc="Getters/Setters">
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCarTrackerId() {
        return carTrackerId;
    }

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

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
    //</editor-fold>

    //<editor-fold desc="equals/hashCode">
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(id, car.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    //</editor-fold>
}
