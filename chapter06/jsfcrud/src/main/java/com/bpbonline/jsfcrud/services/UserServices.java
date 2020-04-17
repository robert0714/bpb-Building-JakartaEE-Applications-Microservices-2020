/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpbonline.jsfcrud.services;


import com.avbravo.jmoordb.mongodb.history.services.ErrorInfoServices;
import com.avbravo.jmoordb.util.JmoordbUtil;
import com.bpbonline.jsfcrud.entity.User;
import com.bpbonline.jsfcrud.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.bson.Document;

/**
 *
 * @authoravbravo
 */
@Stateless

public class UserServices {

 @Inject
    ErrorInfoServices errorServices;
    Boolean coordinadorvalido = false;
    Boolean escoordinador = false;
    @Inject
    UserRepository repository;


    List<User> userList = new ArrayList<>();

    public List<User> complete(String query) {
        List<User> suggestions = new ArrayList<>();
        try {
            suggestions = repository.complete(query);
        } catch (Exception e) {
             errorServices.errorMessage(JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(),e); 
        }

        return suggestions;
    }
    
     // <editor-fold defaultstate="collapsed" desc="completeAutorizadoSalvoConducto(String query)">    
     // <editor-fold defaultstate="collapsed" desc="completeAutorizadoSalvoConducto(String query)">
   /**
    * Retorna el listado de uUsers con autorizado salvo conducto ="si"
    * @param query
    * @return 
    */
  
    

    public List<User> getUserList() {
        try {
            userList = repository.findAll(new Document("username", 1));
        } catch (Exception e) {
             errorServices.errorMessage(JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(),e); 
        }

        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    // <editor-fold defaultstate="collapsed" desc="isDeleted(UUser uUser)">
    public Boolean isDeleted(User uUser) {
        Boolean found = false;
        try {
            Document doc = new Document("uUser.username", uUser.getUsername());
//            Integer count = solicitudRepository.count(doc);
//            if (count > 0) {
//                return false;
//            }

        } catch (Exception e) {
             errorServices.errorMessage(JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(),e); 
        }
        return true;
    }  // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="findById(String id)">
    public User findById(String id) {
        User uUser = new User();
        try {

            uUser.setUsername(id);
            Optional<User> optional = repository.findById(uUser);
            if (optional.isPresent()) {
                return optional.get();
            }
        } catch (Exception e) {
             errorServices.errorMessage(JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(),e); 
        }

        return uUser;
    }
    // </editor-fold>

   
}
