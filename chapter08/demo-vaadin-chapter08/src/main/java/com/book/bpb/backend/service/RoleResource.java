package com.book.bpb.backend.service;

import com.avbravo.jmoordbutils.JsfUtil;
import com.book.bpb.backend.dao.RoleManagement;
import com.book.bpb.backend.entity.Role;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("roles")
public class RoleResource {

    String directoryLogger = JsfUtil.isLinux() ? JsfUtil.userHome() + JsfUtil.fileSeparator() + "demo" + JsfUtil.fileSeparator() + "logs" + JsfUtil.fileSeparator() + "logger.json" : "C:\\demo\\logs\\logger.json";

    @Inject
    RoleManagement roleManagement;

    @GET
    public List<Role> findAll() {
        return this.roleManagement.findAll();
    }

    @GET
    @Path("{name}")
    public List<Role> findByName(@PathParam("name") String name) {
        return this.roleManagement.findRegex("name", name, true, new Document("name", 1));
    }

    @POST
    public Response createRole(Role role) {
        try {

            if (roleManagement.update(role)) {
                return Response.status(201).entity("Role saved").build();
            }
            return Response.status(400).entity("Role could not be saved").build();
        } catch (Exception e) {
            JsfUtil.appendTextToLogErrorFile(this.directoryLogger, JsfUtil.nameOfClass(), JsfUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            return Response.status(400).entity("Error!!" + e.getLocalizedMessage()).build();
        }
    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Role role) {
        try {

            if (roleManagement.update(role)) {
                return Response.status(201).entity("Updated role").build();
            }
            return Response.status(400).entity("Role was not updated").build();
        } catch (Exception e) {
            JsfUtil.appendTextToLogErrorFile(this.directoryLogger, JsfUtil.nameOfClass(), JsfUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            return Response.status(400).entity("Error!!" + e.getLocalizedMessage()).build();

        }

    }

    @DELETE
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(Role role) {
        try {

            if (roleManagement.delete(role)) {
                return Response.status(201).entity("delete role").build();
            }
            return Response.status(400).entity("Not update of Role").build();
        } catch (Exception e) {
            JsfUtil.appendTextToLogErrorFile(this.directoryLogger, JsfUtil.nameOfClass(), JsfUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            return Response.status(400).entity("error!!" + e.getLocalizedMessage()).build();
        }

    }
}
