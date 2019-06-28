/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.book.myweb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import org.bson.Document;

/**
 *
 * @author avbravo
 */
@Stateless
public class CountryRepository {

    Jsonb jsonb = JsonbBuilder.create();
    @Inject
    MongoClient myConnection;

    public MongoDatabase getMongoDatabase() {
        try {
            MongoDatabase db = myConnection.getDatabase("book");
            return db;
        } catch (Exception e) {
            JsfUtil.error(e.getLocalizedMessage());
        }
        return null;
    }

    public List<Country> findAll() {
        List<Country> countryList = new ArrayList<>();
        try {
            for (Document doc : getMongoDatabase().getCollection("user").find()) {
                Country country= jsonb.fromJson(doc.toJson(), Country.class);
                countryList.add(country);
            }
        } catch (Exception e) {
            JsfUtil.error(e.getLocalizedMessage());
        }

        return countryList;
    }

      
    public Boolean save(Country country){
        try {
             getMongoDatabase().getCollection("user").insertOne(Document.parse(jsonb.toJson(country)));
            return true;
        } catch (Exception e) {
            JsfUtil.error(e.getLocalizedMessage());
        }
        return false;
    }
}
