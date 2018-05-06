package web.bean.cartracker;


import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import main.domain.Car;
import main.domain.CarTracker;
import main.domain.Owner;
import main.domain.Ownership;
import main.service.CarService;
import main.service.OwnerService;
import main.service.OwnershipService;
import main.service.RDWService;
import org.json.JSONArray;
import org.primefaces.event.SelectEvent;
import web.core.helper.RedirectHelper;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class CarTrackerOverviewBean implements Serializable {

    @Inject
    private CarService carService;

    @Inject
    private OwnershipService ownershipService;

    @Inject
    private OwnerService ownerService;

    @Inject
    private RDWService rdwService;

    private List<CarTracker> carTrackers;

    private List<CarTracker> filteredCartrackers;

    private Car selectedCar;

    private List<Owner> owners;
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
//        addOwners();
    }


    public void updateOwner (Owner owner){
        Owner knownOwner = ownerService.findById(owner.getId());
        knownOwner.setAddress(owner.getAddress());
        knownOwner.setBirthDay(owner.getBirthDay());
        knownOwner.setFirstName(owner.getFirstName());
        knownOwner.setLastName(owner.getLastName());
        knownOwner.setOwnerships(owner.getOwnerships());
        ownerService.createOrUpdate(knownOwner);
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

    public Response getAllCartrackers() throws UnirestException {
        HttpResponse<JsonNode> getResponse = Unirest.get("http://localhost:8080/DisplacementSystem/api/CarTrackers").asJson();
        JSONArray array = getResponse.getBody().getArray();
        return Response.ok(array.toString()).build();
    }

    public Response createCartracker () throws UnirestException {
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

    public void addOwners(){
        for (Car car: cars) {
            Owner owner = car.getCurrentOwnership().getOwner();
            owners.add(owner);
        }
    }

    public void addCars(){
        for (CarTracker carTracker : carTrackers)
        {
           Car car = carService.findByCarTrackerId(carTracker.getId());
           cars.add(car);
        }
    }

    public List<Owner> getOwners() {
        return owners;
    }

    public List<Car> getCars() {
        return cars;
    }
}