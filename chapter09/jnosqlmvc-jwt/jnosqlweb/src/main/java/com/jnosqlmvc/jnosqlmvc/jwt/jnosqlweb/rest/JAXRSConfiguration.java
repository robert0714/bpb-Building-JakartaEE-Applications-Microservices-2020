package com.jnosqlmvc.jnosqlmvc.jwt.jnosqlweb.rest;
import javax.annotation.security.DeclareRoles;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.eclipse.microprofile.auth.LoginConfig;

@ApplicationPath("resources")
@LoginConfig(authMethod = "MP-JWT")
@DeclareRoles({"web","mobile"})
public class JAXRSConfiguration extends Application {

}
