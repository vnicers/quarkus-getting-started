package org.acme.getting.started.controller;

import io.quarkus.runtime.configuration.ProfileManager;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author tam
 */
@Path("/profile")
public class ProfileResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getActiveProfile() {
        return ProfileManager.getActiveProfile();
    }
}
