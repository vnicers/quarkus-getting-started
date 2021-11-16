package org.acme.getting.started.controller;


import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Uni;
import org.acme.getting.started.model.Fruit;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

@Path("/fruits")
public class FruitResource {

    private Set<Fruit> fruits = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

    public FruitResource() {
        fruits.add(new Fruit("Apple", "Winter fruit", OffsetDateTime.now()));
        fruits.add(new Fruit("Pineapple", "Tropical fruit", OffsetDateTime.now()));
    }

    @GET
    public Set<Fruit> list() {
        return fruits;
    }

    @POST
    public Set<Fruit> add(Fruit fruit) {
        fruits.add(fruit);
        return fruits;
    }

    @DELETE
    public Set<Fruit> delete(Fruit fruit) {
        fruits.removeIf(existingFruit -> existingFruit.getName().contentEquals(fruit.getName()));
        return fruits;
    }

    @GET
    @Path("/reactive")
    public Uni<List<Fruit>> get() {
        return Fruit.listAll(Sort.by("name"));
    }

    @GET
    @Path("/reactive/{id}")
    public Uni<Fruit> getById(@PathParam("id") Long id) {
        return Fruit.findById(id);
    }

    @POST
    @Path("/reactive")
    public Uni<Response> create(Fruit fruit) {
        return Panache.<Fruit>withTransaction(fruit::persist)
                .onItem().transform(inserted -> Response.created(URI.create("/fruits/reactive/" + inserted.id)).build());
    }
}