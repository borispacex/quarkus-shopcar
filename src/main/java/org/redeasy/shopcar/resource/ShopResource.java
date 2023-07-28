package org.redeasy.shopcar.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import org.redeasy.shopcar.repository.ShopRepository;

@Path("/shops")
public class ShopResource {
    @Inject
    ShopRepository shopRepository;
}
