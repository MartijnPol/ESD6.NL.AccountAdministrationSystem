package web.bean.tariff;

import main.domain.Tariff;
import main.service.TariffService;

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
    private Tariff tariff;

    @PostConstruct
    public void init() {
        this.tariffs = this.tariffService.findAll();
    }

    public List<Tariff> getTariffs() {
        return tariffs;
    }

    public void setTariffs(List<Tariff> tariffList) {
        this.tariffs = tariffList;
    }
}
