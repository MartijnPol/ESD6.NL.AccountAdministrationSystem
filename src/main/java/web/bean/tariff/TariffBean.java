package web.bean.tariff;

import main.domain.Tariff;
import main.service.TariffService;
import org.omnifaces.util.Ajax;
import web.core.helper.RedirectHelper;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class TariffBean implements Serializable{

    @Inject
    private TariffService tariffService;

    private double tariffInEuro;
    private boolean ridingDuringRushHour;

    public void create(){
        if (tariffInEuro != 0) {
            Tariff newTariff = new Tariff(this.tariffInEuro, this.ridingDuringRushHour);
            tariffService.createOrUpdate(newTariff);
        }
        RedirectHelper.redirect("/pages/tariff/tariffOverview.xhtml");
    }

    public void remove(Tariff tariff){
        tariffService.delete(tariff);
        RedirectHelper.redirect("/pages/tariff/tariffOverview.xhtml");
    }

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

    public void addMessage() {
        String summary = ridingDuringRushHour ? "Checked" : "Unchecked";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
    }
}
