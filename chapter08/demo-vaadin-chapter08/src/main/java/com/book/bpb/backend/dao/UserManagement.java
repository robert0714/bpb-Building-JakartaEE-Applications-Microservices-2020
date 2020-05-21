package com.book.bpb.backend.dao;

import com.avbravo.jmoordb.mongodb.repository.Repository;
import com.book.bpb.backend.entity.User;
import javax.ejb.Stateless;

@Stateless
public class UserManagement extends Repository<User> {

    public UserManagement(){
        super(User.class,"demo","user");
    }
}
