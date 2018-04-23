package web.bean.cars;

import main.domain.Car;
import main.domain.Owner;
import main.domain.Ownership;
import main.service.CarService;
import main.service.OwnerService;
import main.service.OwnershipService;
import web.bean.BaseBean;
import web.core.helper.FrontendHelper;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * @author Thom van de Pas on 23-4-2018
 */
@Named
@ViewScoped
public class CarBean extends BaseBean {

    @Inject
    private CarService carService;
    @Inject
    private OwnershipService ownershipService;

    private Long carId;
    private Car car;
    private List<Ownership> ownerships;
    private Ownership ownership;

    @Override
    public void init() {
        this.ownerships = this.ownershipService.findAll();
        this.car = this.carService.findById(this.carId);
    }

    public void update() {
        if (car != null && ownership != null) {
            if (!car.getCurrentOwnership().equals(ownership)) {
                this.carService.assignToNewOwner(this.car, this.ownership);
            } else {
                this.carService.createOrUpdate(this.car);
            }
            FrontendHelper.displaySuccessSmallMessage("De auto is succesvol geüpdatet.");
        }
    }

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

    public Ownership getOwnership() {
        return ownership;
    }

    public void setOwnership(Ownership ownership) {
        this.ownership = ownership;
    }

    public List<Ownership> getOwnerships() {
        return ownerships;
    }

    public void setOwnerships(List<Ownership> ownerships) {
        this.ownerships = ownerships;
    }
}
