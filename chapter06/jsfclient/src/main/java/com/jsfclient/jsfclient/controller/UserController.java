/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsfclient.jsfclient.controller;

import com.avbravo.jmoordbutils.JmoordbResourcesFiles;
import com.avbravo.jmoordbutils.JsfUtil;
import com.jsfclient.jsfclient.entity.Profile;
import com.jsfclient.jsfclient.entity.User;
import com.jsfclient.jsfclient.services.ProfileServices;
import com.jsfclient.jsfclient.services.UserServices;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import lombok.Getter;

import lombok.Setter;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author avbravo
 */
@Named
@ViewScoped
@Getter
@Setter
public class UserController implements Serializable{
    
    private String username;
    private String password;
    
  private static final long serialVersionUID = 1L;
    @Inject
    UserServices userServices;
    @Inject
    ProfileServices profileServices;
    Profile profileSearch = new Profile();
     @Inject
    JmoordbResourcesFiles rf;
     User user = new User();
    
    List<User> userList = new ArrayList<>();
    /**
     * Creates a new instance of UserController
     */
    public UserController() {
    }
    
      @PostConstruct
    public void init() {
          

        userList = userServices.findAll();
        
    }
    
    public String login(){
        try {
            JsfUtil.successMessage(rf.getAppMessage("login.welcome") );
            User user= userServices.findByUsername(username);
            
             if (user== null || user.getUsername() == null) {
                JsfUtil.warningMessage(rf.getAppMessage("login.usernamenotvalid"));
                return "";
            } else {


                JsfUtil.infoDialog("Welcome ", "user "+user.getName());
             }
        } catch (Exception e) {
            JsfUtil.errorDialog("login()", e.getLocalizedMessage());
        }
        return "";
    }
    
    
    String handleAutocompleteOfListXhtml(SelectEvent event) {
        
       try {
          JsfUtil.infoDialog("Selected", profileSearch.getProfile());
        } catch (Exception e) {
            JsfUtil.errorDialog("login()", e.getLocalizedMessage());
        }
        return "";
    }
}
