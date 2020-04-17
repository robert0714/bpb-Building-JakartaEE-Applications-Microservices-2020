/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsfmicroservices.resources;

import com.avbravo.jmoordb.util.JmoordbUtil;
import com.jsfmicroservices.entity.Profile;
import com.jsfmicroservices.repository.ProfileRepository;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import javax.annotation.security.RolesAllowed;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
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
import org.bson.Document;

/**
 *
 * @author avbravo
 */
@Path("profile")
public class ProfileResources {

    @Inject
    ProfileRepository profileRepository;

    // <editor-fold defaultstate="collapsed" desc="@GET">
    @GET
    @RolesAllowed({"admin"})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Profile> get() {

        return profileRepository.findAll();
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="@Path("/findall")">
    @GET
    @Path("/findall")
    @RolesAllowed({"admin"})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Profile> findAll() {

        return profileRepository.findAll();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="@Path("/add")">
    @POST
    @Path("/add")
    @RolesAllowed({"admin"})
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Profile profile) {
        try {

            if (profileRepository.update(profile)) {
                return Response.status(201).entity("The profile was saved").build();
            }
//Response.ok().build();
            return Response.status(400).entity("Couldn't save the profile").build();
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
    public Response update(Profile profile) {
        try {

            if (profileRepository.update(profile)) {
                return Response.status(201).entity("The profile was updated").build();
            }
//Response.ok().build();
            return Response.status(400).entity("The profile was not updated").build();
        } catch (Exception e) {

            return Response.status(400).entity("Error!!" + e.getLocalizedMessage()).build();

        }

    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="@Path("/search/{profilesname}")">
    @GET
    @Path("/search/{idprofile}")
    @RolesAllowed({"admin"})
    @Produces(MediaType.APPLICATION_JSON)
    public Profile findByIdprofile(@PathParam("idprofile") String idprofile) {
        Profile profile = new Profile();
        try {
            profile.setIdprofile(idprofile);
            Optional<Profile> optional = profileRepository.findById(profile);
            if (!optional.isPresent()) {
                profile = optional.get();
            }
        } catch (Exception e) {
            System.out.println("findByIdprofile() " + e.getLocalizedMessage());
        }

        return profile;
    }
// </editor-fold>

    @GET
    @Path("/autocomplete/{query}")
    @RolesAllowed({"admin"})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Profile> complete(@PathParam("query") String query) {
        List<Profile> suggestions = new ArrayList<>();
        try {
            query = query.trim();
            suggestions = profileRepository.findRegex("profile", query, true, new Document("profile", 1));
       
        } catch (Exception e) {
            System.out.println("complete() " + e.getLocalizedMessage());
        }
        return suggestions;
    }
}
