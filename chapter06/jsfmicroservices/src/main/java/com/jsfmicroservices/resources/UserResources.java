/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsfmicroservices.resources;

import com.avbravo.jmoordbutils.JsfUtil;
import com.jsfmicroservices.entity.User;
import com.jsfmicroservices.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author avbravo
 */

@Path("user")
public class UserResources {

    @Inject
    UserRepository userRepository;

    // <editor-fold defaultstate="collapsed" desc="@GET">
    @GET
    @RolesAllowed({"admin"})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<User> get() {

        return userRepository.findAll();
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="@Path("/findall")">
    @GET
    @Path("/findall")
        @RolesAllowed({"admin"})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<User> findAll() {

        return userRepository.findAll();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="@Path("/add")">
    @POST
    @Path("/add")
    @RolesAllowed({"admin"})
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(User user) {
        try {

            if (userRepository.update(user)) {
                return Response.status(201).entity("User saved").build();
            }
//Response.ok().build();
            return Response.status(400).entity("User could not be saved").build();
        } catch (Exception e) {

            return Response.status(400).entity("Error!!" + e.getLocalizedMessage()).build();

        }

    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="@Path("/update")">
    @PUT
    @Path("/update")
    @RolesAllowed({"admin"})
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(User user) {
        try {

            if (userRepository.update(user)) {
                return Response.status(201).entity("Updated user").build();
            }
//Response.ok().build();
            return Response.status(400).entity("User was not updated").build();
        } catch (Exception e) {

            return Response.status(400).entity("Error!!" + e.getLocalizedMessage()).build();

        }

    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="@Path("/search/{username}")">
  
    @GET
    @Path("/search/{username}")
    @RolesAllowed({"admin"})
    @Produces(MediaType.APPLICATION_JSON)
    public User findByUsername(@PathParam("username") String username) {
        User user = new User();
        try {
            user.setUsername(username);
            Optional<User> optional = userRepository.findById(user);
            if (!optional.isPresent()) {
                user = optional.get();
            }
        } catch (Exception e) {
            System.out.println("findByUsername() " + e.getLocalizedMessage());
        }

        return user;
    }
// </editor-fold>

}
