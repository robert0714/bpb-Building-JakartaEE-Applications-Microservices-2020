/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpbonline.jsfhelloworld.repository;

import com.avbravo.jmoordb.mongodb.repository.Repository;
import com.bpbonline.jsfhelloworld.entity.Profile;
import com.bpbonline.jsfhelloworld.entity.User;

/**
 *
 * @author avbravo
 */
public class ProfileRepository extends Repository<Profile> {

       public ProfileRepository(){
        super(Profile.class,"jsfdata","user");
    }
   
}
