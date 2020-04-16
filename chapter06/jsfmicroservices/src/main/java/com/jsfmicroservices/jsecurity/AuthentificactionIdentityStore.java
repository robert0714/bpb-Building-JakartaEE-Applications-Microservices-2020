/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsfmicroservices.jsecurity;

import static java.util.Arrays.asList;
import java.util.HashSet;
import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import static javax.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;
import javax.security.enterprise.identitystore.IdentityStore;

/**
 *
 * @author avbravo
 */
@ApplicationScoped
public class AuthentificactionIdentityStore implements IdentityStore {
    


    public CredentialValidationResult validate(UsernamePasswordCredential usernamePasswordCredential) {
        try {
           
     

            if (usernamePasswordCredential.compareTo("demo", "demo")) {
                return new CredentialValidationResult("demo", new HashSet<>(asList("admin", "testing")));
            }
        } catch (Exception e) { 
            
            System.out.println("CredentialValidationResult " + e.getLocalizedMessage());
        }

        return INVALID_RESULT;
    }

}
