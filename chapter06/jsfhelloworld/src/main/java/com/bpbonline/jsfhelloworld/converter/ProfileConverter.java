/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/
package com.bpbonline.jsfhelloworld.converter;

import com.avbravo.jmoordb.mongodb.history.services.ErrorInfoServices;
import com.avbravo.jmoordb.util.JmoordbUtil;
import com.bpbonline.jsfhelloworld.entity.Profile;
import com.bpbonline.jsfhelloworld.repository.ProfileRepository;
import java.util.Optional;
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
    ErrorInfoServices errorServices;
    @Inject
    ProfileRepository profilesRepository;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        Profile profilese = new Profile();
        try{
            if(!s.equals("null")){
               Profile b = new Profile();
               b.setIdprofile(s);
               Optional<Profile> optional = profilesRepository.findById(b);
               if (optional.isPresent()) {
               profilese= optional.get();  
               }   
             }
          } catch (Exception e) {
              errorServices.errorMessage(JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(),e);
          }
          return profilese;
      }


    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        String r = "";
        try {
            if (o instanceof Profile) {
                Profile profilese = (Profile) o;
                r = String.valueOf(profilese.getIdprofile());
            }else if (o instanceof String) {
               r = (String) o;
            }
        } catch (Exception e) {
             errorServices.errorMessage(JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(),e); 
        }
        return r;
        }



}
