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
import org.primefaces.event.SelectEvent;
import web.core.helper.RedirectHelper;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.Response;
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
    private CarTrackerService carTracerService;

    @Inject
    private CarOwnershipService carOwnershipService;

    @Inject
    private RDWService rdwService;

    private List<CarTracker> carTrackers;

    private List<CarTracker> filteredCartrackers;

    private Car selectedCar;

    private List<Car>  cars;


    public CarTrackerOverviewBean() {
    }

    @PostConstruct
    public void init() {
//        try {
//            this.carTrackers = (List<CarTracker>) getAllCartrackers();
//        } catch (UnirestException e) {
//            e.printStackTrace();
//        }
        cars = carService.findAll();
//        addCars();
    }

    public void updateCar (Car car){
        Car knownCar = carService.findById(car.getId());
        knownCar.setCarTrackerId(car.getCarTrackerId());

        if (knownCar.getLicensePlate().equals(car.getLicensePlate()))
        {
            knownCar.setLicensePlate(car.getLicensePlate());
        }
        else {
            knownCar.setRdwData(rdwService.findByLicensePlate(car.getLicensePlate()));
        }

        if (knownCar.getCurrentOwnership().equals(car.getCurrentOwnership())){
            knownCar.setCurrentOwnership(car.getCurrentOwnership());
        }
        else {
            knownCar.setCurrentOwnership(car.getCurrentOwnership());
            knownCar.addPastOwnership(knownCar.getCurrentOwnership());
        }
    }


    public Owner getOwnerDetails (long cartrackerID){
        Car car = carService.findByCarTrackerId(cartrackerID);
        Ownership ownership = car.getCurrentOwnership();
        return  ownership.getOwner();
    }

    public Car getCarDetails (long cartrackerID){
        return carService.findByCarTrackerId(cartrackerID);
    }

    public List<CarTracker> getAllCartrackers() throws UnirestException {
        HttpResponse<JsonNode> getResponse = Unirest.get("http://localhost:8080/DisplacementSystem/api/CarTrackers").asJson();
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

    public Response createCartracker () throws UnirestException {
        // aanpassen naar het simulatie systeem
            HttpResponse<JsonNode> getResponse = Unirest.get("http://localhost:8080/DisplacementSystem/api/CarTrackers/Create").asJson();
            JSONArray array = getResponse.getBody().getArray();
            System.out.println("array = " + array);
            return Response.ok(array.toString()).build();
    }

    public void onRowSelect(SelectEvent event) {
        Car selectedCar = (Car) event.getObject();
        RedirectHelper.redirect("/pages/cartracker/cartracker.xhtml?carId=" + selectedCar.getId());
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

    public Car getSelectedCar() {
        return selectedCar;
    }

    public void setSelectedCar(Car selectedCar) {
        this.selectedCar = selectedCar;
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
}