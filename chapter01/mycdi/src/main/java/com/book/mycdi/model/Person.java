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
public class Person {

    private Integer id;
    private String name;
    private Boolean walk;

    public Person() {
    }

    public Person(Integer id, String name, Boolean walk) {
        this.id = id;
        this.name = name;
        this.walk = walk;
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

    public Boolean getWalk() {
        return walk;
    }

    public void setWalk(Boolean walk) {
        this.walk = walk;
    }

    
}
