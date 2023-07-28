package org.redeasy.shopcar.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.redeasy.shopcar.entity.Car;
import org.redeasy.shopcar.repository.CarRepository;
import org.redeasy.shopcar.repository.ShopRepository;

import java.net.URI;
import java.util.List;

@Path("/cars")
public class CarResource {

    @Inject
    CarRepository carRepository;
    @Inject
    ShopRepository shopRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Car> cars = carRepository.listAll();
        return Response.ok(cars).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getById(@PathParam("id") Long id) {
        Car car = carRepository.findById(id);
        if (carRepository.isPersistent(car)) {
            return Response.ok(car).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/brand/{brand}")
    public Response getByBrand(@PathParam("brand") String brand) {
        List<Car> cars = carRepository.list("SELECT c FROM Car WHERE c.brand = ?1 ORDER BY id", brand);
        return Response.ok(cars).build();
    }

    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Car car) {
        carRepository.persist(car);
        if (carRepository.isPersistent(car)) {
            return Response.created(URI.create("cars" + car.id)).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public Response deleteById(@PathParam("id") Long id) {
        boolean deleted = carRepository.deleteById(id);
        if (deleted) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{idCar}/shop/{idShop}")
    public Response addToShop(@PathParam("idCar") Long idCar, @PathParam("idShop") Long idShop) {
        
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    // Obtenr el numero de autos
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("size")
    public String getSize() {
        return String.format("{\"size\": %d}", carRepository.count());
    }
}
