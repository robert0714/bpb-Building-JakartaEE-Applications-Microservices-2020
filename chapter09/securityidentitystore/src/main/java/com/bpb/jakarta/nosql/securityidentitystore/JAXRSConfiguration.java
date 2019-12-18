/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpb.jakarta.nosql.securityidentitystore;

import java.util.Set;
import javax.annotation.security.DeclareRoles;
import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author avbravo
 */
@ApplicationScoped
@ApplicationPath("resources")
@DeclareRoles({ "web", "mobile" })
@BasicAuthenticationMechanismDefinition(realmName = "admin-realm")
public class JAXRSConfiguration extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        resources.add(com.bpb.jakarta.nosql.securityidentitystore.resources.CountryResources.class);

        return resources;
    }
    
}
