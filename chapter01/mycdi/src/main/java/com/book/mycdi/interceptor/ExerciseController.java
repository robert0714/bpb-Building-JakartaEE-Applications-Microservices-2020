/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.book.mycdi.interceptor;

import com.book.mycdi.model.Dog;
import com.book.mycdi.model.Person;
import java.io.Serializable;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

/**
 *
 * @author avbravo
 */
@Model
public class ExerciseController implements Serializable {
    
    @Inject
    Person person;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
    
    
   
    
    @Feed
    public String start() {
        try {            
            System.out.println("The person started the walk");
        } catch (Exception e) {
            System.out.println("error " + e.getLocalizedMessage());            
        }
        
        return "";
        
    }
}
