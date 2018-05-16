package web.bean.cartracker;

import main.domain.Car;
import main.domain.CarTracker;
import main.domain.Ownership;
import main.service.CarService;
import main.service.OwnershipService;
import main.service.CarTrackerService;
import web.bean.BaseBean;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;


@Named("carTrackerBean")
@ViewScoped
public class CarTrackerBean extends BaseBean {

    @Inject
    private CarService carService;

    @Inject
    private CarTrackerService carTrackerService;

    @Inject
    private OwnershipService ownershipService;

    private Long carId;
    private String cartrackerId;
    private Car car;
    private CarTracker carTracker;
    private List<Ownership> ownerships;
    private Ownership selectedOwnership;

    @Override
    public void init() {
        this.ownerships = this.ownershipService.findAll();
        this.carTracker = this.carTrackerService.findById(cartrackerId);
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

    public String getCartrackerId() {
        return cartrackerId;
    }

    public void setCartrackerId(String cartrackerId) {
        this.cartrackerId = cartrackerId;
    }

    public CarTracker getCarTracker() {
        return carTracker;
    }

    public void setCarTracker(CarTracker carTracker) {
        this.carTracker = carTracker;
    }

    //</editor-fold>
}
