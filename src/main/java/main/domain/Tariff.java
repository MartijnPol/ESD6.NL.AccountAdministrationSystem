package main.domain;

import main.domain.enums.RoadType;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.Map;

@XmlRootElement
@Entity
public class Tariff extends BaseEntity {

    private double tariffInEuro;

    @ElementCollection(fetch = FetchType.LAZY)
    private Map<String, Double> carLabels;

    @ElementCollection(fetch = FetchType.LAZY)
    private Map<String, Double> carFuels;

    @ElementCollection(fetch = FetchType.LAZY)
    private Map<String, Double> rushHourAdditions;

    public Tariff() {
        this.rushHourAdditions = new HashMap<>();
        this.carLabels = new HashMap<>();
        this.carFuels = new HashMap<>();
    }

    public Tariff(double tariffInEuro) {
        this.tariffInEuro = tariffInEuro;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public double getTariffInEuro() {
        return tariffInEuro;
    }

    public void setTariffInEuro(double tariffInEuro) {
        this.tariffInEuro = tariffInEuro;
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

    public Map<String, Double> getCarFuels() {
        return carFuels;
    }

    public void setCarFuels(Map<String, Double> carFuels) {
        this.carFuels = carFuels;
    }

    public void addCarFuel(String fuel, Double percentage) {
        this.carFuels.put(fuel, percentage);
    }

    public void addCarFuels(Map<String, Double> carFuels) {
        this.carFuels.putAll(carFuels);
    }

    public Map<String, Double> getRushHourAdditions() {
        return rushHourAdditions;
    }

    public void setRushHourAdditions(Map<String, Double> rushHourAdditions) {
        this.rushHourAdditions = rushHourAdditions;
    }

    //</editor-fold>
}
