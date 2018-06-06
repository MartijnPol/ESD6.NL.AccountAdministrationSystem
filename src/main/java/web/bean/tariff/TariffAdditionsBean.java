package web.bean.tariff;

import main.domain.Tariff;
import main.service.TariffService;
import org.primefaces.event.CellEditEvent;
import web.bean.BaseBean;
import web.core.helper.FrontendHelper;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Thom van de Pas on 30-5-2018
 */
@Named
@ViewScoped
public class TariffAdditionsBean extends BaseBean {

    @Inject
    private TariffService tariffService;

    private Tariff tariff;
    private Long id;
    private Map<String, Double> carLabels;
    private Map<String, Double> carFuels;

    @Override
    public void init() {
        this.carLabels = new HashMap<>();
        this.carFuels = new HashMap<>();
        this.tariff = this.tariffService.findById(id);
        this.carLabels.putAll(this.tariff.getCarLabels());
        this.carFuels.putAll(this.tariff.getCarFuels());
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

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
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

    //</editor-fold>
}
