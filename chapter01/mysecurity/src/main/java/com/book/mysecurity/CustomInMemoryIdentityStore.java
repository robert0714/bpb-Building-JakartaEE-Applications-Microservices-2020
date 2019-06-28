/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.book.mysecurity;




import java.util.Arrays;
import java.util.HashSet;
import javax.enterprise.context.ApplicationScoped;
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

    private String roleValue = "";
 
  

    @Override
    public CredentialValidationResult validate(Credential credential) {
        UsernamePasswordCredential login = (UsernamePasswordCredential) credential;
        //Esta adaptado para guardar el GRUPO DEL USUARIO la validacion se hizo en LoginController
        String username = login.getCaller();
        String password = login.getPasswordAsString();

        if (!isValidUser(username, password)) {
            return CredentialValidationResult.NOT_VALIDATED_RESULT;
        }
        return new CredentialValidationResult(roleValue, new HashSet<>(Arrays.asList(roleValue)));

    }

    // <editor-fold defaultstate="collapsed" desc="Boolean isValidUser(String username, String password) ">
    private Boolean isValidUser(String username, String password) {
        try {
//
//            Optional optional = userRepository.findByUsername(username);
//            if(optional.isPresent()){
//                User user = (User) optional.get();
//                roleValue=user.getRole();
//                if(user.getPassword().equals(password) ){
//                    JsfUtil.successMessage("Welcome");
//                    return true;
//                }else{
//                    JsfUtil.warningMessage("Please check the password or role");
//                    return false;
//                }
//            }else{
//                JsfUtil.warningMessage("Username not valid");
//                return false;
//            }
//            
        } catch (Exception e) {
            JsfUtil.errorMessage(e.getLocalizedMessage());
        }
        return false;
    }

    // </editor-fold>
  
}
