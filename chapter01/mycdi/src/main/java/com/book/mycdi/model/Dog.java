/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.book.mycdi.model;

import javax.enterprise.inject.Model;

/**
 *
 * @author avbravo
 */
@Model
public class Dog {
 private Integer id;
 private String name;
 private Boolean hunger;

    public Dog() {
    }

    public Dog(Integer id, String name, Boolean hunger) {
        this.id = id;
        this.name = name;
        this.hunger = hunger;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getHunger() {
        return hunger;
    }

    public void setHunger(Boolean hunger) {
        this.hunger = hunger;
    }

    
    
}
