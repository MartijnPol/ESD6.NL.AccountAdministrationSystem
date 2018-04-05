package main.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tariff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double tariffInEuro;

    private boolean ridingDuringRushHour;

    public Tariff(){
        this.ridingDuringRushHour = false;
    }

    public Tariff(double tariffInEuro, boolean ridingDuringRushHour){
        this.tariffInEuro = tariffInEuro;
        this.ridingDuringRushHour = ridingDuringRushHour;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTariffInEuro() {
        return tariffInEuro;
    }

    public void setTariffInEuro(double tariffInEuro) {
        this.tariffInEuro = tariffInEuro;
    }

    public boolean getRidingDuringRushHour(){
        return ridingDuringRushHour;
    }

    public void setRidingDuringRushHour(boolean ridingDuringRushHour) {
        this.ridingDuringRushHour = ridingDuringRushHour;
    }

    //</editor-fold>
}
