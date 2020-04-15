/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/
package com.jsfclient.jsfclient.converter;


import com.jsfclient.jsfclient.entity.User;
import com.jsfclient.jsfclient.services.UserServices;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

/**
 *
 * @author avbravo
 */
@Named
@RequestScoped
public class UserConverter implements Converter {
 
    @Inject
    UserServices userServices; 

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        User user = new User();
        try{
            if(!s.equals("null")){
           
          user = userServices.findByUsername(s);
            }
          } catch (Exception e) {  
            System.out.println("getAsObject() "+e.getLocalizedMessage());
          }
          return user;
      }


    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        String r = "";
        try {
            if (o instanceof User) {
                User user = (User) o;
                r = String.valueOf(user.getUsername());
            }else if (o instanceof String) {
               r = (String) o;
            }
        } catch (Exception e) {   
                System.out.println("getAsString() "+e.getLocalizedMessage());
        }
        return r;
        }



}
