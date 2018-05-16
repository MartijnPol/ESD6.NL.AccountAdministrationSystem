package main.domain;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Thom van de Pas on 4-4-2018
 */
@XmlRootElement
@Entity
public class Address extends BaseEntity {

    private String street;
    private String streetNr;
    private String postalCode;
    private String city;
    private String country;

    public Address() {
    }

    public Address(String street, String streetNr, String postalCode, String city, String country) {
        this.street = street;
        this.streetNr = streetNr;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNr() {
        return streetNr;
    }

    public void setStreetNr(String streetNr) {
        this.streetNr = streetNr;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String zipCode) {
        this.postalCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreetAndNr() {
        return street + " " + streetNr;
    }
    //</editor-fold>
}