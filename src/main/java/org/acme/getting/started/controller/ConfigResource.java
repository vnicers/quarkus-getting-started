package org.acme.getting.started.controller;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author tam
 */
@Path("/config")
public class ConfigResource {

    @GET
    @Path("/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getConfig(@PathParam("name") String name) {
        Config config = ConfigProvider.getConfig();
        return config.getValue(name, String.class);
    }

}
