package main.domain;

import main.domain.enums.PaymentStatus;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * @author Thom van de Pas on 4-4-2018
 */
@XmlRootElement
@Entity
@NamedQueries({
        @NamedQuery(name = "invoice.findByInvoiceNr", query = "SELECT i FROM Invoice i WHERE i.invoiceNr = :invoiceNr"),
        @NamedQuery(name = "invoice.findFirstInvoice", query = "SELECT i FROM Invoice i ORDER BY i.invoiceNr ASC"),
        @NamedQuery(name = "invoice.findLastInvoiceNr", query = "SELECT MAX(i.invoiceNr) FROM Invoice i"),
        @NamedQuery(name = "invoice.findByOwner", query = "SELECT i FROM Invoice i WHERE i.ownership.owner = :owner")
})
public class Invoice extends BaseEntity {

    private Long invoiceNr;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Temporal(value = TemporalType.DATE)
    private Date period;

    @Column(precision = 20, scale = 10)
    private BigDecimal totalAmount;

    @ManyToOne
    private Ownership ownership;

    private String filePath;

    public Invoice() {
        this.paymentStatus = PaymentStatus.OPEN;
        this.totalAmount = BigDecimal.ZERO;
        this.period = new Date();
    }

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Date getPeriod() {
        return period;
    }

    public void setPeriod(Date period) {
        this.period = period;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Ownership getOwnership() {
        return ownership;
    }

    public void setOwnership(Ownership ownership) {
        this.ownership = ownership;
    }

    public Long getInvoiceNr() {
        return invoiceNr;
    }

    public void setInvoiceNr(Long invoiceNr) {
        this.invoiceNr = invoiceNr;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    //</editor-fold>

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(invoiceNr, invoice.invoiceNr) &&
                paymentStatus == invoice.paymentStatus &&
                Objects.equals(period, invoice.period) &&
                Objects.equals(totalAmount, invoice.totalAmount) &&
                Objects.equals(ownership, invoice.ownership);
    }

    public JsonObject toJson() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = dateFormat.format(this.getPeriod());

        return Json.createObjectBuilder()
                .add("invoiceNr", this.getInvoiceNr())
                .add("paymentStatus", this.getPaymentStatus().toString())
                .add("date",formattedDate)
                .add("totalAmount", this.getTotalAmount().setScale(2, RoundingMode.HALF_UP))
                .build();
    }
}
