package com.jsfmicroservices;

import com.avbravo.jmoordb.configuration.JmoordbConnection;
import java.util.Set;
import javax.annotation.security.DeclareRoles;
import javax.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Configures JAX-RS for the application.
 * @author Juneau
 */
@ApplicationPath("resources")
@DeclareRoles({"admin", "testing"})
@BasicAuthenticationMechanismDefinition(realmName = "admin-realm")
public class JAXRSConfiguration extends Application {
    
       @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
           try {
                         //Configuracion de la base de datos
        JmoordbConnection jmc = new JmoordbConnection.Builder()
                .withSecurity(false)
                .withDatabase("jsfdata")
                .withHost("")
                .withPort(0)
                .withUsername("")
                .withPassword("")
                .build();
           } catch (Exception e) {
               System.out.println("Error "+e.getLocalizedMessage());
           }
       
        return resources ;
    }
    
}
