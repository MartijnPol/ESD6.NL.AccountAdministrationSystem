package main.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@NamedQueries({
        @NamedQuery(name = "cartracker.findByCar", query = "SELECT c FROM CarTracker c WHERE c.car = :car"),
        @NamedQuery(name = "carTracker.findById", query = "SELECT c FROM CarTracker  c WHERE c.id = :id")
})
public class CarTracker implements Serializable {

    @Id
    private String id;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    private String manufacturer;

    @ManyToOne
    private Car car;

    private boolean enabled;

    public CarTracker(){
        this.enabled = true;
    }

    public CarTracker(String id , String manufacturer){
        this();
        this.id = id;
        this.manufacturer = manufacturer;
        this.startDate = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String fabric) {
        this.manufacturer = fabric;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Tracker [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + "]";
    }
}
