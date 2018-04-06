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

    public void setTariffs(List<Tariff> tariffs) {
        this.tariffs = tariffs;
    }

    public List<Tariff> getFilteredTariffs() {
        return filteredTariffs;
    }

    public void setFilteredTariffs(List<Tariff> filteredTariffs) {
        this.filteredTariffs = filteredTariffs;
    }
}
