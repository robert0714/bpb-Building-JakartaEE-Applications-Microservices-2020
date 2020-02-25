package com.bpbonline.jsfhelloworld.entity;

import com.avbravo.jmoordb.anotations.Embedded;
import com.avbravo.jmoordb.anotations.Id;
import com.avbravo.jmoordb.anotations.Secondary;
import com.avbravo.jmoordb.pojos.UserInfo;
import java.util.List;

public class Role {

    @Id
    private String idrole;
    private String role;
    private String activo;
    @Embedded
    List<UserInfo> userInfo;

    public Role(String idrole, String role, String activo, List<UserInfo> userInfo) {
        this.idrole = idrole;
        this.role = role;
        this.activo = activo;
        this.userInfo = userInfo;
    }

    public String getIdrole() {
        return idrole;
    }

    public void setIdrole(String idrole) {
        this.idrole = idrole;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public List<UserInfo> getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(List<UserInfo> userInfo) {
        this.userInfo = userInfo;
    }

    
   

}
