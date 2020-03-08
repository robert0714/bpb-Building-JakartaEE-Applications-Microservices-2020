/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/
package com.bpbonline.jsfhelloworld.converter;

import com.avbravo.jmoordb.mongodb.history.services.ErrorInfoServices;
import com.avbravo.jmoordb.util.JmoordbUtil;
import com.bpbonline.jsfhelloworld.entity.Profiles;
import com.bpbonline.jsfhelloworld.repository.ProfilesRepository;
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
public class ProfilesConverter implements Converter {
 @Inject
    ErrorInfoServices errorServices;
    @Inject
    ProfilesRepository profilesRepository;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        Profiles profilese = new Profiles();
        try{
            if(!s.equals("null")){
               Profiles b = new Profiles();
               b.setIdprofile(s);
               Optional<Profiles> optional = profilesRepository.findById(b);
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
            if (o instanceof Profiles) {
                Profiles profilese = (Profiles) o;
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
