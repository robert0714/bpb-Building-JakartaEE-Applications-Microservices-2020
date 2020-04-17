/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpbonline.jsfcrud.security;

import com.avbravo.jmoordb.configuration.JmoordbContext;
import com.avbravo.jmoordbutils.JsfUtil;

import com.avbravo.jmoordbutils.JmoordbResourcesFiles;
import com.bpbonline.jsfcrud.entity.Profile;
import com.bpbonline.jsfcrud.entity.User;
import com.bpbonline.jsfcrud.repository.UserRepository;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;

/**
 *
 * @author avbravo
 */
@ApplicationScoped
public class CustomInMemoryIdentityStore implements IdentityStore {

    private String profileValue = "";
    @Inject
    JmoordbResourcesFiles rf;
    @Inject
    UserRepository usuarioRepository;

    @Override
    public CredentialValidationResult validate(Credential credential) {
        UsernamePasswordCredential login = (UsernamePasswordCredential) credential;
        //Esta adaptado para guardar el GRUPO DEL USUARIO la validacion se hizo en LoginController
        String username = login.getCaller();
        String password = login.getPasswordAsString();

        if (!isValidUser(username, password)) {
            return CredentialValidationResult.NOT_VALIDATED_RESULT;
        }
        return new CredentialValidationResult(profileValue, new HashSet<>(Arrays.asList(profileValue)));

    }

    // <editor-fold defaultstate="collapsed" desc="Boolean isValidUser(String username, String password) ">
    private Boolean isValidUser(String username, String password) {
        try {

            if (!isValidData(username, password)) {
                return false;
            }
            User user = new User();
            user.setUsername(username);

           Profile profiles = (Profile) JmoordbContext.get("jmoordb_profiles");
//Assign the user profile
            this.profileValue = profiles.getIdprofile();

            //-----------------
            user.setUsername(username);

            Optional<User> optional = usuarioRepository.findById(user);
            if (!optional.isPresent()) {
                JsfUtil.warningMessage(rf.getAppMessage("login.usernamenotvalid"));
                return false;
            } else {
                User u2 = optional.get();

                user = u2;
                //guarda el usuario logeado
                JmoordbContext.put("jmoordb_user", user);

                if (!JsfUtil.desencriptar(user.getPassword()).equals(password)) {
        
                    JsfUtil.successMessage(rf.getAppMessage("login.passwordnotvalid"));
                    return false;
                }
                if (user.getActive().equals("no")) {
                    JsfUtil.successMessage(rf.getAppMessage("login.usuarioinactivo"));
                    return false;
                }

            

                //Valida los roles del usuario si coincide con el seleccionado
                Boolean foundprofile = false;
                for (Profile p : user.getProfile()) {
                    if (profiles.getIdprofile().equals(p.getIdprofile())) {
                        foundprofile = true;
                    }
                }
                if (!foundprofile) {
                    JsfUtil.successMessage("No profile assigned");
                    return false;
                }
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Boolean isValidData(String username, String password)">
    private Boolean isValidData(String username, String password) {
        try {
            if (username.isEmpty() || username.equals("") || username == null) {
                JsfUtil.successMessage(rf.getAppMessage("warning.usernameisempty"));
                return false;
            }
            if (password.isEmpty() || password.equals("") || password == null) {
                JsfUtil.successMessage(rf.getAppMessage("warning.passwordisempty"));
                return false;
            }
            return true;
        } catch (Exception e) {
        }

        return false;
    }
    // </editor-fold>
}
