package com.epam.player.dao;

import java.util.List;

import org.mongojack.DBCursor;
import org.mongojack.JacksonDBCollection;

import com.epam.player.model.User;
import com.mongodb.DB;


public class UserDAO  {

    private JacksonDBCollection<User,String> usersCollection;

    public UserDAO(DB database) {
        usersCollection = JacksonDBCollection.wrap(database.getCollection("users"),User.class,String.class);
    }


    public List<User> getUsers(){
     return  usersCollection.find().toArray();
    }

    public User create(User person) {
        return usersCollection.save(person).getSavedObject();
    }

    public User getUserByToken(String token){
        DBCursor<User> cursor = usersCollection.find().is("token", token);
        return  cursor.hasNext()?cursor.next():null;
    }


}
