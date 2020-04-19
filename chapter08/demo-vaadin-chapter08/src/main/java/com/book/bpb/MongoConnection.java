package com.book.bpb;


import com.mongodb.MongoClient;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

@Singleton
public class MongoConnection {

    @Produces
    @ApplicationScoped
    public MongoClient mongoClient() {
        MongoClient mongo = new MongoClient("localhost", 27017);
        return mongo;
    }


}
