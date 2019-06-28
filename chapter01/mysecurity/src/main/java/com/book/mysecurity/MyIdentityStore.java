/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.book.mysecurity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;

/**
 *
 * @author avbravo
 */
public class MyIdentityStore implements IdentityStore{
     @Override
    public CredentialValidationResult validate(Credential credential) {
          UsernamePasswordCredential usernamePassword = (UsernamePasswordCredential) credential;
        //Esta adaptado para guardar el GRUPO DEL USUARIO la validacion se hizo en LoginController
               if (!valid(usernamePassword.getCaller(),usernamePassword.getPasswordAsString())) {
            return CredentialValidationResult.NOT_VALIDATED_RESULT;
        }
         return new CredentialValidationResult("mygroup", new HashSet<>(Arrays.asList("mygroup")));
    }
}
