package main.boundary.rest;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import main.boundary.rest.jwt.Secured;
import main.domain.Car;
import main.domain.Owner;
import main.domain.enums.AuthorizedApplications;
import main.service.CarService;
import main.service.OwnerService;
import org.json.JSONArray;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

/**
 * @author Thom van de Pas on 14-3-2018
 */
@Path("cars")
@Stateless
public class CarResource {

    @Inject
    private CarService carService;
    @Inject
    private OwnerService ownerService;

    public CarResource() {
    }

    /**
     * Gets all the cars in the database.
     *
     * @returns all the cars.
     */
    @GET
    @Secured(AuthorizedApplications.DRIVER)
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll() {
        List<Car> cars = carService.findAll();
        if (cars.isEmpty()) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return Response.ok(this.carService.replaceObjects(cars)).build();
    }

    /**
     * Gets the cars of an cartracker.
     *
     * @param ownerId is the Id of the cartracker.
     * @returns a List of cars.
     */
    @GET
    @Path("{citizenServiceNumber}")
    @Secured(AuthorizedApplications.DRIVER)
    @Produces({MediaType.APPLICATION_JSON})
    public Response getCarsByOwner(@PathParam("citizenServiceNumber") Long citizenServiceNumber) {
        Owner owner = ownerService.findByCSN(citizenServiceNumber);
        List<Car> cars;
        if (owner != null) {
            cars = carService.findByOwner(owner);
        } else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        if (cars.isEmpty()) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        return Response.ok(this.carService.replaceObjects(cars)).build();
    }

    /**
     * Find a Car by its LicensePlate
     *
     * @param licensePlate is the LicensePlate of the Car
     * @return the Car or Response.Status.NOT_FOUND
     */
    @GET
    @Path("/find/{licensePlate}")
    @Secured(AuthorizedApplications.DRIVER)
    @Produces({MediaType.APPLICATION_JSON})
    public Response getCarByLicensePlate(@PathParam("licensePlate") String licensePlate) {
        if (licensePlate == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        Car foundCar = this.carService.findByLicensePlate(licensePlate);

        if (foundCar == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        return Response.ok(foundCar.toJson()).build();
    }

    /**
     * Updates a car.
     *
     * @param car the car that has to be updated.
     * @returns the updatedCar
     */
    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    public Response updateCar(Car car) {
        Car foundCar = carService.findById(car.getId());
        if (foundCar == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        carService.createOrUpdate(car);
        return Response.ok(foundCar).build();
    }

    /**
     * Creates a new Car
     *
     * @param car the Car to be created.
     * @returns the created Car.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCar(Car car) {
        if (car == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        carService.createOrUpdate(car);
        URI id = URI.create(car.getLicensePlate());
        return Response.created(id).build();
    }

    /**
     * Deletes an Account based on it's username.
     *
     * @param licensePlate
     * @returns Status.NO_CONTENT
     */
    @DELETE
    @Path("{licensePlate}")
    public Response deleteCar(String licensePlate) {
        carService.deleteByLicensePlate(licensePlate);
        return Response.noContent().build();
    }

    @GET
    @Path("test")
    @Produces({MediaType.APPLICATION_JSON})
    public Response testGetCartrackers() throws UnirestException {
        HttpResponse<JsonNode> getResponse = Unirest.get("http://localhost:8080/DisplacementSystem/api/CarTrackers").asJson();
        JSONArray array = getResponse.getBody().getArray();
        System.out.println("array = " + array);
        return Response.ok(array.toString()).build();
    }

    @GET
    @Path("create")
    @Produces({MediaType.APPLICATION_JSON})
    public Response testCreate() throws UnirestException {
        HttpResponse<JsonNode> getResponse = Unirest.get("http://localhost:8080/DisplacementSystem/api/CarTrackers/Create").asJson();
        JSONArray array = getResponse.getBody().getArray();
        System.out.println("array = " + array);
        return Response.ok(array.toString()).build();
    }
}