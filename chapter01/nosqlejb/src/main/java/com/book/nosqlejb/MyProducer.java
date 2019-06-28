/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.book.nosqlejb;

import com.mongodb.MongoClient;
import java.util.logging.Logger;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

/**
 *
 * @author avbravo
 */
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class MyProducer {

    private static final Logger LOG = Logger.getLogger(MyProducer.class.getName());

    @Produces
    @ApplicationScoped
    public MongoClient myConnection() {
        MongoClient mongo = new MongoClient("localhost", 27017);
        try {
            mongo = new MongoClient();
        } catch (Exception e) {
            LOG.severe(e.getLocalizedMessage());
        }
        return mongo;
    }

}
