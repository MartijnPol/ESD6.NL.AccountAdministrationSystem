package main.domain;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Thom van de Pas on 4-4-2018
 */
@XmlRootElement
@Entity
public class Ownership extends BaseEntity {

    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @OneToMany(mappedBy = "ownership", cascade = CascadeType.ALL)
    private List<Invoice> invoices;

    @ManyToOne
    private Car car;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Owner owner;

    public Ownership() {
        this.invoices = new ArrayList<>();
    }

    public void addInvoice(Invoice invoice) {
        if (!this.invoices.contains(invoice)) {
            this.invoices.add(invoice);
        }
    }

    public JsonObject toJsonSimple() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return Json.createObjectBuilder()
                .add("car", car.toJson())
                .build();
    }

    public JsonObject toJsonComplete() {
        JsonArrayBuilder invoiceArrayBuilder = Json.createArrayBuilder();

        for (Invoice invoice : getInvoices()) {
            invoiceArrayBuilder.add(invoice.toJson());
        }

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String formattedStartDate = dateFormat.format(this.getStartDate());
        String formattedEndDate = dateFormat.format(this.getEndDate());
        return Json.createObjectBuilder()
                .add("startDate", formattedStartDate)
                .add("endDate", formattedEndDate)
                .add("car", car.toJson())
                .add("invoices", invoiceArrayBuilder)
                .build();
    }

    //<editor-fold desc="Getters/Setters">
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

    @XmlTransient
    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    //</editor-fold>
}
