/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpbonline.jsfhelloworld.services;

import com.avbravo.jmoordb.mongodb.history.services.ErrorInfoServices;
import com.avbravo.jmoordb.util.JmoordbUtil;

import com.bpbonline.jsfhelloworld.entity.Profiles;
import com.bpbonline.jsfhelloworld.repository.ProfilesRepository;
import com.bpbonline.jsfhelloworld.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.bson.Document;

/**
 *
 * @authoravbravo
 */
@Stateless
public class ProfilesServices {

 @Inject
    ErrorInfoServices errorServices;
    @Inject
    ProfilesRepository repository;

    @Inject
    UserRepository usuarioRepository;
    List<Profiles> profilesList = new ArrayList<>();

    public List<Profiles> complete(String query) {
        List<Profiles> suggestions = new ArrayList<>();
        try {
            suggestions = repository.complete(query);
        } catch (Exception e) {
             errorServices.errorMessage(JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(),e);  
        }

        return suggestions;
    }

    public List<Profiles> completeFilter(String query) {
        List<Profiles> suggestions = new ArrayList<>();
        try {
            query = query.trim();
            if (query.length() < 1) {
                return suggestions;
            }
            String field = (String) UIComponent.getCurrentComponent(FacesContext.getCurrentInstance()).getAttributes().get("field");
            suggestions = repository.findRegex(field, query, true, new Document(field, 1));

        } catch (Exception e) {
             errorServices.errorMessage(JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(),e);  
        }
        return suggestions;
    }

    // <editor-fold defaultstate="collapsed" desc="List<Profiles> getProfilesList()">
    public List<Profiles> getProfilesList() {
        try {
          profilesList = repository.findAll(new Document("profile", 1));
        } catch (Exception e) {
             errorServices.errorMessage(JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(),e); 
        }
        return profilesList;
    }// </editor-fold>

    public void setProfilesList(List<Profiles> profilesList) {
        this.profilesList = profilesList;
    }

    // <editor-fold defaultstate="collapsed" desc="Boolean isDeleted(Profiles profiles)">
    public Boolean isDeleted(Profiles profiles) {
        Boolean found = false;
        try {
            Document doc = new Document("profiles.idprofiles", profiles.getIdprofile());
            Integer count = usuarioRepository.count(doc);
            if (count > 0) {
                return false;
            }

        } catch (Exception e) {
             errorServices.errorMessage(JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(),e);  
        }
        return true;
    }  // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="findById(String id)">
    public Profiles findById(String id) {
        Profiles profiles = new Profiles();
        try {

            profiles.setIdprofile(id);
            Optional<Profiles> optional = repository.findById(profiles);
            if (optional.isPresent()) {
                return optional.get();
            }
        } catch (Exception e) {
             errorServices.errorMessage(JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(),e);  
        }

        return profiles;
    }
    // </editor-fold>
}
