package main.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@Entity
public class Tariff extends BaseEntity {

    private double tariffInEuro;

    private boolean ridingDuringRushHour;

    public Tariff() {
        this.ridingDuringRushHour = false;
    }

    public Tariff(double tariffInEuro, boolean ridingDuringRushHour) {
        this.tariffInEuro = tariffInEuro;
        this.ridingDuringRushHour = ridingDuringRushHour;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public double getTariffInEuro() {
        return tariffInEuro;
    }

    public void setTariffInEuro(double tariffInEuro) {
        this.tariffInEuro = tariffInEuro;
    }

    public boolean getRidingDuringRushHour() {
        return ridingDuringRushHour;
    }

    public void setRidingDuringRushHour(boolean ridingDuringRushHour) {
        this.ridingDuringRushHour = ridingDuringRushHour;
    }

    //</editor-fold>
}
