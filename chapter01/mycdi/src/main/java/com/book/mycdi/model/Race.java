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
public class Race {
    private String id;
    private String name;

    public Race() {
    }

    public Race(String id, String name) {
        this.id = id;
        this.name = name;
    }

    
    
    public String getId() {
        return id;
    }

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
