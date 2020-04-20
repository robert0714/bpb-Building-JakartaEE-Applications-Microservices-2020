/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.book.myjaxrs;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author avbravo
 */
@Stateless
public class PersonRepository {
    List<Person> personList = new ArrayList<>();
    public PersonRepository() {
        //Old method
        personList.add(new Person("78-10","Marie"));
        personList.add(new Person("426-3","Stephanie"));
    }
         public List<Person> findAll(){
        return personList;
    }
       public Person findById(String id){
        Person person = personList.stream()
                .filter(p -> id.equals(p.getId()))
                .findAny()
                .orElse(null);
        return person;
    }
}
