package com.book.bpb.backend.entity;

import com.avbravo.jmoordb.anotations.Embedded;
import com.avbravo.jmoordb.anotations.Id;
import com.avbravo.jmoordb.anotations.Secondary;
import com.avbravo.jmoordb.pojos.UserInfo;

import javax.validation.constraints.Email;
import java.util.List;


public class User {

    @Id
    private String idUser;
    @Secondary
    private String firstName;
    private String lastName;
    @Email
    private String contactEmail;
    private String password;
    private Boolean blocked;
    private String country;
    private Role mainRole;

    public User(){}

    public User(String idUser, String firstName, String lastName, @Email String contactEmail, String password, Boolean blocked, String country, Role mainRole) {
        this.idUser = idUser;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactEmail = contactEmail;
        this.password = password;
        this.blocked = blocked;
        this.country = country;
        this.mainRole = mainRole;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Role getMainRole() {
        return mainRole;
    }

    public void setMainRole(Role mainRole) {
        this.mainRole = mainRole;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", password='" + password + '\'' +
                ", blocked=" + blocked +
                ", country='" + country + '\'' +
                ", mainRole=" + mainRole +
                '}';
    }

}


