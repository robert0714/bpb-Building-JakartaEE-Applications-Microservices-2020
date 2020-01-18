/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpb.jakarta.nosql.openlibertymongotest;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;

/**
 *
 * @author avbravo
 */
@ApplicationScoped
public class CountryRepository {

    private final Map<String, Country> countryRepo = new HashMap<>();

    public String save(String name, String id) {
        Country country = new Country(name, id);
        countryRepo.put(country.getId(), country);
        return country.getId();
    }

    public Collection<Country> findAll() {
        return countryRepo.values();
    }

    public Country getCountry(String id) {
        Country foundCountry = countryRepo.get(id);
        if (foundCountry == null) {
            countryNotFound(id);
        }
        return foundCountry;
    }

    public void removeCountry(String id) {
        Country toDelete = countryRepo.get(id);
        if (toDelete == null) {
            countryNotFound(id);
        }
        countryRepo.remove(id);
    }

    private void countryNotFound(String id) {
        throw new NotFoundException("Country with id " + id + " not found.");
    }

    public void updateCountry(String id, Country p) {
        Country toUpdate = getCountry(id);
        if (toUpdate == null) {
            countryNotFound(id);
        }
        countryRepo.put(id, p);
    }

}
