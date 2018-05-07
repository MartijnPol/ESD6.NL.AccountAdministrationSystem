package main.domain;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.Map;

@XmlRootElement
@Entity
public class RDW extends BaseEntity {

    private String aantal_cilinders;
    private String aantal_deuren;
    private String aantal_rolstoelplaatsen;
    private String aantal_wielen;
    private String aantal_zitplaatsen;
    private String afstand_hart_koppeling_tot_achterzijde_voertuig;
    private String afstand_voorzijde_voertuig_tot_hart_koppeling;
    private String api_gekentekende_voertuigen_assen;
    private String api_gekentekende_voertuigen_brandstof;
    private String api_gekentekende_voertuigen_carrosserie;
    private String api_gekentekende_voertuigen_carrosserie_specifiek;
    private String api_gekentekende_voertuigen_voertuigklasse;
    private String breedte;
    private String bruto_bpm;
    private String catalogusprijs;
    private String cilinderinhoud;
    private String datum_eerste_afgifte_nederland;
    private String datum_eerste_toelating;
    private String datum_tenaamstelling;
    private String eerste_kleur;
    private String europese_voertuigcategorie;
    private String export_indicator;
    private String handelsbenaming;
    private String inrichting;
    private String kenteken;
    private String lengte;
    private String massa_ledig_voertuig;
    private String massa_rijklaar;
    private String maximum_massa_samenstelling;
    private String maximum_massa_trekken_ongeremd;
    private String maximum_trekken_massa_geremd;
    private String merk;
    private String openstaande_terugroepactie_indicator;
    private String plaats_chassisnummer;
    private String taxi_indicator;
    private String technische_max_massa_voertuig;
    private String toegestane_maximum_massa_voertuig;
    private String tweede_kleur;
    private String typegoedkeuringsnummer;
    private String uitvoering;
    private String variant;
    private String vermogen_massarijklaar;
    private String vervaldatum_apk;
    private String voertuigsoort;
    private String volgnummer_wijziging_eu_typegoedkeuring;
    private String wacht_op_keuren;
    private String wam_verzekerd;
    private String wielbasis;
    private String zuinigheidslabel;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">


    public String getAantal_cilinders() {
        return aantal_cilinders;
    }

    public void setAantal_cilinders(String aantal_cilinders) {
        this.aantal_cilinders = aantal_cilinders;
    }

    public String getAantal_deuren() {
        return aantal_deuren;
    }

    public void setAantal_deuren(String aantal_deuren) {
        this.aantal_deuren = aantal_deuren;
    }

    public String getAantal_rolstoelplaatsen() {
        return aantal_rolstoelplaatsen;
    }

    public void setAantal_rolstoelplaatsen(String aantal_rolstoelplaatsen) {
        this.aantal_rolstoelplaatsen = aantal_rolstoelplaatsen;
    }

    public String getAantal_wielen() {
        return aantal_wielen;
    }

    public void setAantal_wielen(String aantal_wielen) {
        this.aantal_wielen = aantal_wielen;
    }

    public String getAantal_zitplaatsen() {
        return aantal_zitplaatsen;
    }

    public void setAantal_zitplaatsen(String aantal_zitplaatsen) {
        this.aantal_zitplaatsen = aantal_zitplaatsen;
    }

    public String getAfstand_hart_koppeling_tot_achterzijde_voertuig() {
        return afstand_hart_koppeling_tot_achterzijde_voertuig;
    }

    public void setAfstand_hart_koppeling_tot_achterzijde_voertuig(String afstand_hart_koppeling_tot_achterzijde_voertuig) {
        this.afstand_hart_koppeling_tot_achterzijde_voertuig = afstand_hart_koppeling_tot_achterzijde_voertuig;
    }

    public String getAfstand_voorzijde_voertuig_tot_hart_koppeling() {
        return afstand_voorzijde_voertuig_tot_hart_koppeling;
    }

    public void setAfstand_voorzijde_voertuig_tot_hart_koppeling(String afstand_voorzijde_voertuig_tot_hart_koppeling) {
        this.afstand_voorzijde_voertuig_tot_hart_koppeling = afstand_voorzijde_voertuig_tot_hart_koppeling;
    }

    public String getApi_gekentekende_voertuigen_assen() {
        return api_gekentekende_voertuigen_assen;
    }

    public void setApi_gekentekende_voertuigen_assen(String api_gekentekende_voertuigen_assen) {
        this.api_gekentekende_voertuigen_assen = api_gekentekende_voertuigen_assen;
    }

    public String getApi_gekentekende_voertuigen_brandstof() {
        return api_gekentekende_voertuigen_brandstof;
    }

    public void setApi_gekentekende_voertuigen_brandstof(String api_gekentekende_voertuigen_brandstof) {
        this.api_gekentekende_voertuigen_brandstof = api_gekentekende_voertuigen_brandstof;
    }

    public String getApi_gekentekende_voertuigen_carrosserie() {
        return api_gekentekende_voertuigen_carrosserie;
    }

    public void setApi_gekentekende_voertuigen_carrosserie(String api_gekentekende_voertuigen_carrosserie) {
        this.api_gekentekende_voertuigen_carrosserie = api_gekentekende_voertuigen_carrosserie;
    }

    public String getApi_gekentekende_voertuigen_carrosserie_specifiek() {
        return api_gekentekende_voertuigen_carrosserie_specifiek;
    }

    public void setApi_gekentekende_voertuigen_carrosserie_specifiek(String api_gekentekende_voertuigen_carrosserie_specifiek) {
        this.api_gekentekende_voertuigen_carrosserie_specifiek = api_gekentekende_voertuigen_carrosserie_specifiek;
    }

    public String getApi_gekentekende_voertuigen_voertuigklasse() {
        return api_gekentekende_voertuigen_voertuigklasse;
    }

    public void setApi_gekentekende_voertuigen_voertuigklasse(String api_gekentekende_voertuigen_voertuigklasse) {
        this.api_gekentekende_voertuigen_voertuigklasse = api_gekentekende_voertuigen_voertuigklasse;
    }

    public String getBreedte() {
        return breedte;
    }

    public void setBreedte(String breedte) {
        this.breedte = breedte;
    }

    public String getBruto_bpm() {
        return bruto_bpm;
    }

    public void setBruto_bpm(String bruto_bpm) {
        this.bruto_bpm = bruto_bpm;
    }

    public String getCatalogusprijs() {
        return catalogusprijs;
    }

    public void setCatalogusprijs(String catalogusprijs) {
        this.catalogusprijs = catalogusprijs;
    }

    public String getCilinderinhoud() {
        return cilinderinhoud;
    }

    public void setCilinderinhoud(String cilinderinhoud) {
        this.cilinderinhoud = cilinderinhoud;
    }

    public String getDatum_eerste_afgifte_nederland() {
        return datum_eerste_afgifte_nederland;
    }

    public void setDatum_eerste_afgifte_nederland(String datum_eerste_afgifte_nederland) {
        this.datum_eerste_afgifte_nederland = datum_eerste_afgifte_nederland;
    }

    public String getDatum_eerste_toelating() {
        return datum_eerste_toelating;
    }

    public void setDatum_eerste_toelating(String datum_eerste_toelating) {
        this.datum_eerste_toelating = datum_eerste_toelating;
    }

    public String getDatum_tenaamstelling() {
        return datum_tenaamstelling;
    }

    public void setDatum_tenaamstelling(String datum_tenaamstelling) {
        this.datum_tenaamstelling = datum_tenaamstelling;
    }

    public String getEerste_kleur() {
        return eerste_kleur;
    }

    public void setEerste_kleur(String eerste_kleur) {
        this.eerste_kleur = eerste_kleur;
    }

    public String getEuropese_voertuigcategorie() {
        return europese_voertuigcategorie;
    }

    public void setEuropese_voertuigcategorie(String europese_voertuigcategorie) {
        this.europese_voertuigcategorie = europese_voertuigcategorie;
    }

    public String getExport_indicator() {
        return export_indicator;
    }

    public void setExport_indicator(String export_indicator) {
        this.export_indicator = export_indicator;
    }

    public String getHandelsbenaming() {
        return handelsbenaming;
    }

    public void setHandelsbenaming(String handelsbenaming) {
        this.handelsbenaming = handelsbenaming;
    }

    public String getInrichting() {
        return inrichting;
    }

    public void setInrichting(String inrichting) {
        this.inrichting = inrichting;
    }

    public String getKenteken() {
        return kenteken;
    }

    public void setKenteken(String kenteken) {
        this.kenteken = kenteken;
    }

    public String getLengte() {
        return lengte;
    }

    public void setLengte(String lengte) {
        this.lengte = lengte;
    }

    public String getMassa_ledig_voertuig() {
        return massa_ledig_voertuig;
    }

    public void setMassa_ledig_voertuig(String massa_ledig_voertuig) {
        this.massa_ledig_voertuig = massa_ledig_voertuig;
    }

    public String getMassa_rijklaar() {
        return massa_rijklaar;
    }

    public void setMassa_rijklaar(String massa_rijklaar) {
        this.massa_rijklaar = massa_rijklaar;
    }

    public String getMaximum_massa_samenstelling() {
        return maximum_massa_samenstelling;
    }

    public void setMaximum_massa_samenstelling(String maximum_massa_samenstelling) {
        this.maximum_massa_samenstelling = maximum_massa_samenstelling;
    }

    public String getMaximum_massa_trekken_ongeremd() {
        return maximum_massa_trekken_ongeremd;
    }

    public void setMaximum_massa_trekken_ongeremd(String maximum_massa_trekken_ongeremd) {
        this.maximum_massa_trekken_ongeremd = maximum_massa_trekken_ongeremd;
    }

    public String getMaximum_trekken_massa_geremd() {
        return maximum_trekken_massa_geremd;
    }

    public void setMaximum_trekken_massa_geremd(String maximum_trekken_massa_geremd) {
        this.maximum_trekken_massa_geremd = maximum_trekken_massa_geremd;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getOpenstaande_terugroepactie_indicator() {
        return openstaande_terugroepactie_indicator;
    }

    public void setOpenstaande_terugroepactie_indicator(String openstaande_terugroepactie_indicator) {
        this.openstaande_terugroepactie_indicator = openstaande_terugroepactie_indicator;
    }

    public String getPlaats_chassisnummer() {
        return plaats_chassisnummer;
    }

    public void setPlaats_chassisnummer(String plaats_chassisnummer) {
        this.plaats_chassisnummer = plaats_chassisnummer;
    }

    public String getTaxi_indicator() {
        return taxi_indicator;
    }

    public void setTaxi_indicator(String taxi_indicator) {
        this.taxi_indicator = taxi_indicator;
    }

    public String getTechnische_max_massa_voertuig() {
        return technische_max_massa_voertuig;
    }

    public void setTechnische_max_massa_voertuig(String technische_max_massa_voertuig) {
        this.technische_max_massa_voertuig = technische_max_massa_voertuig;
    }

    public String getToegestane_maximum_massa_voertuig() {
        return toegestane_maximum_massa_voertuig;
    }

    public void setToegestane_maximum_massa_voertuig(String toegestane_maximum_massa_voertuig) {
        this.toegestane_maximum_massa_voertuig = toegestane_maximum_massa_voertuig;
    }

    public String getTweede_kleur() {
        return tweede_kleur;
    }

    public void setTweede_kleur(String tweede_kleur) {
        this.tweede_kleur = tweede_kleur;
    }

    public String getTypegoedkeuringsnummer() {
        return typegoedkeuringsnummer;
    }

    public void setTypegoedkeuringsnummer(String typegoedkeuringsnummer) {
        this.typegoedkeuringsnummer = typegoedkeuringsnummer;
    }

    public String getUitvoering() {
        return uitvoering;
    }

    public void setUitvoering(String uitvoering) {
        this.uitvoering = uitvoering;
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

    public String getVermogen_massarijklaar() {
        return vermogen_massarijklaar;
    }

    public void setVermogen_massarijklaar(String vermogen_massarijklaar) {
        this.vermogen_massarijklaar = vermogen_massarijklaar;
    }

    public String getVervaldatum_apk() {
        return vervaldatum_apk;
    }

    public void setVervaldatum_apk(String vervaldatum_apk) {
        this.vervaldatum_apk = vervaldatum_apk;
    }

    public String getVoertuigsoort() {
        return voertuigsoort;
    }

    public void setVoertuigsoort(String voertuigsoort) {
        this.voertuigsoort = voertuigsoort;
    }

    public String getVolgnummer_wijziging_eu_typegoedkeuring() {
        return volgnummer_wijziging_eu_typegoedkeuring;
    }

    public void setVolgnummer_wijziging_eu_typegoedkeuring(String volgnummer_wijziging_eu_typegoedkeuring) {
        this.volgnummer_wijziging_eu_typegoedkeuring = volgnummer_wijziging_eu_typegoedkeuring;
    }

    public String getWacht_op_keuren() {
        return wacht_op_keuren;
    }

    public void setWacht_op_keuren(String wacht_op_keuren) {
        this.wacht_op_keuren = wacht_op_keuren;
    }

    public String getWam_verzekerd() {
        return wam_verzekerd;
    }

    public void setWam_verzekerd(String wam_verzekerd) {
        this.wam_verzekerd = wam_verzekerd;
    }

    public String getWielbasis() {
        return wielbasis;
    }

    public void setWielbasis(String wielbasis) {
        this.wielbasis = wielbasis;
    }

    public String getZuinigheidslabel() {
        return zuinigheidslabel;
    }

    public void setZuinigheidslabel(String zuinigheidslabel) {
        this.zuinigheidslabel = zuinigheidslabel;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    //</editor-fold>
}