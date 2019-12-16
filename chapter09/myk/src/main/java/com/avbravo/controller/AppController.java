package com.avbravo.controller;

import com.avbravo.controller.repository.Greeting;
import javax.inject.Inject;
import javax.mvc.Controller;
import javax.mvc.View;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("app")
@Controller
public class AppController {

    @Inject
    Greeting greeting;


    @GET
    public String sayHello() {
         greeting.setMessage("Welcome!");   
        return "hello.jsp";
    }

    
    @GET @Path("view")
    @View("hello.jsp")
    public void view() {
    }

  

    @GET @Path("response")
    public Response helloResponse() {
        return Response.status(Response.Status.OK)
            .entity("hello.jsp")
            .build();
    }
   
}
