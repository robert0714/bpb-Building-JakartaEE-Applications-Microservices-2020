/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsfclient.jsfclient.services;

import com.avbravo.jmoordbutils.JsfUtil;
import com.jsfclient.jsfclient.entity.Profile;
import com.jsfclient.jsfclient.producer.AuthentificationProducer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author avbravo
 */
@Stateless
public class ProfileServices implements Serializable {

    private static final String PASS = "pass";
    private static final String FAIL = "fail";
    private static final String SUCCESS_RESULT = "<result>success</result>";

   
    @Inject
    AuthentificationProducer authentificationProducer;

// <editor-fold defaultstate="collapsed" desc="List<Profile> findAll()">
    public List<Profile> findAll() {
        List<Profile> profileList = new ArrayList<>();
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            WebTarget target = client.target("http://localhost:8080" + "/jsfmicroservices/resources/profile/findall");

            GenericType<List<Profile>> data = new GenericType<List<Profile>>() {
            };

            profileList = target.request(MediaType.APPLICATION_JSON).get(data);

        } catch (Exception e) {
         
            System.out.println("findAll()" + e.getLocalizedMessage());
        }
        return profileList;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Boolean add(Profile profile)">
    public Boolean add(Profile profile) {
        try {
            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            WebTarget webTarget
                    = client.target("http://localhost:8080" + "/jsfmicroservices/resources/profile/add");

            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.post(Entity.entity(profile, MediaType.APPLICATION_JSON));

            System.out.println(response.getStatus());
            if (response.getStatus() == 400) {
                return false;
            }
            System.out.println(response.readEntity(String.class
            ));
            return true;
        } catch (Exception e) {
           
            System.out.println("errort" + e.getLocalizedMessage());
        }
        return false;
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Boolean update(Profile profile)">

    public Boolean update(Profile profile) {
        try {
            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            WebTarget webTarget
                    = client.target("http://localhost:8080" + "/jsfmicroservices/resources/profile/update");

            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.post(Entity.entity(profile, MediaType.APPLICATION_JSON));

            System.out.println(response.getStatus());
            if (response.getStatus() == 400) {
                return false;
            }
            System.out.println(response.readEntity(String.class
            ));
            return true;
        } catch (Exception e) {
          
            System.out.println("errort" + e.getLocalizedMessage());
        }
        return false;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Profile findByProfilename(String profileaname) ">
    /**
     * consulta por codigo_pedido impresa
     *
     * @param codigo_
     * @return
     */
    public Profile findByProfilename(String idprofile) {
        Profile profile= new Profile();
        try {
          
            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            profile= client
                    .target("http://localhost:8080"+ "/jsfmicroservices/resources/profile/search/")
                    .path("/{profilename}")
                    .resolveTemplate("profilename", idprofile)
                    .request(MediaType.APPLICATION_JSON)
                    .get(Profile.class
                    );

            //String result = FAIL;
        } catch (Exception e) {
           
            System.out.println("findBProfilename() " + e.getLocalizedMessage());
        }
        return profile;
    }
    // </editor-fold>
    
    
    
      // <editor-fold defaultstate="collapsed" desc="List<Profile> complete( String query)">
    public List<Profile> complete(String query) {
        List<Profile> suggestions = new ArrayList<>();
        try {
      
             Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            suggestions = client
                     .target("http://localhost:8080"+ "/jsfmicroservices/resources/profile/autocomplete/")
                    .path("/{query}")
                    .resolveTemplate("query", query)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<Profile>>() {
                    });

        } catch (Exception e) {
          
            System.out.println("complete() " + e.getLocalizedMessage());
        }

        return suggestions;
    }
    // </editor-fold>


}
