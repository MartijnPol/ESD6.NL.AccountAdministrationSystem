package main.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@XmlRootElement
@Entity
public class Tariff extends BaseEntity {

    private double tariffInEuro;

    @ElementCollection(fetch = FetchType.LAZY)
    private Map<String, Double> carLabels;

    private boolean ridingDuringRushHour;

    public Tariff() {
        this.ridingDuringRushHour = false;
        this.carLabels = new HashMap<>();
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

    public Map<String, Double> getCarLabels() {
        return carLabels;
    }

    public void setCarLabels(Map<String, Double> carLabels) {
        this.carLabels = carLabels;
    }

    public void addCarLabel(String label, Double percentage) {
        this.carLabels.put(label, percentage);
    }

    public void addCarLabels(Map<String, Double> carLabels) {
        this.carLabels.putAll(carLabels);
    }

    //</editor-fold>
}
