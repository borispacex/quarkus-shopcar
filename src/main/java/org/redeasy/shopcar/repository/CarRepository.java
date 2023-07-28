package org.redeasy.shopcar.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.redeasy.shopcar.entity.Car;

@ApplicationScoped
public class CarRepository implements PanacheRepository<Car> {

}
