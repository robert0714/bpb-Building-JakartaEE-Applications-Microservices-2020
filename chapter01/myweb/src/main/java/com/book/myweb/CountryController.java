/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.book.myweb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author avbravo
 */
@Named
@ViewScoped
public class CountryController implements Serializable {

    @Inject
    CountryRepository countryRepository;

    Country country = new Country();

    List<Country> listCountry = new ArrayList<>();

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Country> getListCountry() {
        return countryRepository.findAll();
    }

    public void setListCountry(List<Country> listCountry) {
        this.listCountry = listCountry;
    }

    
    
    
    @PostConstruct
    public void init() {
    }

    public String save() {
        try {
            if (countryRepository.save(country)) {
                JsfUtil.info("Save");
            } else {
                JsfUtil.warn("Not Save");
            }
        } catch (Exception e) {
            JsfUtil.error(e.getLocalizedMessage());
        }
        return "";
    }

}
