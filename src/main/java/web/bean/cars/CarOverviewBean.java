package web.bean.cars;

import main.domain.Car;
import main.domain.CarTracker;
import main.domain.Ownership;
import main.service.CarService;
import main.service.CarTrackerService;
import main.service.OwnershipService;
import org.primefaces.event.SelectEvent;
import web.core.helper.RedirectHelper;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Martijn van der Pol on 05-04-18
 **/
@Named
@ViewScoped
public class CarOverviewBean implements Serializable {

    @Inject
    private CarService carService;
    @Inject
    private OwnershipService ownershipService;
    @Inject
    private CarTrackerService carTrackerService;

    private String licensePlate;
    private Car selectedCar;
    private List<Car> cars;
    private List<Car> filteredCars;

    private List<CarTracker> carTrackers;
    private CarTracker currentCartracker;
    private CarTracker selectedCartracker;

    private List<Ownership> ownerships;
    private Ownership currentOwnership;
    private Ownership selectedOwnership;


    @PostConstruct
    public void init() {
        this.cars = this.carService.findAll();
        this.carTrackers = this.carTrackerService.findAll();
        this.ownerships = this.ownershipService.findAll();
    }

    /**
     * This method is fired when a row is selected in the data table.
     * The user is redirected to the invoice page where master details are displayed.
     *
     * @param event Select event from data table
     */
    public void onRowSelect(SelectEvent event) {
        Car selectedCar = (Car) event.getObject();
        RedirectHelper.redirect("/pages/cars/car.xhtml?carId=" + selectedCar.getId());
    }

    public void onOwnershipChange(Ownership selectedOwnership) {
        this.selectedOwnership = selectedOwnership;
    }

    public void onCartrackerChange(CarTracker selectedCartracker) {
        this.selectedCartracker = selectedCartracker;
    }

    public void CreateCar(){
        Ownership foundOwnership = this.ownershipService.findById(selectedOwnership.getId());
        CarTracker foundCarTracker = this.carTrackerService.findById(this.selectedCartracker.getId());
        Car car = new Car(this.licensePlate , foundOwnership , foundCarTracker);
        carService.createOrUpdate(car);
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public List<Car> getFilteredCars() {
        return filteredCars;
    }

    public void setFilteredCars(List<Car> filteredCars) {
        this.filteredCars = filteredCars;
    }

    public Car getSelectedCar() {
        return selectedCar;
    }

    public void setSelectedCar(Car selectedCar) {
        this.selectedCar = selectedCar;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public CarTracker getCurrentCartracker() {
        return currentCartracker;
    }

    public void setCurrentCartracker(CarTracker currentCartracker) {
        this.currentCartracker = currentCartracker;
    }

    public Ownership getCurrentOwnership() {
        return currentOwnership;
    }

    public void setCurrentOwnership(Ownership currentOwnership) {
        this.currentOwnership = currentOwnership;
    }

    public List<CarTracker> getCarTrackers() {
        return carTrackers;
    }

    public void setCarTrackers(List<CarTracker> carTrackers) {
        this.carTrackers = carTrackers;
    }

    public CarTracker getSelectedCartracker() {
        return selectedCartracker;
    }

    public void setSelectedCartracker(CarTracker selectedCartracker) {
        this.selectedCartracker = selectedCartracker;
    }

    public List<Ownership> getOwnerships() {
        return ownerships;
    }

    public void setOwnerships(List<Ownership> ownerships) {
        this.ownerships = ownerships;
    }

    public Ownership getSelectedOwnership() {
        return selectedOwnership;
    }

    public void setSelectedOwnership(Ownership selectedOwnership) {
        this.selectedOwnership = selectedOwnership;
    }
}