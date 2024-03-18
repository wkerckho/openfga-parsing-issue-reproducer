package org.example.openfga.test;

import io.quarkiverse.zanzibar.annotations.FGAPathObject;
import io.quarkiverse.zanzibar.annotations.FGARelation;
import io.quarkiverse.zanzibar.annotations.FGAUserType;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/trips")
public class TripResource {

    @GET
    @Path("{id}")
    @FGAPathObject(param = "id", type = "trip")
    @FGARelation("booking_viewer")
    @FGAUserType("user")
    public String hello(@PathParam("id") String id) {
        return "Content for trip with id " + id;
    }

}
