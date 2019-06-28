/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.book.mycdi.interceptor;

import com.book.mycdi.controller.JsfUtil;
import com.book.mycdi.model.Dog;
import java.io.Serializable;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

/**
 *
 * @author avbravo
 */
@Model
public class FeedController implements Serializable {
    
    @Inject
    Dog dog;
    
    public Dog getDog() {
        return dog;
    }
    
    public void setDog(Dog dog) {
        this.dog = dog;
    }
    
    @Feed
    public String feedDog() {
        try {            
            System.out.println("The Dog was successfully fed");
        } catch (Exception e) {
            System.out.println("error " + e.getLocalizedMessage());            
        }
        
        return "";
        
    }
}
