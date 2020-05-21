package com.book.bpb;

import com.avbravo.jmoordb.configuration.JmoordbConnection;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("resources")
public class JAXRSConfiguration extends Application{
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        try {
            //Configuration dbms
            JmoordbConnection jmc = new JmoordbConnection.Builder()
                    .withSecurity(false)
                    .withDatabase("demo")
                    .withHost("")
                    .withPort(0)
                    .withUsername("")
                    .withPassword("")
                    .build();
        } catch (Exception e) {
            System.out.println("Error " + e.getLocalizedMessage());
        }
        return resources;
    }
}
