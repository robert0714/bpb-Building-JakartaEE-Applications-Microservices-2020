/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ch3_1.entity;

import com.avbravo.jmoordb.anotations.Id;



/**
 *
 * @author avbravo
 */
public class Country {
    @Id
    private String idcountry;
    private String country;

    public Country() {
    }

    
    
    
    public String getIdcountry() {
        return idcountry;
    }

    public void setIdcountry(String idcountry) {
        this.idcountry = idcountry;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    
}
