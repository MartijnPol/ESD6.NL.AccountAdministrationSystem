package web.bean.tariff;

import main.domain.Tariff;
import main.service.TariffService;
import web.core.helper.RedirectHelper;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("tariffBean")
@ViewScoped
public class TariffBean implements Serializable{

    @Inject
    private TariffService tariffService;
    private List<Tariff> tariffs;
    private Tariff tariff;

    private double tariffInEuro;
    private boolean ridingDuringRushHour;


    @PostConstruct
    public void init() {
        this.tariffs = this.tariffService.findAll();
    }

    public void create(){
        if (tariffInEuro != 0) {
            Tariff newTariff = new Tariff(this.tariffInEuro, this.ridingDuringRushHour);
            tariffService.createOrUpdate(newTariff);
        }
        RedirectHelper.redirect("/pages/tariff/tariffOverview.xhtml");
    }

    public List<Tariff> getTariffs() { return tariffs; }

    public void setTariffs(List<Tariff> tariffList) { this.tariffs = tariffList; }

    public double getTariffInEuro() {
        return tariffInEuro;
    }

    public void setTariffInEuro(double tariffInEuro) {
        this.tariffInEuro = tariffInEuro;
    }

    public boolean isRidingDuringRushHour(){
        return this.ridingDuringRushHour;
    }

    public void setRidingDuringRushHour(boolean ridingDuringRushHour) {
        this.ridingDuringRushHour = ridingDuringRushHour;
    }
}
