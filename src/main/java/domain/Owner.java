package domain;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Thom van de Pas on 8-3-2018
 */
@Entity
public class Owner implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @Temporal(TemporalType.DATE)
    private Date birthDay;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Car> cars;

    public Owner() {
    }

    public Owner(String firstName, String lastName, Date birthDay) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
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

    /**
     * Adds a car to the owner.
     * @param car to be added.
     */
    public void addCar(Car car) {
        if (car != null && !cars.contains(car)) {
            this.cars.add(car);
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

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
    //</editor-fold>

    //<editor-fold desc="equals/hashCode">
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owner owner = (Owner) o;
        return Objects.equals(id, owner.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    //</editor-fold>
}
