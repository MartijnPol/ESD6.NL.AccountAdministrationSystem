package main.boundary.rest;

import main.domain.Car;
import main.domain.Owner;
import main.service.CarService;
import main.service.OwnerService;

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
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll() {
        List<Car> cars = carService.findAll();
        if (cars.isEmpty()) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return Response.ok(this.carService.replaceObjects(cars)).build();
    }

    //TODO-Thom: Vraag aan mevrouw van Sogeti hoe dit zit.
//    /**
//     * Gets a car based on the carTrackerId.
//     *
//     * @param carTrackerId is the Id of the carTracker.
//     * @returns the car.
//     */
//    @GET
//    @Path("{car}")
//    @Produces({MediaType.APPLICATION_JSON})
//    public Response getCarByCarTrackerId(@QueryParam("id") Long carTrackerId) {
//        Car car = carService.findByCarTrackerId(carTrackerId);
//        if (car == null) {
//            throw new WebApplicationException(Response.Status.NOT_FOUND);
//        }
//
//        return Response.ok(car.toJson()).build();
//    }

    /**
     * Gets the cars of an owner.
     *
     * @param ownerId is the Id of the owner.
     * @returns a List of cars.
     */
    @GET
    @Path("{owner}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getCarsByOwner(@QueryParam("ownerId") Long ownerId) {
        Owner owner = ownerService.findById(ownerId);
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
        carService.update(car);
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
        carService.create(car);
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
}
