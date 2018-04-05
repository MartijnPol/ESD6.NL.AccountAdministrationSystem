package web.bean.cars;

import main.domain.Car;
import main.service.CarService;

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
public class CarBean implements Serializable {

    @Inject
    private CarService carService;

    private List<Car> cars;
    private List<Car> filteredCars;

    @PostConstruct
    public void init() {
        this.cars = this.carService.findAll();
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
}