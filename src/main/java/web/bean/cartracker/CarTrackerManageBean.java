package web.bean.cartracker;


import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import main.domain.Car;
import main.domain.Owner;
import main.domain.Ownership;
import main.service.CarService;
import main.service.OwnerService;
import main.service.OwnershipService;
import main.service.RDWService;
import org.json.JSONArray;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class CarTrackerManageBean implements Serializable {

    @Inject
    private CarService carService;

    @Inject
    private OwnershipService ownershipService;

    @Inject
    private OwnerService ownerService;

    @Inject
    private RDWService rdwService;

    private List<Car> cars;

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

}