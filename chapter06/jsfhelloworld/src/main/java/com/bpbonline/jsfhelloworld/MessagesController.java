/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpbonline.jsfhelloworld;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author avbravo
 */
@Named(value = "messagesController")
@SessionScoped
public class MessagesController implements Serializable{
    
private String username;
private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * Creates a new instance of MessagesController
     */
    public MessagesController() {
    }
    
    public String login(){
        try {
          if(username.equals("demo") && password.equals("demo"))  {
              JsfUtil.infoDialog("Welcome "+username);
          }
        } catch (Exception e) {
         JsfUtil.warningMessage(rf.getAppMessage("login.usernamenotvalid"));
        }
        return "";
    }
}
