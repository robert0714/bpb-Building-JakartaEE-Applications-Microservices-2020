/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpb.jakarta.nosql.openlibertymongotest;

import java.util.Objects;
import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author avbravo
 */
public class Country {

    @NotNull
    private String id;

    @NotNull
    @Size(min = 5, max = 50)
    private String name;

    
     @JsonbCreator
    public Country(@JsonbProperty("name") String name, 
            @JsonbProperty("id") String id) {
        this.name = name;

        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Country))
            return false;
        Country other = (Country) obj;
        return Objects.equals(id, other.id) &&
               Objects.equals(name, other.name) ;
            
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
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
