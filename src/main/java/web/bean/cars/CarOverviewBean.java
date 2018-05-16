package web.bean.cars;

import main.domain.Car;
import main.service.CarService;
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

    private Car selectedCar;
    private List<Car> cars;
    private List<Car> filteredCars;

    @PostConstruct
    public void init() {
        this.cars = this.carService.findAll();
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
}