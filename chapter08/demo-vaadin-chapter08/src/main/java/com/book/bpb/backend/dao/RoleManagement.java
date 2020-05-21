package com.book.bpb.backend.dao;

import com.book.bpb.backend.entity.Role;
import javax.ejb.Stateless;
import com.avbravo.jmoordb.mongodb.repository.Repository;


@Stateless
public class RoleManagement extends Repository<Role>{

public RoleManagement(){
    super(Role.class,"demo","role");
}
}
