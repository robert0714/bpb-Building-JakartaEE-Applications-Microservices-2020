/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsfclient.jsfclient.producer;

import com.avbravo.jmoordbutils.JsfUtil;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

/**
 *
 * @author avbravo
 */
@Named
@ApplicationScoped
public class AuthentificationProducer implements Serializable {

    private String user = null;
    private String password = null;

    // <editor-fold defaultstate="collapsed" desc="HttpAuthenticationFeature httpAuthenticationFeature()">
    /**
     * Devuelve la autentificacion para ser usada con el Client
     *
     * @return
     */
    public HttpAuthenticationFeature httpAuthenticationFeature() {
        HttpAuthenticationFeature httpAuthenticationFeature = null;
        try {
            readAuthentificationProperties();
            httpAuthenticationFeature = HttpAuthenticationFeature.basic(user, password);

        } catch (Exception e) {

            JsfUtil.errorMessage("HttpAuthenticationFeature () " + e.getLocalizedMessage());
        }

        return httpAuthenticationFeature;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Boolean readAuthentificationProperties()">
    private Boolean readAuthentificationProperties() {

        try {

            if (user == null) {

                user = "demo";
                password = "demo";
                return true;
            }

        } catch (Exception e) {

            JsfUtil.errorMessage("readAuthentificationProperties() " + e.getLocalizedMessage());
        }

        return false;
    }
    // </editor-fold>

}
