/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpbonline.demo;

import com.avbravo.jmoordbutils.JsfUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;


/**
 *
 * @author avbravo
 */
@Named
@ViewScoped
@Getter
@Setter
public class PersonController implements Serializable{
private String name;

List<Person> personList = new ArrayList<>();
    /**
     * Creates a new instance of MessagesController
     */
    public PersonController() {
    }
    
    public String save(){
        try {

            Person person = new Person(name);
            personList.add(person);
        } catch (Exception e) {
            JsfUtil.errorDialog("test", e.getLocalizedMessage());
        }
        return "";
    }
}
