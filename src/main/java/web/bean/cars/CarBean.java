package web.bean.cars;

import main.domain.Car;
import main.domain.CarTracker;
import main.domain.Owner;
import main.domain.Ownership;
import main.service.CarService;
import main.service.CarTrackerService;
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
    @Inject
    private CarTrackerService carTrackerService;

    private Long carId;
    private Car car;
    private List<Owner> owners;
    private boolean showPastOwners;
    private Owner selectedOwner;
    private List<CarTracker> unusedCarTrackers;
    private CarTracker selectedCartracker;
    private List<Ownership> pastOwnerships;

    @Override
    public void init() {
        this.owners = this.ownerService.findAll();
        this.car = this.carService.findById(this.carId);
        this.pastOwnerships = this.car.getPastOwnerships();
        this.unusedCarTrackers = this.carTrackerService.findUnusedTrackers();
    }

    public void onOwnerChange(Owner selectedOwner) {
        this.selectedOwner = selectedOwner;
    }

    public void onCartrackerChange(CarTracker selectedCartracker) {
        this.selectedCartracker = selectedCartracker;
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

        if (this.car != null && this.selectedCartracker != null) {
            if (!car.getCurrentCarTracker().equals(this.selectedCartracker)) {
                this.carService.assignNewCarTracker(this.car, selectedCartracker);
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

    public List<CarTracker> getUnusedCarTrackers() {
        return unusedCarTrackers;
    }

    public void setUnusedCarTrackers(List<CarTracker> unusedCarTrackers) {
        this.unusedCarTrackers = unusedCarTrackers;
    }

    public CarTracker getSelectedCartracker() {
        return selectedCartracker;
    }

    public void setSelectedCartracker(CarTracker selectedCartracker) {
        this.selectedCartracker = selectedCartracker;
    }

    public boolean isShowPastOwners() {
        return showPastOwners;
    }

    public void setShowPastOwners(boolean showPastOwners) {
        this.showPastOwners = showPastOwners;
    }

    public List<Ownership> getPastOwnerships() {
        return pastOwnerships;
    }

    public void setPastOwnerships(List<Ownership> pastOwnerships) {
        this.pastOwnerships = pastOwnerships;
    }

//</editor-fold>
}
