/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpb.jakarta.nosql.securityidentitystore.resources;

import com.bpb.jakarta.nosql.securityidentitystore.entity.Country;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author avbravo
 */
@Stateless
@Path("/country")
public class CountryResources {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"web"})
    public List<Country> getCountry() {

        List<Country> country = new ArrayList<Country>();
        country.add(new Country("pa", "Panama"));
        country.add(new Country("co", "Colombia"));
        country.add(new Country("br", "Brasil"));
        return country;

    }
}
