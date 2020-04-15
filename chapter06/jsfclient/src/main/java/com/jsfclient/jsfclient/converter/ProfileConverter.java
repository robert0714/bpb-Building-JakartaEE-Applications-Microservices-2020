/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/
package com.jsfclient.jsfclient.converter;


import com.jsfclient.jsfclient.entity.Profile;
import com.jsfclient.jsfclient.services.ProfileServices;
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
public class ProfileConverter implements Converter {
 
    @Inject
    ProfileServices profileServices; 

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        Profile profile = new Profile();
        try{
            if(!s.equals("null")){
           
          profile = profileServices.findByProfilename(s);
            }
          } catch (Exception e) {  
            System.out.println("getAsObject() "+e.getLocalizedMessage());
          }
          return profile;
      }


    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        String r = "";
        try {
            if (o instanceof Profile) {
                Profile profile = (Profile) o;
                r = String.valueOf(profile.getIdprofile());
            }else if (o instanceof String) {
               r = (String) o;
            }
        } catch (Exception e) {   
                System.out.println("getAsString() "+e.getLocalizedMessage());
        }
        return r;
        }



}
