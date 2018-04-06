package web.bean.tariff;

import main.domain.Tariff;
import main.service.TariffService;
import org.primefaces.event.CellEditEvent;
import web.core.helper.FrontendHelper;
import web.core.helper.RedirectHelper;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class TariffOverviewBean implements Serializable {

    @Inject
    private TariffService tariffService;

    private List<Tariff> tariffs;
    private List<Tariff> filteredTariffs;

    @PostConstruct
    public void init() {
        this.tariffs = this.tariffService.findAll();
    }

    public void create(){
        RedirectHelper.redirect("/pages/tariff/createTariff.xhtml");
    }

    public void onCellEdit(Tariff tariff) {
        if (tariff.getTariffInEuro() >= 0) {
            Tariff foundTariff = tariffService.findById(tariff.getId());
            foundTariff.setRidingDuringRushHour(tariff.getRidingDuringRushHour());
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

    public void setTariffs(List<Tariff> tariffList) {
        this.tariffs = tariffList;
    }

    public List<Tariff> getFilteredTariffs() {
        return filteredTariffs;
    }

    public void setFilteredTariffs(List<Tariff> filteredTariffs) {
        this.filteredTariffs = filteredTariffs;
    }
    //</editor-fold>
}
