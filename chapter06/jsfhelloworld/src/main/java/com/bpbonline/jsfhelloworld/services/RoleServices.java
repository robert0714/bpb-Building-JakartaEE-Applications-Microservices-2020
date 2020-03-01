/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpbonline.jsfhelloworld.services;

import com.avbravo.jmoordb.mongodb.history.services.ErrorInfoServices;
import com.avbravo.jmoordb.util.JmoordbUtil;

import com.bpbonline.jsfhelloworld.entity.Role;
import com.bpbonline.jsfhelloworld.repository.RoleRepository;
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
public class RoleServices {

 @Inject
    ErrorInfoServices errorServices;
    @Inject
    RoleRepository repository;

    @Inject
    UserRepository usuarioRepository;
    List<Role> roleList = new ArrayList<>();

    public List<Role> complete(String query) {
        List<Role> suggestions = new ArrayList<>();
        try {
            suggestions = repository.complete(query);
        } catch (Exception e) {
             errorServices.errorMessage(JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(),e);  
        }

        return suggestions;
    }

    public List<Role> completeFilter(String query) {
        List<Role> suggestions = new ArrayList<>();
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

    // <editor-fold defaultstate="collapsed" desc="getRolList()">
    public List<Role> getRolList() {
        try {
            roleList = repository.findAll(new Document("rol", 1));
        } catch (Exception e) {
             errorServices.errorMessage(JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(),e); 
        }
        return roleList;
    }// </editor-fold>

    public void setRolList(List<Role> roleList) {
        this.roleList = roleList;
    }

    // <editor-fold defaultstate="collapsed" desc="isDeleted(Rol rol)">
    public Boolean isDeleted(Role role) {
        Boolean found = false;
        try {
            Document doc = new Document("rol.idrol", role.getIdrole());
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
    public Role findById(String id) {
        Role role = new Role();
        try {

            role.setIdrole(id);
            Optional<Role> optional = repository.findById(role);
            if (optional.isPresent()) {
                return optional.get();
            }
        } catch (Exception e) {
             errorServices.errorMessage(JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(),e);  
        }

        return role;
    }
    // </editor-fold>
}
