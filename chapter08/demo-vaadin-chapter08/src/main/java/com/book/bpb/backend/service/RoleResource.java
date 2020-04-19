package com.book.bpb.backend.service;

import com.book.bpb.backend.dao.RoleManagement;
import com.book.bpb.backend.entity.Role;
import com.mongodb.client.result.DeleteResult;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@Path("roles")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class RoleResource {

    @Inject
    RoleManagement roleManagement;

    @GET
    public List<Role> findAll() {
        return this.roleManagement.findAllRole();
    }

    @GET
    @Path("{name}")
    public Role findByName(@PathParam("name") String name) {
        return this.roleManagement.findBYName(name);
    }

    @POST
    public void createRole(Role role) {
        this.roleManagement.createRole(role);
    }

    @PUT
    @Path("{name}")
    public void updateAccount(@PathParam("name") String name, Role role) {
        this.roleManagement.updateRoleByName(name, role);
    }

    @DELETE
    @Path("{name}")
    public Response deleteRole(@PathParam("name") String name) {
        DeleteResult result = this.roleManagement.removeByName(name);

        System.out.println(result.getDeletedCount());

        return Response.ok().build();
    }
}
