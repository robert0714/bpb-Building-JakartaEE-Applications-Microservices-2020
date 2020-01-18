/*
 * Copyright (c) 2019 IBM Corporation and others
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bpbonline.jakarta.nosql.openlibertymongotest.test;

import com.bpb.jakarta.nosql.openlibertytest.Country;
import com.bpb.jakarta.nosql.openlibertytest.CountryService;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import static org.junit.jupiter.api.Assertions.*;

import org.bson.Document;

import org.junit.jupiter.api.Test;
import org.microshed.testing.jaxrs.RESTClient;
import org.microshed.testing.jupiter.MicroShedTest;
import org.microshed.testing.testcontainers.ApplicationContainer;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;

@MicroShedTest
public class JaxrsJsonIT {

    @Container
    public static GenericContainer<?> mongo = new GenericContainer<>("mongo:3.4")
            .withExposedPorts(27017)
            .withNetworkAliases("testmongo");

    @Container
    public static ApplicationContainer app = new ApplicationContainer()
            .withAppContextRoot("/openlibertytest")
            .withReadinessPath("/health/ready")
            .dependsOn(mongo);
    @RESTClient
    public static CountryService countryService;



    @Test
    public void testMongoDB() {
      
        mongo.start();
        
        String conecction = "mongodb://" + mongo.getContainerIpAddress() + ":" + mongo.getMappedPort(27017);
        
        MongoClient mongoClient = MongoClients.create(conecction);
        MongoDatabase database = mongoClient.getDatabase("test");
       
        MongoCollection<Document> collection = database.getCollection("country");

        Document document = new Document("name", "Panama").append("id", "pa");

        collection.insertOne(document);


      Country country = convert(collection.find(eq("name", "Panama")));

        assertEquals("Panama", country.getName());
    }

   
    public Country convert(FindIterable<Document> iterable) {
        Country country = new Country("","");
        try {

            MongoCursor<Document> cursor = iterable.iterator();
//Use JsonB
            Jsonb jsonb = JsonbBuilder.create();
            while (cursor.hasNext()) {
                country = jsonb.fromJson(cursor.next().toJson(), Country.class);
                break;
            }


        } catch (Exception e) {
            System.out.println(" convert() " + e.getLocalizedMessage());
        }
        return country;

    }

}
