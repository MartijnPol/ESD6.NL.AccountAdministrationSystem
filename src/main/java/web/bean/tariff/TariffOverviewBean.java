package web.bean.tariff;

import main.domain.Tariff;
import main.domain.enums.RoadType;
import main.service.TariffService;
import web.core.helper.FrontendHelper;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@ViewScoped
public class TariffOverviewBean implements Serializable {

    @Inject
    private TariffService tariffService;

    private List<Tariff> tariffs;
    private List<Tariff> filteredTariffs;
    private Tariff selectedTariff;
    private List<RoadType> roadTypes;
    private RoadType selectedRoadType;

    private double tariffInEuro;
    private boolean ridingDuringRushHour;

    private Tariff tariff;
    private Long id;
    private Map<String, Double> carLabels;
    private Map<String, Double> carFuels;
    private Map<String, Double> rushHourAdditions;

    @PostConstruct
    public void init() {
        this.roadTypes = Arrays.asList(RoadType.values());
        this.tariffs = this.tariffService.findAll();
        this.carLabels = new HashMap<>();
        this.carFuels = new HashMap<>();
        this.rushHourAdditions = new HashMap<>();
        this.tariff = this.tariffs.get(0);
        this.carLabels.putAll(this.tariff.getCarLabels());
        this.carFuels.putAll(this.tariff.getCarFuels());
        this.rushHourAdditions.putAll(this.tariff.getRushHourAdditions());
    }

    public void onCarLabelCellEdit(Map.Entry<String, Double> entry) {
        if (!entry.getKey().isEmpty() && entry.getValue() != null) {
            this.carLabels.replace(entry.getKey(), entry.getValue());
            this.tariff.setCarLabels(this.carLabels);
            this.tariffService.createOrUpdate(this.tariff);
            FrontendHelper.displaySuccessSmallMessage("U heeft het opslagpercentage van dit label succesvol aangepast!");
        }
    }

    public void onCarFuelCellEdit(Map.Entry<String, Double> entry) {
        if (!entry.getKey().isEmpty() && entry.getValue() != null) {
            this.carFuels.replace(entry.getKey(), entry.getValue());
            this.tariff.setCarFuels(this.carFuels);
            this.tariffService.createOrUpdate(this.tariff);
            FrontendHelper.displaySuccessSmallMessage("U heeft het opslagpercentage van dit brandstoftype succesvol gewijzigd.");
        }
    }

    public void onRushHourAdditionsCellEdit(Map.Entry<String, Double> entry) {
        if (!entry.getKey().isEmpty() && entry.getValue() != null) {
            this.rushHourAdditions.replace(entry.getKey(), entry.getValue());
            this.tariff.setRushHourAdditions(this.rushHourAdditions);
            this.tariffService.createOrUpdate(this.tariff);
            FrontendHelper.displaySuccessSmallMessage("U heeft het opslagpercentage van dit brandstoftype succesvol gewijzigd.");
        }
    }

    public void onCellEdit(Tariff tariff) {
        if (tariff.getTariffInEuro() >= 0) {
            Tariff foundTariff = tariffService.findById(tariff.getId());
            foundTariff.setTariffInEuro(tariff.getTariffInEuro());
            tariffService.createOrUpdate(foundTariff);
        } else {
            FrontendHelper.displayErrorSmallMessage("Helaas", "Vul een positief tarief in.");
        }
        FrontendHelper.displaySuccessSmallMessage("Succes!", "Tarief is succesvol gewijzigd.");
    }

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public List<Tariff> getTariffs() {
        return tariffs;
    }

    public void setTariffs(List<Tariff> tariffs) {
        this.tariffs = tariffs;
    }

    public List<Tariff> getFilteredTariffs() {
        return filteredTariffs;
    }

    public void setFilteredTariffs(List<Tariff> filteredTariffs) {
        this.filteredTariffs = filteredTariffs;
    }

    public double getTariffInEuro() {
        return tariffInEuro;
    }

    public void setTariffInEuro(double tariffInEuro) {
        this.tariffInEuro = tariffInEuro;
    }

    public boolean isRidingDuringRushHour() {
        return this.ridingDuringRushHour;
    }

    public void setRidingDuringRushHour(boolean ridingDuringRushHour) {
        this.ridingDuringRushHour = ridingDuringRushHour;
    }

    public Tariff getSelectedTariff() {
        return selectedTariff;
    }

    public void setSelectedTariff(Tariff selectedTariff) {
        this.selectedTariff = selectedTariff;
    }

    public List<RoadType> getRoadTypes() {
        return roadTypes;
    }

    public void setRoadTypes(List<RoadType> roadTypes) {
        this.roadTypes = roadTypes;
    }

    public RoadType getSelectedRoadType() {
        return selectedRoadType;
    }

    public void setSelectedRoadType(RoadType selectedRoadType) {
        this.selectedRoadType = selectedRoadType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    public Map<String, Double> getCarLabels() {
        return carLabels;
    }

    public void setCarLabels(Map<String, Double> carLabels) {
        this.carLabels = carLabels;
    }

    public Map<String, Double> getCarFuels() {
        return carFuels;
    }

    public void setCarFuels(Map<String, Double> carFuels) {
        this.carFuels = carFuels;
    }

    public Map<String, Double> getRushHourAdditions() {
        return rushHourAdditions;
    }

    public void setRushHourAdditions(Map<String, Double> rushHourAdditions) {
        this.rushHourAdditions = rushHourAdditions;
    }

    //</editor-fold>

}
