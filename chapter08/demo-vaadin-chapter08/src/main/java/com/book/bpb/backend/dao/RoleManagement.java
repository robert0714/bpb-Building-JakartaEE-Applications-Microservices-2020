package com.book.bpb.backend.dao;

import com.book.bpb.backend.entity.Role;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;


@Stateless
public class RoleManagement {

    public static final String DB_NAME = "demo";
    public static final String COLL_NAME = "roles";

    protected MongoDatabase db;
    protected MongoCollection<Document> collection;

    @Inject
    MongoClient mongoClient;

    @PostConstruct
    public void init() {
        this.db = this.mongoClient.getDatabase(DB_NAME);
        this.collection = this.db.getCollection(COLL_NAME);
    }

    public List<Role> findAllRole() {
        List<Role> roles = new ArrayList<>();
        MongoCursor<Document> cursor = this.collection.find().iterator();

        try {

            while (cursor.hasNext()) {

                Document roleBson = cursor.next();

                Role role = new Role();

                String objectId = roleBson.getObjectId("_idRole").toString();

                role.setIdRole(objectId);
                role.setName(roleBson.getString("name"));
                role.setAuthorizedModule1(roleBson.getBoolean("authorizedModule1"));
                role.setAccessModule2(roleBson.getBoolean("accessModule2"));

                roles.add(role);
            }

        } finally {
            cursor.close();
        }

        return roles;
    }

    public Role findBYName(String name) {
        Role role = new Role();

        Document roleBson = this.collection.find(eq("name", name)).first();

        String objectId = roleBson.getObjectId("_idRole").toString();

        role.setIdRole(objectId);
        role.setName(roleBson.getString("name"));
        role.setAuthorizedModule1(roleBson.getBoolean("authorizedModule1"));
        role.setAccessModule2(roleBson.getBoolean("accessModule2"));

        return role;
    }

    public void createRole(Role role) {
        Document doc = new Document();
        ObjectId id = new ObjectId();

        doc.append("idRole", role.getIdRole());
        doc.append("name", role.getName());
        doc.append("authorizedModule1", role.getAuthorizedModule1());
        doc.append("accessModule2", role.getAccessModule2());

        this.collection.insertOne(doc);
    }

    public void updateRoleByName(String name, Role role) {
        Document doc = new Document();

        doc.append("idRole", role.getIdRole());
        doc.append("name", role.getName());
        doc.append("authorizedModule1", role.getAuthorizedModule1());
        doc.append("accessModule2", role.getAccessModule2());

        this.collection.findOneAndUpdate(eq("name", name), new Document("$set", doc));
    }

    public DeleteResult removeByName(String name) {
        DeleteResult deleteResult = this.collection.deleteOne(eq("name", name));
        return deleteResult;
    }
}
