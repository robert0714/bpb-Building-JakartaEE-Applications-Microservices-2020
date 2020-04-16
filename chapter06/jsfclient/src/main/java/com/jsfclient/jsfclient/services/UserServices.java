/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsfclient.jsfclient.services;

import com.jsfclient.jsfclient.entity.User;
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
public class UserServices implements Serializable {

    private static final String PASS = "pass";
    private static final String FAIL = "fail";
    private static final String SUCCESS_RESULT = "<result>success</result>";

   
    @Inject
    AuthentificationProducer authentificationProducer;

// <editor-fold defaultstate="collapsed" desc="List<User> findAll()">
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        try {

            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            WebTarget target = client.target("http://localhost:8080" + "/jsfmicroservices/resources/user/findall");

            GenericType<List<User>> data = new GenericType<List<User>>() {
            };

            userList = target.request(MediaType.APPLICATION_JSON).get(data);

        } catch (Exception e) {
         
            System.out.println("findAll()" + e.getLocalizedMessage());
        }
        return userList;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Boolean add(User user)">
    public Boolean add(User user) {
        try {
            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            WebTarget webTarget
                    = client.target("http://localhost:8080" + "/jsfmicroservices/resources/user/add");

            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.post(Entity.entity(user, MediaType.APPLICATION_JSON));

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
    // <editor-fold defaultstate="collapsed" desc="Boolean update(User user)">

    public Boolean uodate(User user) {
        try {
            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            WebTarget webTarget
                    = client.target("http://localhost:8080" + "/jsfmicroservices/resources/user/update");

            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.post(Entity.entity(user, MediaType.APPLICATION_JSON));

    
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

    // <editor-fold defaultstate="collapsed" desc="User findByUsername(String useraname) ">
    /**
     * consulta por codigo_pedido impresa
     *
     * @param codigo_
     * @return
     */
    public User findByUsername(String username) {
        User user= new User();
        try {
          
            Client client = ClientBuilder.newClient();
            client.register(authentificationProducer.httpAuthenticationFeature());
            user= client
                    .target("http://localhost:8080"+ "/jsfmicroservices/resources/user/search/")
                    .path("/{username}")
                    .resolveTemplate("username", username)
                    .request(MediaType.APPLICATION_JSON)
                    .get(User.class
                    );

            //String result = FAIL;
        } catch (Exception e) {
           
            System.out.println("findBUsername() " + e.getLocalizedMessage());
        }
        return user;
    }
    // </editor-fold>

}
