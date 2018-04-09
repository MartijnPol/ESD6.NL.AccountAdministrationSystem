package main.domain;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "aantal_cilinders",
        "aantal_deuren",
        "aantal_rolstoelplaatsen",
        "aantal_wielen",
        "aantal_zitplaatsen",
        "afstand_hart_koppeling_tot_achterzijde_voertuig",
        "afstand_voorzijde_voertuig_tot_hart_koppeling",
        "api_gekentekende_voertuigen_assen",
        "api_gekentekende_voertuigen_brandstof",
        "api_gekentekende_voertuigen_carrosserie",
        "api_gekentekende_voertuigen_carrosserie_specifiek",
        "api_gekentekende_voertuigen_voertuigklasse",
        "breedte",
        "bruto_bpm",
        "catalogusprijs",
        "cilinderinhoud",
        "datum_eerste_afgifte_nederland",
        "datum_eerste_toelating",
        "datum_tenaamstelling",
        "eerste_kleur",
        "europese_voertuigcategorie",
        "export_indicator",
        "handelsbenaming",
        "inrichting",
        "kenteken",
        "lengte",
        "massa_ledig_voertuig",
        "massa_rijklaar",
        "maximum_massa_samenstelling",
        "maximum_massa_trekken_ongeremd",
        "maximum_trekken_massa_geremd",
        "merk",
        "openstaande_terugroepactie_indicator",
        "plaats_chassisnummer",
        "taxi_indicator",
        "technische_max_massa_voertuig",
        "toegestane_maximum_massa_voertuig",
        "tweede_kleur",
        "typegoedkeuringsnummer",
        "uitvoering",
        "variant",
        "vermogen_massarijklaar",
        "vervaldatum_apk",
        "voertuigsoort",
        "volgnummer_wijziging_eu_typegoedkeuring",
        "wacht_op_keuren",
        "wam_verzekerd",
        "wielbasis",
        "zuinigheidslabel"
})

@XmlRootElement
@Entity
public class RDW extends BaseEntity {

    @JsonProperty("aantal_cilinders")
    private String aantalCilinders;
    @JsonProperty("aantal_deuren")
    private String aantalDeuren;
    @JsonProperty("aantal_rolstoelplaatsen")
    private String aantalRolstoelplaatsen;
    @JsonProperty("aantal_wielen")
    private String aantalWielen;
    @JsonProperty("aantal_zitplaatsen")
    private String aantalZitplaatsen;
    @JsonProperty("afstand_hart_koppeling_tot_achterzijde_voertuig")
    private String afstandHartKoppelingTotAchterzijdeVoertuig;
    @JsonProperty("afstand_voorzijde_voertuig_tot_hart_koppeling")
    private String afstandVoorzijdeVoertuigTotHartKoppeling;
    @JsonProperty("api_gekentekende_voertuigen_assen")
    private String apiGekentekendeVoertuigenAssen;
    @JsonProperty("api_gekentekende_voertuigen_brandstof")
    private String apiGekentekendeVoertuigenBrandstof;
    @JsonProperty("api_gekentekende_voertuigen_carrosserie")
    private String apiGekentekendeVoertuigenCarrosserie;
    @JsonProperty("api_gekentekende_voertuigen_carrosserie_specifiek")
    private String apiGekentekendeVoertuigenCarrosserieSpecifiek;
    @JsonProperty("api_gekentekende_voertuigen_voertuigklasse")
    private String apiGekentekendeVoertuigenVoertuigklasse;
    @JsonProperty("breedte")
    private String breedte;
    @JsonProperty("bruto_bpm")
    private String brutoBpm;
    @JsonProperty("catalogusprijs")
    private String catalogusprijs;
    @JsonProperty("cilinderinhoud")
    private String cilinderinhoud;
    @JsonProperty("datum_eerste_afgifte_nederland")
    private String datumEersteAfgifteNederland;
    @JsonProperty("datum_eerste_toelating")
    private String datumEersteToelating;
    @JsonProperty("datum_tenaamstelling")
    private String datumTenaamstelling;
    @JsonProperty("eerste_kleur")
    private String eersteKleur;
    @JsonProperty("europese_voertuigcategorie")
    private String europeseVoertuigcategorie;
    @JsonProperty("export_indicator")
    private String exportIndicator;
    @JsonProperty("handelsbenaming")
    private String handelsbenaming;
    @JsonProperty("inrichting")
    private String inrichting;
    @JsonProperty("kenteken")
    private String kenteken;
    @JsonProperty("lengte")
    private String lengte;
    @JsonProperty("massa_ledig_voertuig")
    private String massaLedigVoertuig;
    @JsonProperty("massa_rijklaar")
    private String massaRijklaar;
    @JsonProperty("maximum_massa_samenstelling")
    private String maximumMassaSamenstelling;
    @JsonProperty("maximum_massa_trekken_ongeremd")
    private String maximumMassaTrekkenOngeremd;
    @JsonProperty("maximum_trekken_massa_geremd")
    private String maximumTrekkenMassaGeremd;
    @JsonProperty("merk")
    private String merk;
    @JsonProperty("openstaande_terugroepactie_indicator")
    private String openstaandeTerugroepactieIndicator;
    @JsonProperty("plaats_chassisnummer")
    private String plaatsChassisnummer;
    @JsonProperty("taxi_indicator")
    private String taxiIndicator;
    @JsonProperty("technische_max_massa_voertuig")
    private String technischeMaxMassaVoertuig;
    @JsonProperty("toegestane_maximum_massa_voertuig")
    private String toegestaneMaximumMassaVoertuig;
    @JsonProperty("tweede_kleur")
    private String tweedeKleur;
    @JsonProperty("typegoedkeuringsnummer")
    private String typegoedkeuringsnummer;
    @JsonProperty("uitvoering")
    private String uitvoering;
    @JsonProperty("variant")
    private String variant;
    @JsonProperty("vermogen_massarijklaar")
    private String vermogenMassarijklaar;
    @JsonProperty("vervaldatum_apk")
    private String vervaldatumApk;
    @JsonProperty("voertuigsoort")
    private String voertuigsoort;
    @JsonProperty("volgnummer_wijziging_eu_typegoedkeuring")
    private String volgnummerWijzigingEuTypegoedkeuring;
    @JsonProperty("wacht_op_keuren")
    private String wachtOpKeuren;
    @JsonProperty("wam_verzekerd")
    private String wamVerzekerd;
    @JsonProperty("wielbasis")
    private String wielbasis;
    @JsonProperty("zuinigheidslabel")
    private String zuinigheidslabel;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("aantal_cilinders")
    public String getAantalCilinders() {
        return aantalCilinders;
    }

    @JsonProperty("aantal_cilinders")
    public void setAantalCilinders(String aantalCilinders) {
        this.aantalCilinders = aantalCilinders;
    }

    @JsonProperty("aantal_deuren")
    public String getAantalDeuren() {
        return aantalDeuren;
    }

    @JsonProperty("aantal_deuren")
    public void setAantalDeuren(String aantalDeuren) {
        this.aantalDeuren = aantalDeuren;
    }

    @JsonProperty("aantal_rolstoelplaatsen")
    public String getAantalRolstoelplaatsen() {
        return aantalRolstoelplaatsen;
    }

    @JsonProperty("aantal_rolstoelplaatsen")
    public void setAantalRolstoelplaatsen(String aantalRolstoelplaatsen) {
        this.aantalRolstoelplaatsen = aantalRolstoelplaatsen;
    }

    @JsonProperty("aantal_wielen")
    public String getAantalWielen() {
        return aantalWielen;
    }

    @JsonProperty("aantal_wielen")
    public void setAantalWielen(String aantalWielen) {
        this.aantalWielen = aantalWielen;
    }

    @JsonProperty("aantal_zitplaatsen")
    public String getAantalZitplaatsen() {
        return aantalZitplaatsen;
    }

    @JsonProperty("aantal_zitplaatsen")
    public void setAantalZitplaatsen(String aantalZitplaatsen) {
        this.aantalZitplaatsen = aantalZitplaatsen;
    }

    @JsonProperty("afstand_hart_koppeling_tot_achterzijde_voertuig")
    public String getAfstandHartKoppelingTotAchterzijdeVoertuig() {
        return afstandHartKoppelingTotAchterzijdeVoertuig;
    }

    @JsonProperty("afstand_hart_koppeling_tot_achterzijde_voertuig")
    public void setAfstandHartKoppelingTotAchterzijdeVoertuig(String afstandHartKoppelingTotAchterzijdeVoertuig) {
        this.afstandHartKoppelingTotAchterzijdeVoertuig = afstandHartKoppelingTotAchterzijdeVoertuig;
    }

    @JsonProperty("afstand_voorzijde_voertuig_tot_hart_koppeling")
    public String getAfstandVoorzijdeVoertuigTotHartKoppeling() {
        return afstandVoorzijdeVoertuigTotHartKoppeling;
    }

    @JsonProperty("afstand_voorzijde_voertuig_tot_hart_koppeling")
    public void setAfstandVoorzijdeVoertuigTotHartKoppeling(String afstandVoorzijdeVoertuigTotHartKoppeling) {
        this.afstandVoorzijdeVoertuigTotHartKoppeling = afstandVoorzijdeVoertuigTotHartKoppeling;
    }

    @JsonProperty("api_gekentekende_voertuigen_assen")
    public String getApiGekentekendeVoertuigenAssen() {
        return apiGekentekendeVoertuigenAssen;
    }

    @JsonProperty("api_gekentekende_voertuigen_assen")
    public void setApiGekentekendeVoertuigenAssen(String apiGekentekendeVoertuigenAssen) {
        this.apiGekentekendeVoertuigenAssen = apiGekentekendeVoertuigenAssen;
    }

    @JsonProperty("api_gekentekende_voertuigen_brandstof")
    public String getApiGekentekendeVoertuigenBrandstof() {
        return apiGekentekendeVoertuigenBrandstof;
    }

    @JsonProperty("api_gekentekende_voertuigen_brandstof")
    public void setApiGekentekendeVoertuigenBrandstof(String apiGekentekendeVoertuigenBrandstof) {
        this.apiGekentekendeVoertuigenBrandstof = apiGekentekendeVoertuigenBrandstof;
    }

    @JsonProperty("api_gekentekende_voertuigen_carrosserie")
    public String getApiGekentekendeVoertuigenCarrosserie() {
        return apiGekentekendeVoertuigenCarrosserie;
    }

    @JsonProperty("api_gekentekende_voertuigen_carrosserie")
    public void setApiGekentekendeVoertuigenCarrosserie(String apiGekentekendeVoertuigenCarrosserie) {
        this.apiGekentekendeVoertuigenCarrosserie = apiGekentekendeVoertuigenCarrosserie;
    }

    @JsonProperty("api_gekentekende_voertuigen_carrosserie_specifiek")
    public String getApiGekentekendeVoertuigenCarrosserieSpecifiek() {
        return apiGekentekendeVoertuigenCarrosserieSpecifiek;
    }

    @JsonProperty("api_gekentekende_voertuigen_carrosserie_specifiek")
    public void setApiGekentekendeVoertuigenCarrosserieSpecifiek(String apiGekentekendeVoertuigenCarrosserieSpecifiek) {
        this.apiGekentekendeVoertuigenCarrosserieSpecifiek = apiGekentekendeVoertuigenCarrosserieSpecifiek;
    }

    @JsonProperty("api_gekentekende_voertuigen_voertuigklasse")
    public String getApiGekentekendeVoertuigenVoertuigklasse() {
        return apiGekentekendeVoertuigenVoertuigklasse;
    }

    @JsonProperty("api_gekentekende_voertuigen_voertuigklasse")
    public void setApiGekentekendeVoertuigenVoertuigklasse(String apiGekentekendeVoertuigenVoertuigklasse) {
        this.apiGekentekendeVoertuigenVoertuigklasse = apiGekentekendeVoertuigenVoertuigklasse;
    }

    @JsonProperty("breedte")
    public String getBreedte() {
        return breedte;
    }

    @JsonProperty("breedte")
    public void setBreedte(String breedte) {
        this.breedte = breedte;
    }

    @JsonProperty("bruto_bpm")
    public String getBrutoBpm() {
        return brutoBpm;
    }

    @JsonProperty("bruto_bpm")
    public void setBrutoBpm(String brutoBpm) {
        this.brutoBpm = brutoBpm;
    }

    @JsonProperty("catalogusprijs")
    public String getCatalogusprijs() {
        return catalogusprijs;
    }

    @JsonProperty("catalogusprijs")
    public void setCatalogusprijs(String catalogusprijs) {
        this.catalogusprijs = catalogusprijs;
    }

    @JsonProperty("cilinderinhoud")
    public String getCilinderinhoud() {
        return cilinderinhoud;
    }

    @JsonProperty("cilinderinhoud")
    public void setCilinderinhoud(String cilinderinhoud) {
        this.cilinderinhoud = cilinderinhoud;
    }

    @JsonProperty("datum_eerste_afgifte_nederland")
    public String getDatumEersteAfgifteNederland() {
        return datumEersteAfgifteNederland;
    }

    @JsonProperty("datum_eerste_afgifte_nederland")
    public void setDatumEersteAfgifteNederland(String datumEersteAfgifteNederland) {
        this.datumEersteAfgifteNederland = datumEersteAfgifteNederland;
    }

    @JsonProperty("datum_eerste_toelating")
    public String getDatumEersteToelating() {
        return datumEersteToelating;
    }

    @JsonProperty("datum_eerste_toelating")
    public void setDatumEersteToelating(String datumEersteToelating) {
        this.datumEersteToelating = datumEersteToelating;
    }

    @JsonProperty("datum_tenaamstelling")
    public String getDatumTenaamstelling() {
        return datumTenaamstelling;
    }

    @JsonProperty("datum_tenaamstelling")
    public void setDatumTenaamstelling(String datumTenaamstelling) {
        this.datumTenaamstelling = datumTenaamstelling;
    }

    @JsonProperty("eerste_kleur")
    public String getEersteKleur() {
        return eersteKleur;
    }

    @JsonProperty("eerste_kleur")
    public void setEersteKleur(String eersteKleur) {
        this.eersteKleur = eersteKleur;
    }

    @JsonProperty("europese_voertuigcategorie")
    public String getEuropeseVoertuigcategorie() {
        return europeseVoertuigcategorie;
    }

    @JsonProperty("europese_voertuigcategorie")
    public void setEuropeseVoertuigcategorie(String europeseVoertuigcategorie) {
        this.europeseVoertuigcategorie = europeseVoertuigcategorie;
    }

    @JsonProperty("export_indicator")
    public String getExportIndicator() {
        return exportIndicator;
    }

    @JsonProperty("export_indicator")
    public void setExportIndicator(String exportIndicator) {
        this.exportIndicator = exportIndicator;
    }

    @JsonProperty("handelsbenaming")
    public String getHandelsbenaming() {
        return handelsbenaming;
    }

    @JsonProperty("handelsbenaming")
    public void setHandelsbenaming(String handelsbenaming) {
        this.handelsbenaming = handelsbenaming;
    }

    @JsonProperty("inrichting")
    public String getInrichting() {
        return inrichting;
    }

    @JsonProperty("inrichting")
    public void setInrichting(String inrichting) {
        this.inrichting = inrichting;
    }

    @JsonProperty("kenteken")
    public String getKenteken() {
        return kenteken;
    }

    @JsonProperty("kenteken")
    public void setKenteken(String kenteken) {
        this.kenteken = kenteken;
    }

    @JsonProperty("lengte")
    public String getLengte() {
        return lengte;
    }

    @JsonProperty("lengte")
    public void setLengte(String lengte) {
        this.lengte = lengte;
    }

    @JsonProperty("massa_ledig_voertuig")
    public String getMassaLedigVoertuig() {
        return massaLedigVoertuig;
    }

    @JsonProperty("massa_ledig_voertuig")
    public void setMassaLedigVoertuig(String massaLedigVoertuig) {
        this.massaLedigVoertuig = massaLedigVoertuig;
    }

    @JsonProperty("massa_rijklaar")
    public String getMassaRijklaar() {
        return massaRijklaar;
    }

    @JsonProperty("massa_rijklaar")
    public void setMassaRijklaar(String massaRijklaar) {
        this.massaRijklaar = massaRijklaar;
    }

    @JsonProperty("maximum_massa_samenstelling")
    public String getMaximumMassaSamenstelling() {
        return maximumMassaSamenstelling;
    }

    @JsonProperty("maximum_massa_samenstelling")
    public void setMaximumMassaSamenstelling(String maximumMassaSamenstelling) {
        this.maximumMassaSamenstelling = maximumMassaSamenstelling;
    }

    @JsonProperty("maximum_massa_trekken_ongeremd")
    public String getMaximumMassaTrekkenOngeremd() {
        return maximumMassaTrekkenOngeremd;
    }

    @JsonProperty("maximum_massa_trekken_ongeremd")
    public void setMaximumMassaTrekkenOngeremd(String maximumMassaTrekkenOngeremd) {
        this.maximumMassaTrekkenOngeremd = maximumMassaTrekkenOngeremd;
    }

    @JsonProperty("maximum_trekken_massa_geremd")
    public String getMaximumTrekkenMassaGeremd() {
        return maximumTrekkenMassaGeremd;
    }

    @JsonProperty("maximum_trekken_massa_geremd")
    public void setMaximumTrekkenMassaGeremd(String maximumTrekkenMassaGeremd) {
        this.maximumTrekkenMassaGeremd = maximumTrekkenMassaGeremd;
    }

    @JsonProperty("merk")
    public String getMerk() {
        return merk;
    }

    @JsonProperty("merk")
    public void setMerk(String merk) {
        this.merk = merk;
    }

    @JsonProperty("openstaande_terugroepactie_indicator")
    public String getOpenstaandeTerugroepactieIndicator() {
        return openstaandeTerugroepactieIndicator;
    }

    @JsonProperty("openstaande_terugroepactie_indicator")
    public void setOpenstaandeTerugroepactieIndicator(String openstaandeTerugroepactieIndicator) {
        this.openstaandeTerugroepactieIndicator = openstaandeTerugroepactieIndicator;
    }

    @JsonProperty("plaats_chassisnummer")
    public String getPlaatsChassisnummer() {
        return plaatsChassisnummer;
    }

    @JsonProperty("plaats_chassisnummer")
    public void setPlaatsChassisnummer(String plaatsChassisnummer) {
        this.plaatsChassisnummer = plaatsChassisnummer;
    }

    @JsonProperty("taxi_indicator")
    public String getTaxiIndicator() {
        return taxiIndicator;
    }

    @JsonProperty("taxi_indicator")
    public void setTaxiIndicator(String taxiIndicator) {
        this.taxiIndicator = taxiIndicator;
    }

    @JsonProperty("technische_max_massa_voertuig")
    public String getTechnischeMaxMassaVoertuig() {
        return technischeMaxMassaVoertuig;
    }

    @JsonProperty("technische_max_massa_voertuig")
    public void setTechnischeMaxMassaVoertuig(String technischeMaxMassaVoertuig) {
        this.technischeMaxMassaVoertuig = technischeMaxMassaVoertuig;
    }

    @JsonProperty("toegestane_maximum_massa_voertuig")
    public String getToegestaneMaximumMassaVoertuig() {
        return toegestaneMaximumMassaVoertuig;
    }

    @JsonProperty("toegestane_maximum_massa_voertuig")
    public void setToegestaneMaximumMassaVoertuig(String toegestaneMaximumMassaVoertuig) {
        this.toegestaneMaximumMassaVoertuig = toegestaneMaximumMassaVoertuig;
    }

    @JsonProperty("tweede_kleur")
    public String getTweedeKleur() {
        return tweedeKleur;
    }

    @JsonProperty("tweede_kleur")
    public void setTweedeKleur(String tweedeKleur) {
        this.tweedeKleur = tweedeKleur;
    }

    @JsonProperty("typegoedkeuringsnummer")
    public String getTypegoedkeuringsnummer() {
        return typegoedkeuringsnummer;
    }

    @JsonProperty("typegoedkeuringsnummer")
    public void setTypegoedkeuringsnummer(String typegoedkeuringsnummer) {
        this.typegoedkeuringsnummer = typegoedkeuringsnummer;
    }

    @JsonProperty("uitvoering")
    public String getUitvoering() {
        return uitvoering;
    }

    @JsonProperty("uitvoering")
    public void setUitvoering(String uitvoering) {
        this.uitvoering = uitvoering;
    }

    @JsonProperty("variant")
    public String getVariant() {
        return variant;
    }

    @JsonProperty("variant")
    public void setVariant(String variant) {
        this.variant = variant;
    }

    @JsonProperty("vermogen_massarijklaar")
    public String getVermogenMassarijklaar() {
        return vermogenMassarijklaar;
    }

    @JsonProperty("vermogen_massarijklaar")
    public void setVermogenMassarijklaar(String vermogenMassarijklaar) {
        this.vermogenMassarijklaar = vermogenMassarijklaar;
    }

    @JsonProperty("vervaldatum_apk")
    public String getVervaldatumApk() {
        return vervaldatumApk;
    }

    @JsonProperty("vervaldatum_apk")
    public void setVervaldatumApk(String vervaldatumApk) {
        this.vervaldatumApk = vervaldatumApk;
    }

    @JsonProperty("voertuigsoort")
    public String getVoertuigsoort() {
        return voertuigsoort;
    }

    @JsonProperty("voertuigsoort")
    public void setVoertuigsoort(String voertuigsoort) {
        this.voertuigsoort = voertuigsoort;
    }

    @JsonProperty("volgnummer_wijziging_eu_typegoedkeuring")
    public String getVolgnummerWijzigingEuTypegoedkeuring() {
        return volgnummerWijzigingEuTypegoedkeuring;
    }

    @JsonProperty("volgnummer_wijziging_eu_typegoedkeuring")
    public void setVolgnummerWijzigingEuTypegoedkeuring(String volgnummerWijzigingEuTypegoedkeuring) {
        this.volgnummerWijzigingEuTypegoedkeuring = volgnummerWijzigingEuTypegoedkeuring;
    }

    @JsonProperty("wacht_op_keuren")
    public String getWachtOpKeuren() {
        return wachtOpKeuren;
    }

    @JsonProperty("wacht_op_keuren")
    public void setWachtOpKeuren(String wachtOpKeuren) {
        this.wachtOpKeuren = wachtOpKeuren;
    }

    @JsonProperty("wam_verzekerd")
    public String getWamVerzekerd() {
        return wamVerzekerd;
    }

    @JsonProperty("wam_verzekerd")
    public void setWamVerzekerd(String wamVerzekerd) {
        this.wamVerzekerd = wamVerzekerd;
    }

    @JsonProperty("wielbasis")
    public String getWielbasis() {
        return wielbasis;
    }

    @JsonProperty("wielbasis")
    public void setWielbasis(String wielbasis) {
        this.wielbasis = wielbasis;
    }

    @JsonProperty("zuinigheidslabel")
    public String getZuinigheidslabel() {
        return zuinigheidslabel;
    }

    @JsonProperty("zuinigheidslabel")
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

}