package web.bean.cars;

import main.domain.Car;
import main.domain.Owner;
import main.domain.Ownership;
import main.service.CarService;
import main.service.OwnerService;
import web.bean.BaseBean;
import web.core.helper.FrontendHelper;

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
    private OwnerService ownerService;

    private Long carId;
    private Car car;
    private List<Owner> owners;
    private Owner selectedOwner;

    @Override
    public void init() {
        this.owners = this.ownerService.findAll();
        this.car = this.carService.findById(this.carId);
    }

    public void onItemChange(Owner selectedOwner) {
        this.selectedOwner = selectedOwner;
    }

    public void update() {
        if (this.car != null && this.selectedOwner != null) {
            if (!car.getCurrentOwnership().getOwner().equals(this.selectedOwner)) {
                Ownership newOwnership = new Ownership(selectedOwner);
                this.carService.assignToNewOwner(this.car, newOwnership);
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

    public List<Owner> getOwners() {
        return owners;
    }

    public void setOwners(List<Owner> owners) {
        this.owners = owners;
    }

    public Owner getSelectedOwner() {
        return selectedOwner;
    }

    public void setSelectedOwner(Owner selectedOwner) {
        this.selectedOwner = selectedOwner;
    }
//</editor-fold>
}
