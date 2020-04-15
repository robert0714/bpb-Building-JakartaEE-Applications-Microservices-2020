/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsfclient.jsfclient.controller;

import com.jsfclient.jsfclient.entity.User;
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

/**
 *
 * @author avbravo
 */
@Named
@ViewScoped
@Getter
@Setter
public class UserController implements Serializable{
  private static final long serialVersionUID = 1L;
    @Inject
    UserServices userServices;
    
    List<User> userList = new ArrayList<>();
    /**
     * Creates a new instance of UserController
     */
    public UserController() {
    }
    
      @PostConstruct
    public void init() {
        userList = userServices.findAll();
        for(User u:userList){
            System.out.println("---> "+u.getName() + " profile "+u.getProfile().get(0));
        }
    }
    
}
