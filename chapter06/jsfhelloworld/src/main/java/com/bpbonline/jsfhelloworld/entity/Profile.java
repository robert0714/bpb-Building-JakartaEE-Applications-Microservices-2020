package com.bpbonline.jsfhelloworld.entity;

import com.avbravo.jmoordb.pojos.UserInfo;
import java.util.List;
import javax.persistence.Embedded;
import javax.persistence.Id;

public class Profile {

    @Id
    private String idprofile;
    private String profile;
    private String active;
    @Embedded
    List<UserInfo> userInfo;

    public Profile() {
    }

    public Profile(String idprofile, String profile, String active, List<UserInfo> userInfo) {
        this.idprofile = idprofile;
        this.profile = profile;
        this.active = active;
        this.userInfo = userInfo;
    }

    public String getIdprofile() {
        return idprofile;
    }

    public void setIdprofile(String idprofile) {
        this.idprofile = idprofile;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public List<UserInfo> getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(List<UserInfo> userInfo) {
        this.userInfo = userInfo;
    }

   
    
    
}
