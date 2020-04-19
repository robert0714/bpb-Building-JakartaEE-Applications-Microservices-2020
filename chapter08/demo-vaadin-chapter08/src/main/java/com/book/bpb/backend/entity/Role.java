package com.book.bpb.backend.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlType(name = "roles")
@XmlAccessorType(XmlAccessType.FIELD)
public class Role {

    @XmlElement(name = "_idRole")
    private String idRole;
    private String name;
    private Boolean authorizedModule1;
    private Boolean accessModule2;

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
