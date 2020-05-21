package com.book.bpb.backend.service;

import com.avbravo.jmoordbutils.JsfUtil;
import com.book.bpb.backend.dao.UserManagement;
import com.book.bpb.backend.entity.User;
import org.bson.Document;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("users")
public class UserResource {

    String directoryLogger = JsfUtil.isLinux() ? JsfUtil.userHome() + JsfUtil.fileSeparator() + "demo" + JsfUtil.fileSeparator() + "logs" + JsfUtil.fileSeparator() + "logger.json" : "C:\\demo\\logs\\logger.json";

    @Inject
    UserManagement userManagement;

    @GET
    public List<User> findAll() {
        return this.userManagement.findAll();
    }

    @GET
    @Path("{name}")
    public List<User> findByName(@PathParam("name") String name) {
        return this.userManagement.findRegex("name", name, true, new Document("name", 1));
    }

    @POST
    public Response createUser(User user) {
        try {

            if (userManagement.update(user)) {
                return Response.status(201).entity("User saved").build();
            }
            return Response.status(400).entity("User could not be saved").build();
        } catch (Exception e) {
            JsfUtil.appendTextToLogErrorFile(this.directoryLogger, JsfUtil.nameOfClass(), JsfUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            return Response.status(400).entity("Error!!" + e.getLocalizedMessage()).build();
        }
    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(User user) {
        try {

            if (userManagement.update(user)) {
                return Response.status(201).entity("Updated User").build();
            }
            return Response.status(400).entity("User was not updated").build();
        } catch (Exception e) {
            JsfUtil.appendTextToLogErrorFile(this.directoryLogger, JsfUtil.nameOfClass(), JsfUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            return Response.status(400).entity("Error!!" + e.getLocalizedMessage()).build();

        }

    }

    @DELETE
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(User user) {
        try {

            if (userManagement.delete(user)) {
                return Response.status(201).entity("delete user").build();
            }
            return Response.status(400).entity("Not update of user").build();
        } catch (Exception e) {
            JsfUtil.appendTextToLogErrorFile(this.directoryLogger, JsfUtil.nameOfClass(), JsfUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            return Response.status(400).entity("error!!" + e.getLocalizedMessage()).build();
        }

    }
}
