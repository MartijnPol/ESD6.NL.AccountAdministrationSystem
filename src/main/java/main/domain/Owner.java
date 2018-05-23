package main.domain;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
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
@NamedQueries({
        @NamedQuery(name = "owner.findByFullNameAndCSN", query = "SELECT o FROM Owner o WHERE o.firstName = :firstName " +
                "AND o.lastName = :lastName AND o.citizenServiceNumber = :citizenServiceNumber"),
        @NamedQuery(name = "owner.findByCSN",
                query = "SELECT o FROM Owner o WHERE o.citizenServiceNumber = :citizenServiceNumber")
})
public class Owner extends BaseEntity {

    private String firstName;
    private String lastName;

    @Temporal(TemporalType.DATE)
    private Date birthDay;

    private Long citizenServiceNumber;

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
        JsonArrayBuilder ownershipArrayBuilder = Json.createArrayBuilder();

        for (Ownership ownership : getOwnerships()) {
            ownershipArrayBuilder.add(ownership.toJsonSimple());
        }

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String formattedBirthDay = dateFormat.format(this.getBirthDay());

        return Json.createObjectBuilder()
                .add("fullName", this.getFullName())
                .add("birthday", formattedBirthDay)
                .add("citizenServiceNumber", this.getCitizenServiceNumber())
                .add("address", this.getAddress().toJson())
                .add("ownerships", ownershipArrayBuilder)
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

    public Long getCitizenServiceNumber() {
        return citizenServiceNumber;
    }

    public void setCitizenServiceNumber(Long citizenServiceNumber) {
        this.citizenServiceNumber = citizenServiceNumber;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Owner owner = (Owner) o;
        return Objects.equals(firstName, owner.firstName) &&
                Objects.equals(lastName, owner.lastName) &&
                Objects.equals(birthDay, owner.birthDay) &&
                Objects.equals(address, owner.address) &&
                Objects.equals(ownerships, owner.ownerships);
    }
}
