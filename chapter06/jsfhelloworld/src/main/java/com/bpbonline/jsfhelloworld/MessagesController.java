/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpbonline.jsfhelloworld;

import com.avbravo.jmoordbutils.JsfUtil;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import lombok.Getter;

import lombok.Setter;

/**
 *
 * @author avbravo
 */
@Named(value = "messagesController")
@SessionScoped
@Getter
@Setter
public class MessagesController implements Serializable{
    
private String username;
private String password;



    /**
     * Creates a new instance of MessagesController
     */
    public MessagesController() {
    }
    
    public String login(){
        try {
          if(username.equals("demo") && password.equals("demo"))  {
              JsfUtil.infoDialog("Welcome ",username);
          }else{
              JsfUtil.warningDialog("Wanring","Verify your data");
          }
        } catch (Exception e) {
         JsfUtil.warningMessage(e.getLocalizedMessage());
        }
        return "";
    }
}
