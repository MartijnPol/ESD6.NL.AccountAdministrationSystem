package main.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class RDWFuel extends BaseEntity {

    @JsonProperty("brandstof_omschrijving")
    private String brandstofOmschrijving;
    @JsonProperty("brandstof_volgnummer")
    private String brandstofVolgnummer;
    @JsonProperty("brandstofverbruik_buiten")
    private String brandstofVerbruikBuiten;
    @JsonProperty("brandstofverbruik_gecombineerd")
    private String brandstofVerbruikGecombineerd;
    @JsonProperty("brandstofverbruik_stad")
    private String brandstofVerbruikStad;
    @JsonProperty("co2_uitstoot_gecombineerd")
    private String co2UitstootGecombineerd;
    @JsonProperty("emissiecode_omschrijving")
    private String emissiecodeOmschrijving;
    @JsonProperty("geluidsniveau_stationair")
    private String geluidsniveauStationair;
    @JsonProperty("kenteken")
    private String kenteken;
    @JsonProperty("milieuklasse_eg_goedkeuring_licht")
    private String milieuklasseEgGoedkeuringLicht;
    @JsonProperty("nettomaximumvermogen")
    private String nettomaximumvermogen;
    @JsonProperty("toerental_geluidsniveau")
    private String toerentalGeluidsniveau;

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">

    @JsonProperty("brandstof_omschrijving")
    public String getBrandstofOmschrijving() {
        return brandstofOmschrijving;
    }

    @JsonProperty("brandstof_omschrijving")
    public void setBrandstofOmschrijving(String brandstofOmschrijving) {
        this.brandstofOmschrijving = brandstofOmschrijving;
    }

    @JsonProperty("brandstof_volgnummer")
    public String getBrandstofVolgnummer() {
        return brandstofVolgnummer;
    }

    @JsonProperty("brandstof_volgnummer")
    public void setBrandstofVolgnummer(String brandstofVolgnummer) {
        this.brandstofVolgnummer = brandstofVolgnummer;
    }

    @JsonProperty("brandstofverbruik_buiten")
    public String getBrandstofVerbruikBuiten() {
        return brandstofVerbruikBuiten;
    }

    @JsonProperty("brandstofverbruik_buiten")
    public void setBrandstofVerbruikBuiten(String brandstofVerbruikBuiten) {
        this.brandstofVerbruikBuiten = brandstofVerbruikBuiten;
    }

    @JsonProperty("brandstofverbruik_gecombineerd")
    public String getBrandstofVerbruikGecombineerd() {
        return brandstofVerbruikGecombineerd;
    }

    @JsonProperty("brandstofverbruik_gecombineerd")
    public void setBrandstofVerbruikGecombineerd(String brandstofVerbruikGecombineerd) {
        this.brandstofVerbruikGecombineerd = brandstofVerbruikGecombineerd;
    }

    @JsonProperty("brandstofverbruik_stad")
    public String getBrandstofVerbruikStad() {
        return brandstofVerbruikStad;
    }

    @JsonProperty("brandstofverbruik_stad")
    public void setBrandstofVerbruikStad(String brandstofVerbruikStad) {
        this.brandstofVerbruikStad = brandstofVerbruikStad;
    }

    @JsonProperty("co2_uitstoot_gecombineerd")
    public String getCo2UitstootGecombineerd() {
        return co2UitstootGecombineerd;
    }

    @JsonProperty("co2_uitstoot_gecombineerd")
    public void setCo2UitstootGecombineerd(String co2UitstootGecombineerd) {
        this.co2UitstootGecombineerd = co2UitstootGecombineerd;
    }

    @JsonProperty("emissiecode_omschrijving")
    public String getEmissiecodeOmschrijving() {
        return emissiecodeOmschrijving;
    }

    @JsonProperty("emissiecode_omschrijving")
    public void setEmissiecodeOmschrijving(String emissiecodeOmschrijving) {
        this.emissiecodeOmschrijving = emissiecodeOmschrijving;
    }

    @JsonProperty("geluidsniveau_stationair")
    public String getGeluidsniveauStationair() {
        return geluidsniveauStationair;
    }

    @JsonProperty("geluidsniveau_stationair")
    public void setGeluidsniveauStationair(String geluidsniveauStationair) {
        this.geluidsniveauStationair = geluidsniveauStationair;
    }

    @JsonProperty("kenteken")
    public String getKenteken() {
        return kenteken;
    }

    @JsonProperty("kenteken")
    public void setKenteken(String kenteken) {
        this.kenteken = kenteken;
    }

    @JsonProperty("milieuklasse_eg_goedkeuring_licht")
    public String getMilieuklasseEgGoedkeuringLicht() {
        return milieuklasseEgGoedkeuringLicht;
    }

    @JsonProperty("milieuklasse_eg_goedkeuring_licht")
    public void setMilieuklasseEgGoedkeuringLicht(String milieuklasseEgGoedkeuringLicht) {
        this.milieuklasseEgGoedkeuringLicht = milieuklasseEgGoedkeuringLicht;
    }

    @JsonProperty("nettomaximumvermogen")
    public String getNettomaximumvermogen() {
        return nettomaximumvermogen;
    }

    @JsonProperty("nettomaximumvermogen")
    public void setNettomaximumvermogen(String nettomaximumvermogen) {
        this.nettomaximumvermogen = nettomaximumvermogen;
    }

    @JsonProperty("toerental_geluidsniveau")
    public String getToerentalGeluidsniveau() {
        return toerentalGeluidsniveau;
    }

    @JsonProperty("toerental_geluidsniveau")
    public void setToerentalGeluidsniveau(String toerentalGeluidsniveau) {
        this.toerentalGeluidsniveau = toerentalGeluidsniveau;
    }

    //</editor-fold>
}
