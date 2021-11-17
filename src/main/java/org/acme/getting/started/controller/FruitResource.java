package org.acme.getting.started.controller;


import com.fasterxml.jackson.annotation.JsonView;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Uni;
import org.acme.getting.started.common.config.Views;
import org.acme.getting.started.common.exception.ServiceException;
import org.acme.getting.started.common.validator.ValidationGroups;
import org.acme.getting.started.model.Book;
import org.acme.getting.started.model.FormData;
import org.acme.getting.started.model.Fruit;
import org.acme.getting.started.model.User;
import org.jboss.resteasy.reactive.*;

import javax.validation.Valid;
import javax.validation.groups.ConvertGroup;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.time.Duration;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.*;

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

    @POST
    @Path("/param")
    public String allParams(@RestPath String type,
                            @RestMatrix String variant,
                            @RestQuery String age,
                            @RestCookie String level,
                            @RestHeader("X-Cheese-Secret-Handshake")
                                    String secretHandshake,
                            @RestForm String smell) {
        return type + "/" + variant + "/" + age + "/" + level + "/" + secretHandshake + "/" + smell;
    }


    @GET
    @Path("/response")
    public RestResponse<String> hello() {
        // HTTP OK status with text/plain content type
        return RestResponse.ResponseBuilder.ok("Hello, World!", MediaType.TEXT_PLAIN_TYPE)
                // set a response header
                .header("X-FroMage", "Camembert")
                // set the Expires response header to two days from now
                .expires(Date.from(Instant.now().plus(Duration.ofDays(2))))
                // send a new cookie
                .cookie(new NewCookie("Flavour", "praliné"))
                // end of builder API
                .build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("/form")
    public String form(@MultipartForm FormData formData) {
        // return something
        return "file upload success";
    }

    @JsonView(Views.Public.class)
    @GET
    @Path("/public")
    public User userPublic() {
        return new User(10086, "dd");
    }

    @GET
    @Path("/ex")
    public Set<Fruit> findFromage(String fromage) {
        if (fromage == null)
            throw new BadRequestException();
        if (!fromage.equals("camembert"))
            throw new ServiceException(-1, "Unknown cheese: " + fromage);
        return fruits;
    }


    @POST
    @Path("/ex")
    public Set<Fruit> findFromage(User user) {
        throw new ServiceException(-1, "test post exception");
    }

    @POST
    @Path("/filter")
    public Set<Fruit> testFilter(User user) {
        return fruits;
    }

    @POST
    @Path("/validate")
    public Set<Fruit> testValidatePost(@Valid @ConvertGroup(to = ValidationGroups.Post.class) Book book) {
        return fruits;
    }

    @PUT
    @Path("/validate")
    public Set<Fruit> testValidatePut(@Valid @ConvertGroup(to = ValidationGroups.Put.class)
                                              Book book) {
        return fruits;
    }
}