package com.book.bpb.backend.dao;

import com.book.bpb.backend.entity.Role;
import com.book.bpb.backend.entity.User;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;


import org.bson.Document;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.result.DeleteResult;
import org.bson.types.ObjectId;


@Stateless
public class UserManagement {

    public static final String DB_NAME = "demo";
    public static final String COLL_NAME = "users";

    protected MongoDatabase db;
    protected MongoCollection<Document> collection;

    @Inject
    MongoClient mongoClient;

    @PostConstruct
    public void init() {
        this.db = this.mongoClient.getDatabase(DB_NAME);
        this.collection = this.db.getCollection(COLL_NAME);
    }

    public List<User> findAllUser() {
        List<User> users = new ArrayList<>();
        MongoCursor<Document> cursor = this.collection.find().iterator();

        try {

            while (cursor.hasNext()) {

                Document userBson = cursor.next();
                Document roleBson = userBson.get("role", Document.class);

                User user = new User();
                Role role = new Role();

                String objectId = userBson.getObjectId("_idUser").toString();

                user.setIdUser(objectId);
                user.setFirstName(userBson.getString("firstName"));
                user.setLastName(userBson.getString("lastName"));
                user.setContactEmail(userBson.getString("contactEmail"));
                user.setPassword(userBson.getString("password"));
                user.setBlocked(userBson.getBoolean("blocked"));
                user.setCountry(userBson.getString("country"));

                role.setIdRole(roleBson.getString("idRole"));
                role.setName(roleBson.getString("name"));
                role.setAuthorizedModule1(roleBson.getBoolean("authorizedModule1"));
                role.setAccessModule2(roleBson.getBoolean("accessModule2"));

                user.setMainRole(role);

                users.add(user);
            }

        } finally {
            cursor.close();
        }

        return users;
    }

    public User findBYName(String firstName) {
        User user = new User();
        Role role = new Role();

        Document userBson = this.collection.find(eq("firstName", firstName)).first();
        Document roleBson = userBson.get("address", Document.class);

        String objectId = userBson.getObjectId("_idUser").toString();

        user.setIdUser(objectId);
        user.setFirstName(userBson.getString("firstName"));
        user.setLastName(userBson.getString("lastName"));
        user.setContactEmail(userBson.getString("contactEmail"));
        user.setPassword(userBson.getString("password"));
        user.setBlocked(userBson.getBoolean("blocked"));
        user.setCountry(userBson.getString("country"));

        role.setIdRole(roleBson.getString("idRole"));
        role.setName(roleBson.getString("name"));
        role.setAuthorizedModule1(roleBson.getBoolean("authorizedModule1"));
        role.setAccessModule2(roleBson.getBoolean("accessModule2"));

        user.setMainRole(role);

        return user;
    }

    public void createUser(User user) {
        Document doc = new Document();
        Document roleBson = new Document();
        ObjectId id = new ObjectId();

        Role role = user.getMainRole();

        doc.append("firstName", user.getFirstName());
        doc.append("lastName", user.getLastName());
        doc.append("contactEmail", user.getContactEmail());
        doc.append("password", user.getPassword());
        doc.append("blocked", user.getBlocked());
        doc.append("country", user.getCountry());

        roleBson.append("idRole", role.getIdRole());
        roleBson.append("name", role.getName());
        roleBson.append("authorizedModule1", role.getAuthorizedModule1());
        roleBson.append("accessModule2", role.getAccessModule2());

        doc.append("role", new Document(roleBson));

        this.collection.insertOne(doc);
    }

    public void updateUserByName(String firstName, User user) {
        Document doc = new Document();
        Document roleBson = new Document();

        Role role = user.getMainRole();

        doc.append("firstName", user.getFirstName());
        doc.append("lastName", user.getLastName());
        doc.append("contactEmail", user.getContactEmail());
        doc.append("password", user.getPassword());
        doc.append("blocked", user.getBlocked());
        doc.append("country", user.getCountry());

        roleBson.append("idRole", role.getIdRole());
        roleBson.append("name", role.getName());
        roleBson.append("authorizedModule1", role.getAuthorizedModule1());
        roleBson.append("accessModule2", role.getAccessModule2());

        doc.append("role", new Document(roleBson));

        this.collection.findOneAndUpdate(eq("firstName", firstName), new Document("$set", doc));
    }

    public DeleteResult removeByName(String firstName) {
        DeleteResult deleteResult = this.collection.deleteOne(eq("firstName", firstName));
        return deleteResult;
    }
}
