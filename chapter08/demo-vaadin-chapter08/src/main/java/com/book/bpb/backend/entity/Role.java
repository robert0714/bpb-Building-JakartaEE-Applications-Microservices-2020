package com.book.bpb.backend.entity;

import com.avbravo.jmoordb.anotations.Embedded;
import com.avbravo.jmoordb.anotations.Id;
import com.avbravo.jmoordb.anotations.Secondary;
import com.avbravo.jmoordb.pojos.UserInfo;
import java.util.List;

public class Role {

    @Id
    private String idRole;
    @Secondary
    private String name;
    private Boolean authorizedModule1;
    private Boolean accessModule2;

    public Role(){}

    public Role(String idRole, String name, Boolean authorizedModule1, Boolean accessModule2) {
        this.idRole = idRole;
        this.name = name;
        this.authorizedModule1 = authorizedModule1;
        this.accessModule2 = accessModule2;
    }

    public String getIdRole() {
        return idRole;
    }

    public void setIdRole(String idRole) {
        this.idRole = idRole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getAuthorizedModule1() {
        return authorizedModule1;
    }

    public void setAuthorizedModule1(Boolean authorizedModule1) {
        this.authorizedModule1 = authorizedModule1;
    }

    public Boolean getAccessModule2() {
        return accessModule2;
    }

    public void setAccessModule2(Boolean accessModule2) {
        this.accessModule2 = accessModule2;
    }

    @Override
    public String toString() {
        return "Role{" +
                "idRole=" + idRole +
                ", name='" + name + '\'' +
                ", authorizedModule1=" + authorizedModule1 +
                ", accessModule2=" + accessModule2 +
                '}';
    }


}
