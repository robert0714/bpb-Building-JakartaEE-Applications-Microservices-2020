/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/
package com.jsfmicroservices.repository;
import com.avbravo.jmoordb.mongodb.repository.Repository;
import com.jsfmicroservices.entity.Profile;
import com.jsfmicroservices.entity.User;
import javax.ejb.Stateless;

/**
 *
 * @author avbravo
 */
@Stateless
public class ProfileRepository extends Repository<Profile> {

    public ProfileRepository(){
           super(Profile.class,"jsfdata","profile");
    }
}
