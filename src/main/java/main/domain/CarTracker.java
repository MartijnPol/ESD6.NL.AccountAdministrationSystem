package main.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@NamedQueries({
        @NamedQuery(name = "cartracker.findByCar", query = "SELECT c FROM CarTracker c WHERE c.car = :car"),
})
public class CarTracker implements Serializable {

    @Id
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    private String manufacturer;

    @ManyToOne
    private Car car;

    public CarTracker(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    @Override
    public String toString() {
        return "Tracker [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + "]";
    }
}
