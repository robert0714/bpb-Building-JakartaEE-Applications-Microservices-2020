/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/
package com.bpbonline.jsfhelloworld.repository;
import com.avbravo.jmoordb.mongodb.repository.Repository;
import com.bpbonline.jsfhelloworld.entity.Role;
import javax.ejb.Stateless;

/**
 *
 * @author avbravo
 */
@Stateless
public class RoleRepository extends Repository<Role> {

    public RoleRepository(){
        super(Role.class);
    }
   

}
