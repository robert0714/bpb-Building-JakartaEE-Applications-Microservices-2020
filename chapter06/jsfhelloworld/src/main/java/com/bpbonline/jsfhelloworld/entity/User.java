package com.bpbonline.jsfhelloworld.entity;

import com.avbravo.jmoordb.anotations.Embedded;
import com.avbravo.jmoordb.anotations.Id;
import com.avbravo.jmoordb.anotations.Referenced;
import com.avbravo.jmoordb.pojos.UserInfo;
import java.util.List;
import javax.validation.constraints.Size;

public class User {

    @Id
    private String username;
    @Size(min = 10, max = 250)
    private String password;
    @Size(min = 8, max = 250)
    private String name;

    @Referenced(collection = "Profiles",
            field = "idprofile", javatype = "String", lazy = false,
            repository = "com.bpbonline.jsfhelloworld.repository.ProfilesRepository")
    private List<Profiles> profiles;
    
    private String active;

    @Embedded
    List<UserInfo> userInfo;

    public User() {
        
    }

    public User(String username, String password, String name, List<Profiles> profiles, String active, List<UserInfo> userInfo) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.profiles = profiles;
        this.active = active;
        this.userInfo = userInfo;
    }

    
    
    
    
    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

   
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Profiles> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profiles> profiles) {
        this.profiles = profiles;
    }

    public List<UserInfo> getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(List<UserInfo> userInfo) {
        this.userInfo = userInfo;
    }

    
    
   
    

}
