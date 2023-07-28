package org.redeasy.shopcar.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.redeasy.shopcar.entity.Shop;

@ApplicationScoped
public class ShopRepository implements PanacheRepository<Shop> {

}
