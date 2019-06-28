/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.book.myjsonb;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * @author avbravo
 */
public class Animal {
    @Min(value = 14)
    private String id;
    @NotNull
    private String name;

    public Animal() {
    }

    public Animal(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    
    

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public String getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    
    
}
