package com.jnosqlmvc.jnosqlmvc.jwt.jnosqlweb.rest;

import java.security.Principal;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import org.eclipse.microprofile.jwt.JsonWebToken;

@Path("ping")
@Produces(TEXT_PLAIN)
public class PingResource {

    @Inject
    private Principal principal;

    @Inject
    private JsonWebToken jwt;

    @GET
    @PermitAll
    @Path("all")
    public String ping() {
        return principal.getName() + jwt.getGroups();
    }

    @GET
    @RolesAllowed({"web","mobile"})
    @Path("secure")
    public String pingSecure() {
        return principal.getName() + jwt.getGroups();
    }

}
