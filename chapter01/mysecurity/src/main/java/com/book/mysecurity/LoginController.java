/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.book.mysecurity;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import static javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters.withParams;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Named
@SessionScoped
public class LoginController implements Serializable {
// <editor-fold defaultstate="collapsed" desc="fields">

    @Inject
    private SecurityContext securityContext;
    @Inject
    private ExternalContext externalContext;
    @Inject
    private FacesContext facesContext;

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(LoginController.class.getName());

    private String username;
    private String password;
    private String role;



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

 

    
// <editor-fold defaultstate="collapsed" desc="login">
    public String login() {
        try {

            Credential credential
                    = new UsernamePasswordCredential(username, new Password(password));

            AuthenticationStatus status = securityContext.authenticate(
                    (HttpServletRequest) externalContext.getRequest(),
                    (HttpServletResponse) externalContext.getResponse(),
                    withParams().credential(credential));


//---Injectarlo en el Session
            switch (status) {
                case SEND_CONTINUE:
                    facesContext.responseComplete();
                    break;
                case SEND_FAILURE:
                    facesContext.addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed", null));
                    break;
                case SUCCESS:                 
                   facesContext.addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", null));
                    return "/faces/pages/index.xhtml?faces-redirect=true";
                case NOT_DONE:
            }

        } catch (Exception e) {
            JsfUtil.errorMessage(e.getLocalizedMessage());
        }
        return "";
    } // </editor-fold>

}
