package main.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class RDWFuel extends BaseEntity {

    private String brandstof_omschrijving;
    private String brandstof_volgnummer;
    private String brandstofverbruik_buiten;
    private String brandstofverbruik_gecombineerd;
    private String brandstofverbruik_stad;
    private String co2_uitstoot_gecombineerd;
    private String emissiecode_omschrijving;
    private String geluidsniveau_stationair;
    private String kenteken;
    private String milieuklasse_eg_goedkeuring_licht;
    private String nettomaximumvermogen;
    private String toerental_geluidsniveau;

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">

    public String getBrandstof_omschrijving() {
        return brandstof_omschrijving;
    }

    public void setBrandstof_omschrijving(String brandstof_omschrijving) {
        this.brandstof_omschrijving = brandstof_omschrijving;
    }

    public String getBrandstof_volgnummer() {
        return brandstof_volgnummer;
    }

    public void setBrandstof_volgnummer(String brandstof_volgnummer) {
        this.brandstof_volgnummer = brandstof_volgnummer;
    }

    public String getBrandstofverbruik_buiten() {
        return brandstofverbruik_buiten;
    }

    public void setBrandstofverbruik_buiten(String brandstofverbruik_buiten) {
        this.brandstofverbruik_buiten = brandstofverbruik_buiten;
    }

    public String getBrandstofverbruik_gecombineerd() {
        return brandstofverbruik_gecombineerd;
    }

    public void setBrandstofverbruik_gecombineerd(String brandstofverbruik_gecombineerd) {
        this.brandstofverbruik_gecombineerd = brandstofverbruik_gecombineerd;
    }

    public String getBrandstofverbruik_stad() {
        return brandstofverbruik_stad;
    }

    public void setBrandstofverbruik_stad(String brandstofverbruik_stad) {
        this.brandstofverbruik_stad = brandstofverbruik_stad;
    }

    public String getCo2_uitstoot_gecombineerd() {
        return co2_uitstoot_gecombineerd;
    }

    public void setCo2_uitstoot_gecombineerd(String co2_uitstoot_gecombineerd) {
        this.co2_uitstoot_gecombineerd = co2_uitstoot_gecombineerd;
    }

    public String getEmissiecode_omschrijving() {
        return emissiecode_omschrijving;
    }

    public void setEmissiecode_omschrijving(String emissiecode_omschrijving) {
        this.emissiecode_omschrijving = emissiecode_omschrijving;
    }

    public String getGeluidsniveau_stationair() {
        return geluidsniveau_stationair;
    }

    public void setGeluidsniveau_stationair(String geluidsniveau_stationair) {
        this.geluidsniveau_stationair = geluidsniveau_stationair;
    }

    public String getKenteken() {
        return kenteken;
    }

    public void setKenteken(String kenteken) {
        this.kenteken = kenteken;
    }

    public String getMilieuklasse_eg_goedkeuring_licht() {
        return milieuklasse_eg_goedkeuring_licht;
    }

    public void setMilieuklasse_eg_goedkeuring_licht(String milieuklasse_eg_goedkeuring_licht) {
        this.milieuklasse_eg_goedkeuring_licht = milieuklasse_eg_goedkeuring_licht;
    }

    public String getNettomaximumvermogen() {
        return nettomaximumvermogen;
    }

    public void setNettomaximumvermogen(String nettomaximumvermogen) {
        this.nettomaximumvermogen = nettomaximumvermogen;
    }

    public String getToerental_geluidsniveau() {
        return toerental_geluidsniveau;
    }

    public void setToerental_geluidsniveau(String toerental_geluidsniveau) {
        this.toerental_geluidsniveau = toerental_geluidsniveau;
    }

    //</editor-fold>
}
