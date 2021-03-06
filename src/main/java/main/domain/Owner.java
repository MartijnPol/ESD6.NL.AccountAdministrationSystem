package main.domain;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Thom van de Pas on 8-3-2018
 */
@XmlRootElement
@Entity
public class Owner extends BaseEntity {

    private String firstName;
    private String lastName;

    @Temporal(TemporalType.DATE)
    private Date birthDay;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Ownership> ownerships;

    public Owner() {
        this.ownerships = new ArrayList<>();
    }

    public Owner(String firstName, String lastName, Date birthDay, Address address) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.address = address;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public JsonObject toJson() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String date = dateFormat.format(this.getBirthDay());
        return Json.createObjectBuilder()
                .add("fullname", this.getFullName())
                .add("birthday", date)
                .build();
    }

    public void addOwnership(Ownership ownership) {
        if (!ownerships.contains(ownership)) {
            this.ownerships.add(ownership);
        }
    }

    //<editor-fold desc="Getters/Setters">
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Ownership> getOwnerships() {
        return ownerships;
    }

    public void setOwnerships(List<Ownership> ownerships) {
        this.ownerships = ownerships;
    }

    //</editor-fold>
}
