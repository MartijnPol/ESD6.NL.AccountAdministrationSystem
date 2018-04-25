package web.bean.cars;

import main.domain.Car;
import main.domain.Ownership;
import main.service.CarService;
import main.service.OwnershipService;
import web.bean.BaseBean;
import web.core.helper.FrontendHelper;

import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * @author Thom van de Pas on 23-4-2018
 */
@Named("carBean")
@ViewScoped
public class CarBean extends BaseBean {

    @Inject
    private CarService carService;
    @Inject
    private OwnershipService ownershipService;

    private Long carId;
    private Car car;
    private List<Ownership> ownerships;
    private Ownership selectedOwnership;

    @Override
    public void init() {
        this.ownerships = this.ownershipService.findAll();
        this.car = this.carService.findById(this.carId);
    }

    public void onItemChange(Ownership selectedOwnership) {
        this.selectedOwnership = selectedOwnership;
    }

    public void update() {
        if (this.car != null && this.selectedOwnership != null) {
            if (!car.getCurrentOwnership().equals(this.selectedOwnership)) {
                this.carService.assignToNewOwner(this.car, this.selectedOwnership);
            } else {
                this.carService.createOrUpdate(this.car);
            }
            FrontendHelper.displaySuccessSmallMessage("De auto is succesvol geüpdatet.");
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Ownership getSelectedOwnership() {
        return selectedOwnership;
    }

    public void setSelectedOwnership(Ownership selectedOwnership) {
        this.selectedOwnership = selectedOwnership;
    }

    public List<Ownership> getOwnerships() {
        return ownerships;
    }

    public void setOwnerships(List<Ownership> ownerships) {
        this.ownerships = ownerships;
    }
    //</editor-fold>
}
