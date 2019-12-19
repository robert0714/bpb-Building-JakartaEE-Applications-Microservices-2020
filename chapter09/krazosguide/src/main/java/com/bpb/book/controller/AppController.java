package com.bpb.book.controller;

import com.bpb.book.controller.messages.Greeting;
import javax.inject.Inject;
import javax.mvc.Controller;
import javax.mvc.Models;
import javax.mvc.View;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import org.eclipse.krazo.engine.Viewable;

@Path("app")
@Controller
public class AppController {

    @Inject
    Models models;

    @Inject
    Greeting greeting;

    @GET
    public String sayHello() {
        return "hello.jsp";
    }

    @GET
    @Path("view")
    @View("hello.jsp")
    public void view() {
        this.models.put("message", "Welcome from view");
    }

    @GET
    @Path("response")
    public Response response() {
        this.models.put("message", "Welcome from response");
        return Response.status(Response.Status.OK)
                .entity("hello.jsp")
                .build();
    }

    @GET
    @Path("viewable")
    public Viewable viewAble() {
        this.models.put("message", "Welcome from viewable");
        return new Viewable("hello.jsp");
    }

    @GET
    @Path("cdi")
    public String Cdi() {
        greeting.setMessage("Welcome from CDI");
        return "hello.jsp";
    }

}
