package com.book.bpb.backend.service;

import com.book.bpb.backend.dao.UserManagement;
import com.book.bpb.backend.entity.User;
import com.mongodb.client.result.DeleteResult;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("users")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class UserResource {

    @Inject
    UserManagement userManagement;

    @GET
    public List<User> findAll() {
        return this.userManagement.findAllUser();
    }

    @GET
    @Path("{firstName}")
    public User findByName(@PathParam("firstName") String firstName) {
        return this.userManagement.findBYName(firstName);
    }

    @POST
    public void createUser(User user) {
        this.userManagement.createUser(user);
    }

    @PUT
    @Path("{firstName}")
    public void updateAccount(@PathParam("firstName") String firstName, User user) {
        this.userManagement.updateUserByName(firstName, user);
    }

    @DELETE
    @Path("{name}")
    public Response deleteUser(@PathParam("name") String name) {
        DeleteResult result = this.userManagement.removeByName(name);

        System.out.println(result.getDeletedCount());

        return Response.ok().build();
    }
}
