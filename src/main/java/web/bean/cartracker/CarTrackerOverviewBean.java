package web.bean.cartracker;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import main.domain.Car;
import main.domain.Ownership;
import main.domain.CarTracker;
import main.domain.Owner;
import main.service.*;
import org.json.JSONArray;
import org.omnifaces.util.Ajax;
import org.primefaces.event.SelectEvent;
import web.core.helper.FrontendHelper;
import web.core.helper.RedirectHelper;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class CarTrackerOverviewBean implements Serializable {

    @Inject
    private CarService carService;

    @Inject
    private CarTrackerService carTrackerService;

    @Inject
    private OwnershipService ownershipService;

    @Inject
    private RDWService rdwService;

    private List<CarTracker> carTrackers;

    private List<CarTracker> filteredCartrackers;

    private CarTracker selectedCartracker;

    private List<Car>  cars;

    private String cartrackerId;

    private String manufacturer;


    public CarTrackerOverviewBean() {

    }

    @PostConstruct
    public void init() {

        this.cars = carService.findAll();
        this.carTrackers = carTrackerService.findAll();

    }

    public void createCarTracker(){
        if (!this.cartrackerId.isEmpty() && !this.manufacturer.isEmpty()) {
            CarTracker carTracker = new CarTracker(cartrackerId , manufacturer);
            carTrackerService.createOrUpdate(carTracker);
            // Post naar simulatie systeem met id
            //Unirest.post("http://localhost:8080/DisplacementSystem/api/CarTrackers")
            this.carTrackers = this.carTrackerService.findAll();
            FrontendHelper.displaySuccessSmallMessage("De CarTracker is succesvol toegevoegd.");
        }
    }

    public void disableCarTracker(CarTracker carTracker){
        carTracker.setEnabled(false);
        carTrackerService.createOrUpdate(carTracker);
    }


    public Owner getOwnerDetails (String carTrackerId){
        Car car = carService.findByCarTrackerId(carTrackerId);
        Ownership ownership = car.getCurrentOwnership();
        return  ownership.getOwner();
    }

    public Car getCarDetails (String cartrackerId){
        return carService.findByCarTrackerId(cartrackerId);
    }

    public List<CarTracker> getAllCartrackers() throws UnirestException {
        HttpResponse<JsonNode> getResponse = Unirest.get("http://192.168.25.122:77/DisplacementSystem/api/CarTrackers").asJson();
        JSONArray array = getResponse.getBody().getArray();
        List<CarTracker> carTrackers = null;
        ObjectMapper mapper = new ObjectMapper();

        try {
            for (Object obj: array) {
                CarTracker carTracker = mapper.readValue(new File(String.valueOf(obj)), CarTracker.class);
                carTrackers.add(carTracker);
                System.out.println("TEST:   " + carTracker.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return carTrackers;
    }

    public void onRowSelect(SelectEvent event) {
        CarTracker selectedCarTracker = (CarTracker) event.getObject();
        RedirectHelper.redirect("/pages/cartracker/cartracker.xhtml?cartrackerId=" + selectedCarTracker.getId());
    }

    public List<CarTracker> getCarTrackers() {
        return carTrackers;
    }

    public void setCarTrackers(List<CarTracker> carTrackers) {
        this.carTrackers = carTrackers;
    }

    public List<CarTracker> getFilteredCartrackers() {
        return filteredCartrackers;
    }

    public void setFilteredCartrackers(List<CarTracker> filteredCartrackers) {
        this.filteredCartrackers = filteredCartrackers;
    }

    public CarTracker getSelectedCartracker() {
        return selectedCartracker;
    }

    public void setSelectedCartracker(CarTracker selectedCartracker) {
        this.selectedCartracker = selectedCartracker;
    }

    public void addCars(){
        for (CarTracker carTracker : carTrackers)
        {
           Car car = carService.findByCarTrackerId(carTracker.getId());
           cars.add(car);
        }
    }

    public List<Car> getCars() {
        return cars;
    }

    public String getCartrackerId() {
        return cartrackerId;
    }

    public void setCartrackerId(String cartrackerId) {
        this.cartrackerId = cartrackerId;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}