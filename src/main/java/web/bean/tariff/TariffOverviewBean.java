package web.bean.tariff;

import main.domain.Tariff;
import main.service.TariffService;
import org.primefaces.event.SelectEvent;
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
    private Tariff selectedTariff;

    private double tariffInEuro;
    private boolean ridingDuringRushHour;

    @PostConstruct
    public void init() {
        this.tariffs = this.tariffService.findAll();
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

    public void create() {
        if (tariffInEuro != 0) {
            Tariff newTariff = new Tariff(this.tariffInEuro, this.ridingDuringRushHour);
            tariffService.createOrUpdate(newTariff);
            this.tariffs = tariffService.findAll();
            FrontendHelper.displaySuccessSmallMessage("Het tarief is succesvol toegevoegd!");
        } else {
            FrontendHelper.displayErrorSmallMessage("Vul alstublieft een tarief in.");
        }
    }

    public void remove(Tariff tariff) {
        if (tariff != null) {
            tariffService.delete(tariff);
            this.tariffs = tariffService.findAll();
            FrontendHelper.displaySuccessSmallMessage("Het tarief is succesvol verwijderd!");
        } else {
            FrontendHelper.displayErrorSmallMessage("Er ging iets mis.", "Probeer het opnieuw.");
        }
    }


    public void onRowSelect(SelectEvent event) {
        Tariff selectedTariff = (Tariff) event.getObject();
        RedirectHelper.redirect("/pages/tariff/tariff.xhtml?id=" + selectedTariff.getId());
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
    //</editor-fold>
}
